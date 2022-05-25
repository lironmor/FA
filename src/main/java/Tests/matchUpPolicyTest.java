package Tests;

import Domain.Game;
import Domain.*;
import Domain.matchUpPolicy;
import org.junit.jupiter.api.*;

import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class matchUpPolicyTest {
    Stadium std1;
    Stadium std2;
    Team teamHome;
    Team teamAway;
    Game validG;
    Game notValidG;
    matchUpPolicy policy;
    @BeforeEach
    public  void stubsInit() throws Exception {
        // Exception wont be thrown here - stubs created properly.
        std1 = new Stadium("Tel aviv", "Blumfield");
        std2 = new Stadium("Haifa", "Sami Ofer");
        teamHome = new Team("Hapoel", std1);
        teamAway = new Team("Maccabi", std2);
        validG = new Game("3",teamHome,teamAway);
        validG.setStadium(std1);
        notValidG = new Game("2",teamHome,teamAway);
        notValidG.setStadium(std2);
        policy = new matchUpPolicy();
    }

    @Test
    public void homeTeamStadium_valid(){
        try {
            Assertions.assertTrue( policy.homeTeamStadium(validG));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void homeTeamStadium_notValid() {
        System.out.println(notValidG+ "2");
        try{
            boolean result = policy.homeTeamStadium(notValidG);
            Assertions.assertFalse(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void homeTeamStadium_nullGame(){
        System.out.println(notValidG+ "3");
        try{
            policy.homeTeamStadium(null);
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e) {
            Assertions.assertEquals("Not a valid game", e.getMessage());
        }
    }

    @Test
    public void validGameTime_EarlyHour(){
        System.out.println(notValidG);
        notValidG.setTimeAndDate(new Date(120,10,18,14,30));
        try{
            boolean result = policy.validGameTime(notValidG);
            Assertions.assertFalse(result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void validGameTime_LateHour(){
        notValidG.setTimeAndDate(new Date(120,10,18,23,00));
        try{
            boolean result = policy.validGameTime(notValidG);
            Assertions.assertFalse(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void validGameTime_valid(){
        System.out.println(notValidG+ "5");
        validG.setTimeAndDate(new Date(120,10,18,20,30));
        try {
            boolean result = policy.validGameTime(validG);
            Assertions.assertTrue(result);
        }catch (Exception e){
            e.printStackTrace();
        };
    }

    @Test
    public void validGameTime_nullGame(){
        notValidG = null;
        try{
            policy.validGameTime(notValidG);
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("Not a valid game",e.getMessage());
        }
    }



}
