package Tests;

import Domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTests {
    UserController uc;

    @BeforeEach
    public void init() {

        uc = UserController.getInstance();
    }

    @Test
    public void userControllerGameTeamStadium_Valid() {
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
        } catch (Exception e) {
            e.printStackTrace();
        };

    }


    @Test
    public void UserControllerNullGame() {
        try {
            uc.addGame(null);
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("game is not valid", e.getMessage());
        }
    }


    @Test
    public void UserControllerNullLeague() {
        try {
            uc.addLeague(null);
            Assertions.assertThrows(Exception.class, () -> {});
        } catch (Exception e) {
            Assertions.assertEquals("League is not valid", e.getMessage());
        }
    }
    @Test
    public void UserControllerNullSeason(){
        try{
            uc.addSeason(null);
        } catch (Exception e) {
           Assertions.assertEquals("Season is not valid", e.getMessage());
        }
    }

    @Test
    public void UserControllerNullStadium(){
        try{
            uc.addStadium(null);
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("Stadium is not valid" ,e.getMessage());
        }
    }

    @Test
    public void UserControllerNullTeam(){
        try{
            uc.addTeam(null);
            Assertions.assertThrows(Exception.class, () -> {});
        } catch(Exception e){
            Assertions.assertEquals("Team is not valid" , e.getMessage());
        }
    }

    @Test
    public void UserControllerGetGame_NotValid(){
        try{
            uc.getGame("Not Exist");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("game not found", e.getMessage());
        }
    }

}
