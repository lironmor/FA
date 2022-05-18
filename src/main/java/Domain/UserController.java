package Domain;
import AccessData.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;


public class UserController {
    private User loggedInUser;
    public static UserController instance;
    private TeamDao teamDa;
    private RFADao faDa;
    private GameDao gameDa;
    private LeaguesDao leaguesDa;
    private RefereeDao refereeDa;
    private SeasonsDao seasonsDa;
    private StadiumDao stadiumDa;
    private UserDao userDa;
    private matchUpPolicy matchUpP;


    // SINGLETON !!
    private UserController() {
        this.loggedInUser = null;
        this.teamDa = TeamDao.getInstance();
        this.faDa = RFADao.getInstance();
        this.gameDa = GameDao.getInstance();
        this.leaguesDa = LeaguesDao.getInstance();
        this.refereeDa = RefereeDao.getInstance();
        this.seasonsDa = SeasonsDao.getInstance();
        this.stadiumDa = StadiumDao.getInstance();
        this.userDa = UserDao.getInstance();
        this.matchUpP = new matchUpPolicy();
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
    //0- all good
    //1- not exist
    //2-already logged in

    public boolean logIn(String userName, String password) throws Exception {
        // SHTUYOT SHEL LIRON... if()...== ()
        //if(setLoggedIn(User u) == true --> HACOL TOV , Else --> "You are alredy logged in as" + loggedInUser.getUserName();
        if (userName == null || password == null) {
            throw new Exception("Some parameters are missing");
        }
        if (getLoggedInUser() != null) {
            throw new Exception("User is already logged in");
        }
        Document user = this.userDa.get(userName);
        String uName = (String) user.get("userName");
        String uPass = (String) user.get("password");
        if (user == null || !uPass.equals(password)) {
            throw new Exception("User name or password are wrong");
        } else {
            switch ((String) user.get("type")) {
                case "referee":
                    Document refDoc = (Document) refereeDa.get(userName).get("ref");
                    if (refDoc == null) {
                        throw new Exception("referee not exist in DB");
                    }
                    String name = (String) refDoc.get("fullName");
                    String email = (String) refDoc.get("email");
                    String role = (String) refDoc.get("refereeRole");
                    String degree = (String) refDoc.get("degree");
                    loggedInUser = new Referee(name, email, uName, password, role, degree);
                    return true;

                case "fa":
                    Document faDoc = (Document) faDa.get("rfa").get(userName);
                    if (faDoc == null) {
                        throw new Exception("FA not exist in DB");
                    }
                    name = (String) faDoc.get("name");
                    email = (String) faDoc.get("email");
                    loggedInUser = new RFA(name, email, uName, password);
                    return true;

                default:
                    return false;

            }
        }
    }

    private boolean setLoggedIn(User u) {
        if (loggedInUser != null) {
            return false;
        } else {
            this.loggedInUser = u;
            return true;
        }
    }

    public boolean embedGame(String gameId, Date time, String stadiumName) throws Exception {
        if(!loggedInUser.getClass().getName().equals(RFA.class.getName())) {
            throw new Exception("User is not allowed to register referee");
        } else if(gameId == null || time == null || stadiumName == null){
            throw new Exception("parameters are null");
        } else {
            Game game = getGame(gameId);
            game.setTimeAndDate(time);
            Document stadiumDoc = (Document) ((Document) stadiumDa.get(stadiumName)).get("stadium");
            Stadium stadium = getStadiumFromDoc(stadiumDoc);
            game.setStadium(stadium);
            if (!matchUpP.homeTeamStadium(game) || !matchUpP.validGameTime(game)) {
                throw new Exception("the game embedding don't follow the matchup policy");
            }
            gameDa.update(game.getGameID(), game);
        }
        return true;
    }

    public boolean registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree, String id) throws Exception {
        if (loggedInUser == null) {
            throw new Exception("No user are currently logged in");
        }
        if (loggedInUser.getClass().getName().equals(RFA.class.getName())) {
            if (fullName == null || email == null || userName == null || password == null || refereeRole == null || degree == null || id == null) {
                throw new Exception("Referee parameters are missing");
            }
            if (userDa.get(userName) != null) {
                throw new Exception("User name is already exist in system");
            }
            Referee referee = new Referee(fullName, email,userName,password,refereeRole,degree);
            refereeDa.save(referee, userName, password);
        } else {
            throw new Exception("User is not allowed to register referee");
        }
        return true;
    }

//    public void addTeam(String teamId, String teamName, String season, String league) throws Exception {
//        if (teamId == null || teamName == null || season == null || league == null) {
//            throw new Exception("some parameters are null");
//        }
//        if (teamDa.get(teamId) != null) {
//            throw new Exception("team is already exist");
//        }
//        Team team = new Team(teamId, teamName, season, league);
//        teamDa.save(teamId, teamName, team.getExpense(), new ArrayList<>(), league, season);
//    }

    public void addGame(Game game) throws Exception {
        this.gameDa.save(game.getGameID(), game);
    }

    public Game getGame(String gameId) throws Exception {
        Document gameDoc = (Document) this.gameDa.get(gameId).get("game");
        if(gameDoc == null) {
            throw new Exception("game not found");
        }
        Document away = (Document) gameDoc.get("awayTeam");
        Team awayT = getTeamFromDoc(away);

        Document home = (Document) gameDoc.get("homeTeam");
        Team homeT = getTeamFromDoc(home);

        String id = (String) gameDoc.get("gameID");
        Stadium stadium = getStadiumFromDoc((Document) gameDoc.get("stadium"));
        Date time = (Date) gameDoc.get("timeAndDate");
        Game game = new Game(id, homeT ,awayT);
        game.setStadium(stadium);
        game.setTimeAndDate(time);
        return game;
    }

    public void addTeam(Team team) throws Exception {
        this.teamDa.save(team.getTeamName(), team);
    }

    public Team getTeamFromDoc(Document teamDoc) throws Exception {
        if(teamDoc == null) {
            throw new Exception("team not found");
        }
        String teamN = (String) teamDoc.get("teamName");
        int teamExpense = (int) teamDoc.get("expense");
        Stadium stadium = getStadiumFromDoc((Document) teamDoc.get("stadium"));
        Team team = new Team(teamN, stadium);
        team.setExpense(teamExpense);
        return team;
    }

    public void addStadium(Stadium stadium) throws Exception {
        this.stadiumDa.save(stadium.getName(), stadium);
    }

    public Stadium getStadiumFromDoc(Document stadiumDoc) throws Exception {
        String stadName = (String) stadiumDoc.get("name");
        String stadLoc = (String) stadiumDoc.get("location");
        return new Stadium(stadLoc, stadName);
    }

    public void addLeague(League league){
        this.leaguesDa.save(league.getName(), league);
    }

    public void addSeason(Season season){
        this.seasonsDa.save(season.getId(), season);
    }

    public static void main(String[] args) throws Exception {
        UserController uc = UserController.getInstance();
        Referee referee1 = new Referee("Roey", "RB@gmail.com", "Roey", "Roey", "main", "expert");
        uc.refereeDa.save(referee1, "Roey", "Roey");
        Referee referee;
        try {
            uc.logIn("Roey", "Roey");
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(uc.getLoggedInUser());
        try {
            uc.registerReferee("Liron", "Liron@gmail.com", "LironMor", "1234", "main", "expert", "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        League league = new League("SeriaA");
        Season season = new Season(league.getName(), 2020,2021);
        league.addSeasonId(season.getId());

        Stadium samiOffer = new Stadium("Haifa", "Sami Offer");
        uc.addStadium(samiOffer);

        Stadium blumfield = new Stadium("Tel-Aviv", "Blumfield");
        uc.addStadium(blumfield);

        Team team = new Team("Maccabi Haifa" , samiOffer);
        Team team1 = new Team("Hapoel Tel Aviv", blumfield);
        team.setExpense(1000);
        team1.setExpense(100);
        uc.addTeam(team);
        uc.addTeam(team1);

        season.addTeamName(team.getTeamName());
        season.addTeamName(team1.getTeamName());

        Game game = new Game("2", team, team1);
        game.setStadium(samiOffer);
        season.addGame(game);

        uc.addSeason(season);
        uc.addLeague(league);
        uc.addGame(game);
        uc.embedGame(game.getGameID(), new Date(122, 4, 30,20,00), "Sami Offer");
    }
}