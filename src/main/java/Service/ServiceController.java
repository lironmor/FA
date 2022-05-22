package Service;

import Domain.*;

import java.util.Date;

public class ServiceController {
    private UserController uc;
    private static ServiceController instance = null;

    private ServiceController(){
        this.uc = UserController.getInstance();
    }

    public static ServiceController getInstance(){
        if(instance == null){
            instance = new ServiceController();
        }
        return instance;
    }

    //Login to the system.
    public boolean logIn(String userName , String password) throws Exception {
        return uc.logIn(userName, password);
//        boolean result = false;
//            result = uc.logIn(userName, password);
//        if (result) {
//            System.out.println("Succsessful login");
//        }
    }

    public boolean embedGame(String gameID, Date time, String stadiumName) throws Exception{
        return uc.embedGame(gameID, time, stadiumName);
//        boolean result = uc.embedGame(gameID, time, stadiumName);
//        if(result){
//            System.out.println("Game is succsessfuly set");
//        }
//        else{
//            System.out.println("There is no such game" + gameID);
//        }
    }

    public boolean registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree, String id) throws Exception {
        return uc.registerReferee(fullName, email, userName, password, refereeRole, degree, id);
//        boolean result = false;
//        try {
//            result = uc.registerReferee(fullName, email, userName, password, refereeRole, degree, id);
//        } catch (Exception e) {
//            throw e;
//        }
//        if(result) {
//            System.out.println("successful register");
//        }
    }

    public UserController getUc() {
        return uc;
    }
    //ONLY FOR TEST PURPOSE.
    public void setUc(UserController uc) {
        this.uc = uc;
    }
}

