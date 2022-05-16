package Domain;
import AccessData.*;

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

public class UserController{
    private User loggedInUser;
    public static UserController instance;
    // SHTUYOT SHEL LIRON

    // SINGLETON !!
    private UserController(){
        this.loggedInUser = null;
    }
    public static UserController getInstance(){
        if(instance == null){
            instance = new UserController();
        }
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public int logIn(String userName , String password){
        boolean success = false;
        // SHTUYOT SHEL LIRON... if()...== ()
        //if(setLoggedIn(User u) == true --> HACOL TOV , Else --> "You are alredy logged in as" + loggedInUser.getUserName();
        success =  true;
        return success;

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

    public boolean registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree){
        //Shtoyout SHEL LIRON
        //SEND TO DB
        return true;
    }
}