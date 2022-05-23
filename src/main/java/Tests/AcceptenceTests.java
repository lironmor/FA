package Tests;

import AccessData.RFADao;
import AccessData.RefereeDao;
import Domain.*;
import Service.ServiceController;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import Service.*;
import org.testng.annotations.BeforeTest;

import java.util.Date;

public class AcceptenceTests {
    ServiceController service;
    Date date;

    @BeforeAll
    public static void Init() throws Exception {
        UserController uc = UserController.getInstance();
        RefereeDao refereeDao = RefereeDao.getInstance();
        RFADao rfaDao = RFADao.getInstance();
        Referee ref = new Referee("Roey Bokobza", "aaa@aaa.com", "Roey", "12345", "main", "expert");
        refereeDao.save(ref, "Roey", "12345");
        rfaDao.save("Liron Mor", "aaa@aaa.com", "Liron", "12345");
        League league = new League("Ligat Ha'Al");
        Season season = new Season(league.getName(), 2021,2022);
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
    }

    @BeforeEach
    public void init() {
        this.service = ServiceController.getInstance();
        this.date = new Date(2021,10,12,20,00);
    }

//    @AfterEach
//    public void resetService() {
//        service = null;
//    }

//    public void resetService() {
//        service.setUc(null);
//    }

    // The user: Liron,12345 is in the system.
    // TODO: insert the user Liron(FA)("Liron","12345") to the system in the INIT() function.
    // TODO: insert a game to system with id 1 --> Maccabi haifa (HOME) VS Hapoel Tel Aviv (Away).
    // TODO: insert the user Roey(Referee)("Roey","12345") to the ststem in the INIT() function
    // TODO: insert the Sdadium "Sami offer" to the system.
    // TODO: insert the stadium "blumfield" to the system.

    @Test
    public void loginTest_Valid() {
        try {
            boolean succsses = service.logIn("Liron", "12345");
            Assertions.assertEquals(true,succsses);
        } catch (Exception e) {
        }
        ;
    }

    @Test
    public void loginTest_NotValid() {
        try {
            boolean succsses = service.logIn("NotExist", "123");
        } catch (Exception e) {
            Assertions.assertEquals("User name or password are wrong", e.getMessage());
        }
    }

    @Test
    public void loginTest_NotValidPass() {
        try {
            boolean succsses = service.logIn("Liron", "NotThePass");
        } catch (Exception e) {
            Assertions.assertEquals("User name or password are wrong", e.getMessage());
        }
    }


    @Test
    public void embedGameTest_Valid() {
        try {
            service.logIn("Liron", "12345");
            boolean succsses = service.embedGame("1", date, "Sami Offer");
            Game game = service.getUc().getGame("1");
            Assertions.assertEquals(game.getGameID(), "1");
            Assertions.assertEquals("Sami Offer", game.getStadium().getName());
            Assertions.assertEquals(date, game.getTimeAndDate());
            Assertions.assertEquals(true,succsses);
        } catch (Exception e) {};


    }

    @Test
    public void embedGameTest_NotValidGame(){
        try{
            service.logIn("Liron", "12345");
            boolean succsses =service.embedGame("NotExist",date,"Sami Offer");
        }catch (Exception e){
            Assertions.assertEquals("game not found",e.getMessage());
        }
    }
    @Test
    public void embedGameTest_NullParams(){
        try{
            service.logIn("Liron", "12345");
            boolean succsses =service.embedGame("1",null,"Sami Offer");
        }catch (Exception e){
            Assertions.assertEquals("Not valid game",e.getMessage());
        }
    }

    @Test
    public void embedGameTest_NotFA(){
        try{
            service.logIn("Roey","12345");
            boolean succsses =service.embedGame("1",date,"Sami Offer");
        }catch(Exception e){
            Assertions.assertEquals("User is not allowed to embed games",e.getMessage());
        }
    }

    @Test
    public void embedGameTest_errorPolicyHomeStadium(){
        try{
            service.logIn("Liron", "12345");
            boolean succsses =service.embedGame("1",date,"Blumfield");

        }catch (Exception e){
            Assertions.assertEquals("Matchup policy error",e.getMessage());
        }
    }

    @Test
    public void embedGameTest_errorPolicyTime(){
        try{
            service.logIn("Liron", "12345");
            boolean succsses =service.embedGame("1",new Date(2021,10,12,23,00),"Sami Offer");
        }catch (Exception e){
            Assertions.assertEquals("Matchup policy error",e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_Valid(){
        try{
            service.logIn("Liron", "12345");
            boolean succsses = service.registerReferee("Eli hacmon","Eli@gmail.com","eli","1234","main","novice","2");
            Assertions.assertEquals(true,succsses);
        }catch (Exception e){
        }
    }

    @Test
    public void registerRefereeTest_notUserLoggedIn() {
        try {
            boolean succsses = service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", "main", "novice", "2");

        } catch (Exception e) {
            Assertions.assertEquals("No user are currently logged in", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_NotFA(){
        try{
            service.logIn("Roey","12345");
            boolean succsses = service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", "main", "novice", "2");
        }catch(Exception e){
            Assertions.assertEquals("User is not allowed to register referee",e.getMessage());
        }

    }
    @Test
    public void registerRefereeTest_notValidRef(){
        try{
            service.logIn("Liron","12345");
            boolean succsses = service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", null, "novice", "2");
        } catch(Exception e){
            Assertions.assertEquals("Not valid referee", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_AlredyExist(){
        try{
            service.logIn("Liron","12345");
            boolean succsses = service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", "main", "novice", "2");

        }catch (Exception e){
            Assertions.assertEquals("user name is alreedy exist in the system",e.getMessage());
        }
    }


}
