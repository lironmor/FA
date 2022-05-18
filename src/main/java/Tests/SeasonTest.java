package Tests;

import Domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeasonTest {
    Team team;
    Stadium std;
    @BeforeAll
    public void stubsInit() throws Exception {
        //Exception wont be thrown here - creating stubs properly
        std = new Stadium("Tel Aviv","Blumfield");
        team = new Team("Hapoel",std);
    }

    @Test
    public void seasonConstructor_valid(){
        try{
            Season season = new Season("Seria A",2020,2021);
            Assertions.assertEquals("Seria A",season.getLeagueName());
            Assertions.assertEquals(2020,season.getStartYear());
            Assertions.assertEquals(2021,season.getEndYear());
            Assertions.assertEquals("Seria A 2020/2021",season.getId());
        }
        catch(Exception e){};
    }

    @Test
    public void seasonConstructor_nullLeague(){
        try{
            Season season = new Season(null,2020,2021);
        }catch(Exception e){
            Assertions.assertEquals("Not a valid season",e.getMessage());
        }
    }

    @Test
    public void seasonConstructor_notValid(){
        try{
            Season season = new Season(null,2023,2021);
        }catch(Exception e){
            Assertions.assertEquals("Not a valid season",e.getMessage());
        }

    }

    @Test
    public void addTeamsToSeason_Valid(){
        try{
            ArrayList<String> teamNames = new ArrayList<String>();
            teamNames.add(team.getTeamName());
            Season season = new Season("Seria A",2020,2021);
            season.addTeamName(team.getTeamName());
            Assertions.assertEquals(teamNames,season.getTeamNames());
        } catch(Exception e){};
    }

    @Test
    public void addTeamsToSeason_teamAlredyExist(){
        try{
            ArrayList<String> teamNames = new ArrayList<String>();
            teamNames.add(team.getTeamName());
            Season season = new Season("Seria A",2020,2021);
            season.addTeamName(team.getTeamName());
            season.addTeamName(team.getTeamName());
        }catch(Exception e){
            Assertions.assertEquals("Team is already exist in the season",e.getMessage());
        }
    }

    @Test
    public void addTeamsToSeason_nullTeam() {
        try {
            Season season = new Season("Seria A", 2020, 2021);
            season.addTeamName(null);
        } catch (Exception e) {
            Assertions.assertEquals("Not a valid team", e.getMessage());
        }
    }

}
