package Service;

import Domain.*;

public class ServiceController {
    private UserController uc;

    public ServiceController(UserController uc){
        this.uc = uc;
    }

    //Login to the system.
    public void logIn(String userName , String password) {
        int result = uc.logIn(userName, password);
        if (result == 0) {
            System.out.println("Succsessful login");
        } else if (result == 1) {
            System.out.println("User name or password are incorrect");
        } else {
            System.out.println("You are alredy logged in to the system as "+ uc.getLoggedInUser().getUserName());
        }
    }

    public void embedGame(String gameID){
        boolean result = uc.embedGame(gameID);
        if(result){
            System.out.println("Game is succsessfuly set");
        }
        else{
            System.out.println("There is no such game" + gameID);
        }
    }

    public void registerReferee(String fullName, String email, String userName, String password, String refereeRole, String degree){
        boolean result = uc.registerReferee(fullName,email,userName,password,refereeRole,degree);

    }


}

