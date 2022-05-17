package Domain;
import AccessData.*;
import org.bson.Document;

import java.util.ArrayList;

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
    private AssetsDao assetsDa;
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
        this.assetsDa = AssetsDao.getInstance();
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
        int result = 0;
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
                    loggedInUser = new Referee(name, email, uName, password, role, degree , new ArrayList<>());
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

    private boolean setLoggedIn(User u){
        if(loggedInUser != null){
            return false;
        }
        else{
            this.loggedInUser = u;
            return true;
        }
    }
    public boolean embedGame(String gameID){
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

    public boolean registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree) throws Exception {
        if(loggedInUser == null) {
            throw new Exception("No user are currently logged in");
        }
        if(loggedInUser.getClass().getName().equals(RFA.class.getName())) {
            if (fullName == null || email == null || userName == null || password == null || refereeRole == null || degree == null) {
                throw new Exception("Referee parameters are missing");
            }
            if (userDa.get(userName) != null) {
                throw new Exception("User name is already exist in system");
            }
            refereeDa.save(fullName, email, userName, password, degree, refereeRole, new ArrayList<>());
        } else {
            throw new Exception("User is not allowed to register referee");
        }
        return true;
    }

    public void addTeam(String teamId, String teamName, String season, String league) throws Exception {
        if(teamDa.get(teamId) != null) {
            throw new Exception("team is already exist");
        }
        if(teamId == null || teamName == null || season == null || league == null){
            throw new Exception("some parameters are null");
        }
        Team team = new Team(teamId, teamName, season, league);
        teamDa.save(teamId, teamName, team.getExpense(), new ArrayList<>(), league, season);
    }

    public void addGame() {

    }
}