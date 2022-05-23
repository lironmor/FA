package Domain;
import AccessData.*;
import org.bson.Document;

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


    public void logOut() {
        loggedInUser = null;
    }

    public boolean logIn(String userName, String password) throws Exception {
        if (userName == null || password == null) {
            throw new Exception("Some parameters are missing");
        }
        if (getLoggedInUser() != null) {
            throw new Exception("User is already logged in");
        }
        Document user = this.userDa.get(userName);
        if(user == null){
            throw new Exception("User name or password are wrong");
        }
        String uName = (String) user.get("userName");
        String uPass = (String) user.get("password");
        if (!uPass.equals(password)) {
            throw new Exception("User name or password are wrong");
        } else {
            switch ((String) user.get("type")) {
                case "referee":
                    Document refDoc = refereeDa.get(userName);
                    if (refDoc == null) {
                        throw new Exception("referee not exist in DB");
                    }
                    refDoc = (Document) refDoc.get("ref");
                    String name = (String) refDoc.get("fullName");
                    String email = (String) refDoc.get("email");
                    String role = (String) refDoc.get("refereeRole");
                    String degree = (String) refDoc.get("degree");
                    loggedInUser = new Referee(name, email, uName, password, role, degree);
                    return true;

                case "rfa":
                    Document faDoc = faDa.get(userName);
                    if (faDoc == null) {
                        throw new Exception("FA not exist in DB");
                    }
                    faDoc = (Document) faDoc.get("rfa");
                    name = (String) faDoc.get("fullName");
                    email = (String) faDoc.get("email");
                    loggedInUser = new RFA(name, email, uName, password);
                    return true;

                default:
                    return false;

            }
        }
    }


    public boolean embedGame(String gameId, Date time, String stadiumName) throws Exception {
        if(!loggedInUser.getClass().getName().equals(RFA.class.getName())) {
            throw new Exception("User is not allowed to embed games");
        } else if(gameId == null || time == null || stadiumName == null){
            throw new Exception("Not valid game");
        } else {
            Game game = getGame(gameId);
            game.setTimeAndDate(time);
            Document stadiumDoc = (Document) (stadiumDa.get(stadiumName)).get("stadium");
            Stadium stadium = getStadiumFromDoc(stadiumDoc);
            game.setStadium(stadium);
            if (!matchUpP.homeTeamStadium(game) || !matchUpP.validGameTime(game)) {
                throw new Exception("Matchup policy error");
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
                throw new Exception("Not valid referee");
            }
            if (userDa.get(userName) != null) {
                throw new Exception("User name is alredy exist in the system");
            }
            if (userDa.isEmailExist(email)) {
                throw new Exception("Email is alredy exist in the system");
            }
            Referee referee = new Referee(fullName, email,userName,password,refereeRole,degree);
            refereeDa.save(referee, userName, password, email);
        } else {
            throw new Exception("User is not allowed to register referee");
        }
        return true;
    }

    public void addGame(Game game) throws Exception {
        if(game == null) {
            throw new Exception("game is not valid");
        }
        this.gameDa.save(game.getGameID(), game);
    }

    public Game getGame(String gameId) throws Exception {
        Document gameDoc = this.gameDa.get(gameId);
        if(gameDoc == null) {
            throw new Exception("game not found");
        }
        gameDoc = (Document) gameDoc.get("game");
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
        if(team == null) {
            throw new Exception("Team is not valid");
        }
        this.teamDa.save(team.getTeamName(), team);
    }

    public Team getTeamFromDoc(Document teamDoc) throws Exception {
        if(teamDoc == null) {
            throw new Exception("Team not found");
        }
        String teamN = (String) teamDoc.get("teamName");
        int teamExpense = (int) teamDoc.get("expense");
        Stadium stadium = getStadiumFromDoc((Document) teamDoc.get("stadium"));
        Team team = new Team(teamN, stadium);
        team.setExpense(teamExpense);
        return team;
    }

    public void addStadium(Stadium stadium) throws Exception {
        if(stadium == null) {
            throw new Exception("Stadium is not valid");
        }
        this.stadiumDa.save(stadium.getName(), stadium);
    }

    public Stadium getStadiumFromDoc(Document stadiumDoc) throws Exception {
        String stadName = (String) stadiumDoc.get("name");
        String stadLoc = (String) stadiumDoc.get("location");
        return new Stadium(stadLoc, stadName);
    }

    public void addLeague(League league) throws Exception{
        if(league == null) {
            throw new Exception("League is not valid");
        }
        this.leaguesDa.save(league.getName(), league);
    }

    public void addSeason(Season season) throws Exception{
        if(season == null) {
            throw new Exception("Season is not valid");
        }
        this.seasonsDa.save(season.getId(), season);
    }

}