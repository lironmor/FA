package Tests;

import Domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;

import java.rmi.server.ExportException;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {
    Stadium std1;
    Stadium std2;
    Team teamHome;
    Team teamAway;
    @BeforeAll
    public void stubsInit() throws Exception {
        // Exception wont be thrown here - stubs created properly.
        std1 = new Stadium("Tel aviv","Blumfield");
        std2 = new Stadium("Haifa","Sami Ofer");
        teamHome = new Team("Hapoel",std1);
        teamAway = new Team("Maccabi",std2);
    }
    @Test
    public void constructorTest_valid()  {
        //Using the constructor
        try {
            Game g = new Game("4", teamHome, teamAway);
            //check that all values of game are propelry created.
            Assertions.assertEquals("Hapoel", g.getHomeTeam().getTeamName());
            Assertions.assertEquals("Maccabi", g.getAwayTeam().getTeamName());
            Assertions.assertEquals("Tel aviv", g.getStadium().getLocation());

        }catch (Exception e){};
        }

    @Test
    public void constructorTest_NotValidTeam() {
        try {
            Game g = new Game("12", null, teamAway);
        } catch (Exception e) {
            Assertions.assertEquals("Not a valid game parameters", e.getMessage());
        }
    }

}


