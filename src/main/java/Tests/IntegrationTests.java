package Tests;

import Domain.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTests {
    UserController uc = UserController.getInstance();


    @Test
    public void userControllerGameTeam(){
        UserController uc = UserController.getInstance();
        try {
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
            Game game = new Game("2", team, team1);
            uc.addGame(game);
            Game g = uc.getGame(game.getGameID());
            Assertions.assertTrue(game.getGameID() == g.getGameID());
        }catch(Exception e){};
    }
//
//    @Test
//    public void UserControllerNullGame() {
//        try {
//            uc.addGame(null);
//        } catch (Exception e) {
//            Assertions.assertEquals("Game is not valid", e.getMessage());
//        }
//        Assertions.assertEquals(true, false);
//    }
//
//    @Test
//    public void UserControllerNullLeague() {
//        try {
//            uc.addLeague(null);
//        } catch (Exception e) {
//            Assertions.assertEquals("League is not valid", e.getMessage());
//        }
//        Assertions.assertThrows(Exception,)
//    }
//        }
//        try{
//            uc.addSeason(null);
//        } catch (Exception e) {
//            Assertions.assertEquals("Season is not valid", e.getMessage());
//        }
//        try{
//            uc.addStadium(null);
//        } catch (Exception e) {
//            Assertions.assertEquals("Stadium is not valid", e.getMessage());
//        }
//        try{
//            uc.addTeam(null);
//        } catch (Exception e) {
//            Assertions.assertEquals("Team is not valid", e.getMessage());
//        }
////    }
//}
