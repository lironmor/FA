package Domain;
import AccessData.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

//public class UserController {
//    ///data members
//    private User logedInUser;
//    private TeamDao
//
////    public UserController() {
////        this.logedInUser = null;
////    }
////
////    public UserController() {ud = UserDaoSQL.getInstance();
////    }
////    public void insertUser (User aUser ) {
////        try {
////            ud.save(aUser);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//}

public class UserController {
    private User loggedInUser;
    public static UserController instance;
    // SHTUYOT SHEL LIRON
    private TeamDao teamDa;
//    private AssetsDao assetsDa;
    private RFADao faDa;
    private GameDao gameDa;
    private LeaguesDao leaguesDa;
    private RefereeDao refereeDa;
    private SeasonsDao seasonsDa;
    private StadiumDao stadiumDa;
    private UserDao userDa;


    // SINGLETON !!
    private UserController() {
        this.loggedInUser = null;
        this.teamDa = TeamDao.getInstance();
//        this.assetsDa = AssetsDao.getInstance();
        this.faDa = RFADao.getInstance();
        this.gameDa = GameDao.getInstance();
        this.leaguesDa = LeaguesDao.getInstance();
        this.refereeDa = RefereeDao.getInstance();
        this.seasonsDa = SeasonsDao.getInstance();
        this.stadiumDa = StadiumDao.getInstance();
        this.userDa = UserDao.getInstance();
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
                    Document refDoc = refereeDa.get(uName);
                    if (refDoc == null) {
                        throw new Exception("referee not exist in DB");
                    }
                    String name = (String) refDoc.get("name");
                    String email = (String) refDoc.get("email");
                    String role = (String) refDoc.get("role");
                    String degree = (String) refDoc.get("degree");
                    loggedInUser = new Referee(name, email, uName, password, role, degree, new ArrayList<>());
                    return true;

                case "fa":
                    Document faDoc = faDa.get(uName);
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

    public boolean embedGame(String gameID) {
        //SHTUYOT SHEL LIRON --> if no such GAME - RETURN 1;
        //CREATE Team1
        //CREATE team2
        //Create STADIUM
        //GAMEREPORT = NULL
        //GAME = NEW GAME(..)
        //SET TIME  REFEREE STADIUM
        // SENT TO POLICY
        //
        //SAVE BACK TO DB
        return true;
    }

    public boolean registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree, String id) throws Exception {
        if (loggedInUser == null) {
            throw new Exception("No user are currently logged in");
        }
        if (loggedInUser.getClass().getName().equals(RFA.class.getName())) {
            if (fullName == null || email == null || userName == null || password == null || refereeRole == null || degree == null) {
                throw new Exception("Referee parameters are missing");
            }
            if (userDa.get(userName) != null) {
                throw new Exception("User name is already exist in system");
            }
//            refereeDa.save(fullName, email, userName, password, degree, refereeRole, new ArrayList<>());
            Referee referee = new Referee(fullName, email,userName,password,refereeRole,degree,new ArrayList<>());
            refereeDa.save(id, referee, userName, password);
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

    public void addGame() {

    }

    public static void main(String[] args) throws Exception {
        UserController uc = UserController.getInstance();
        RFADao rfaDao = RFADao.getInstance();
        rfaDao.save("Roey", "RB@gmail.com", "Roey", "Roey");
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
        Stadium samiOffer = new Stadium("Haifa", "Sami Offer");
        uc.stadiumDa.save(samiOffer.getName(), samiOffer);
        Stadium blumfield = new Stadium("Tel-Aviv", "Blumfield");
        uc.stadiumDa.save(blumfield.getName(), blumfield);
        Team team = new Team("maccabi haifa" , samiOffer);
        Team team1 = new Team("Hapoel Tel Aviv", blumfield);
        team.setExpense(1000);
        team1.setExpense(100);
        uc.teamDa.save(team.getTeamName(), team);
        uc.teamDa.save(team1.getTeamName(), team1);
        Game game = new Game("2", team, team1, team.getStadium());
        uc.gameDa.save(game.getGameID(), game);
//        referee = new Referee("Liron", "Liron@gmail.com", "Liron", "1234", "main", "expert", new ArrayList<>());
//        uc.refereeDa.save("1", referee);
        Document document = (Document) uc.gameDa.get(game.getGameID()).get("game");
        Document away = (Document) document.get("awayTeam");
        Document awayTS = (Document) away.get("stadium");
        Document home = (Document) document.get("homeTeam");
        Document homeTS = (Document) home.get("stadium");
        Team homeT = new Team((String) home.get("teamName"), new Stadium((String) homeTS.get("name"), (String) homeTS.get("location")));
        Team awayT = new Team((String) away.get("teamName"), new Stadium((String) awayTS.get("name"), (String) awayTS.get("location")));
        int homeEx = (int) home.get("expense");
        int awayEx = (int) away.get("expense");
        homeT.setExpense(homeEx);
        awayT.setExpense(awayEx);
        String id = (String) document.get("gameID");
        String stadiumName = (String) ((Document)document.get("stadium")).get("name");
        String stadiumLoc = (String) ((Document)document.get("stadium")).get("location");
        Game g = new Game(id, homeT ,awayT, new Stadium(stadiumLoc, stadiumName));
        System.out.println(g.getHomeTeam().getExpense());
    }
}