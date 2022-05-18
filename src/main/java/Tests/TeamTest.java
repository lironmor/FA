package Tests;

import Domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TeamTest {

    @Test
    public void teamConstructor(){
        try{
            Team team = new Team("1", "Macabi", "2021-2022", "league");
            Assertions.assertEquals("1", team.getTeamID());
            Assertions.assertEquals("Macabi", team.getTeamName());
            Assertions.assertEquals("2021-2022", team.getSeason());
            Assertions.assertEquals("league", team.getLeague());
        }
        catch (Exception e){
            //TODO:EXPECTED MESSAGE
            Assertions.assertEquals("", e.getMessage());
        }
    }
}
