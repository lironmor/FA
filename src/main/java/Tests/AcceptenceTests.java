package Tests;

import AccessData.RFADao;
import AccessData.RefereeDao;
import Domain.*;
import Service.ServiceController;
import org.junit.jupiter.api.*;


import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcceptenceTests {
    ServiceController service = ServiceController.getInstance();
    Date date = new Date(121,10,12,20,00);

    @BeforeAll
    public static void init() {
        try {
            UserController uc = UserController.getInstance();
            RefereeDao refereeDao = RefereeDao.getInstance();
            RFADao rfaDao = RFADao.getInstance();
            Referee ref = new Referee("Roey Bokobza", "roey@gmail.com", "Roey", "12345", "main", "expert");
            refereeDao.save(ref, "Roey", "12345", "roey@gmail.com");
            RFA rfa = new RFA("Liron Mor", "liron@gmail.com", "Liron", "12345");
            rfaDao.save(rfa, "Liron", "12345", "liron@gmail.com");
            League league = new League("Ligat Ha'Al");
            Season season = new Season(league.getName(), 2021, 2022);
            league.addSeasonId(season.getId());

            Stadium samiOffer = new Stadium("Haifa", "Sami Offer");
            uc.addStadium(samiOffer);

            Stadium blumfield = new Stadium("Tel-Aviv", "Blumfield");
            uc.addStadium(blumfield);

            Team team = new Team("Maccabi Haifa", samiOffer);
            Team team1 = new Team("Hapoel Tel Aviv", blumfield);
            team.setExpense(1000);
            team1.setExpense(100);
            uc.addTeam(team);
            uc.addTeam(team1);

            season.addTeamName(team.getTeamName());
            season.addTeamName(team1.getTeamName());

            Game game = new Game("1", team, team1);
            game.setStadium(samiOffer);
            season.addGame(game);

            uc.addSeason(season);
            uc.addLeague(league);
            uc.addGame(game);
        } catch (Exception e) {}
    }

    @AfterEach
    public void afterEach() {
        service.logOut();
    }

    @AfterAll
    public void afterAll() {
        RefereeDao refereeDao = RefereeDao.getInstance();
        refereeDao.deleteOne("eli");
    }

    @Test
    public void loginTest_Valid() {
        try {
            boolean succsses = service.logIn("Liron", "12345");
            Assertions.assertEquals(true,succsses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest_NotValid() {
        try {
            service.logIn("NotExist", "123");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("User name or password are wrong", e.getMessage());
        }
    }

    @Test
    public void loginTest_NotValidPass() {
        try {
            service.logIn("Liron", "NotThePass");
            Assertions.assertThrows(Exception.class, () -> {});
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
        } catch (Exception e) {
            e.printStackTrace();
        };


    }

    @Test
    public void embedGameTest_NotValidGame(){
        try{
            service.logIn("Liron", "12345");
            service.embedGame("NotExist", date,"Sami Offer");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch (Exception e){
            Assertions.assertEquals("game not found",e.getMessage());
        }
    }
    @Test
    public void embedGameTest_NullParams(){
        try{
            service.logIn("Liron", "12345");
            service.embedGame("1",null,"Sami Offer");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch (Exception e){
            Assertions.assertEquals("Not valid game",e.getMessage());
        }
    }

    @Test
    public void embedGameTest_NotFA(){
        try{
            service.logIn("Roey","12345");
            service.embedGame("1",date,"Sami Offer");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("User is not allowed to embed games",e.getMessage());
        }
    }

    @Test
    public void embedGameTest_errorPolicyHomeStadium(){
        try{
            service.logIn("Liron", "12345");
            service.embedGame("1", date,"Blumfield");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch (Exception e){
            Assertions.assertEquals("Matchup policy error",e.getMessage());
        }
    }

    @Test
    public void embedGameTest_errorPolicyTime(){
        try{
            service.logIn("Liron", "12345");
            service.embedGame("1",new Date(121,10,12,23,00),"Sami Offer");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch (Exception e){
            Assertions.assertEquals("Matchup policy error",e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_Valid(){
        try{
            service.logIn("Liron", "12345");
            boolean succsses = service.registerReferee("Eli hacmon","Eli@gmail.com","eli","3abcdsdf","main","novice","2");
            Assertions.assertEquals(true,succsses);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void registerRefereeTest_notUserLoggedIn() {
        try {
            service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("No user are currently logged in", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_notValidPassword1() {
        try {
            service.logIn("Liron", "12345");
            service.registerReferee("Lior hagag", "lior@gmail.com", "lior", "123456", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("the password must contain at least 6 chars and at least 1 number", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_notValidPassword2() {
        try {
            service.logIn("Liron", "12345");
            service.registerReferee("meir hagag", "meir@gmail.com", "meir", "password", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("the password must contain at least 6 chars and at least 1 number", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_notValidPassword3() {
        try {
            service.logIn("Liron", "12345");
            service.registerReferee("Shlomi hagag", "Shlomi@gmail.com", "Shlomi", "pas12", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("the password must contain at least 6 chars and at least 1 number", e.getMessage());
        }
    }


    @Test
    public void registerRefereeTest_emailAlredyExist() {
        try {
            service.logIn("Liron", "12345");
            service.registerReferee("Liron Buhnik", "liron@gmail.com", "Lir", "1234", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("Email is alredy exist in the system", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_NotFA(){
        try{
            service.logIn("Roey","12345");
            service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("User is not allowed to register referee",e.getMessage());
        }

    }
    @Test
    public void registerRefereeTest_notValidRef(){
        try{
            service.logIn("Liron","12345");
            service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", null, "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        } catch(Exception e){
            Assertions.assertEquals("Not valid referee", e.getMessage());
        }
    }

    @Test
    public void registerRefereeTest_AlredyExist(){
        try{
            service.logIn("Liron","12345");
            service.registerReferee("Eli hacmon", "Eli@gmail.com", "eli", "1234", "main", "novice", "2");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch (Exception e){
            Assertions.assertEquals("User name is alredy exist in the system",e.getMessage());
        }
    }


}
