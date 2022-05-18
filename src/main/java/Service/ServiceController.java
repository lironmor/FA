package Service;

import Domain.*;

import java.util.Date;

public class ServiceController {
    private UserController uc;
    private static ServiceController instance = null;

    private ServiceController(){
        this.uc = UserController.getInstance();
    }

    public ServiceController getInstance(){
        if(instance == null){
            instance = new ServiceController();
        }
        return instance;
    }

    //Login to the system.
    public void logIn(String userName , String password) throws Exception {
        uc.logIn(userName, password);
//        boolean result = false;
//            result = uc.logIn(userName, password);
//        if (result) {
//            System.out.println("Succsessful login");
//        }
    }

    public void embedGame(String gameID, Date time, String stadiumName) throws Exception{
        uc.embedGame(gameID, time, stadiumName);
//        boolean result = uc.embedGame(gameID, time, stadiumName);
//        if(result){
//            System.out.println("Game is succsessfuly set");
//        }
//        else{
//            System.out.println("There is no such game" + gameID);
//        }
    }

    public void registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree, String id) throws Exception {
        uc.registerReferee(fullName, email, userName, password, refereeRole, degree, id);
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


}

