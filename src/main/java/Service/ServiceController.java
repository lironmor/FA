//package Service;
//
//import Domain.*;
//
//public class ServiceController {
//    private UserController uc;
//
//    public ServiceController(UserController uc){
//        this.uc = uc;
//    }
//
//    //Login to the system.
//    public void logIn(String userName , String password) throws Exception {
//        boolean result = false;
//        try {
//            result = uc.logIn(userName, password);
//        } catch (Exception e) {
//            throw e;
//        }
//        if (result) {
//            System.out.println("Succsessful login");
//        }
//    }
//
//    public void embedGame(String gameID){
//        boolean result = uc.embedGame(gameID);
//        if(result){
//            System.out.println("Game is succsessfuly set");
//        }
//        else{
//            System.out.println("There is no such game" + gameID);
//        }
//    }
//
//    public void registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree, String id) throws Exception {
//        boolean result = false;
//        try {
//            result = uc.registerReferee(fullName, email, userName, password, refereeRole, degree, id);
//        } catch (Exception e) {
//            throw e;
//        }
//        if(result) {
//            System.out.println("successful register");
//        }
//    }
//
//
//}
//
