package Tests;

import Domain.League;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeagueTest {
    @Test
    public void leagueConstructor(){
        try{
            League league = new League(1);
            Assertions.assertEquals(1, league.getId());
        }
        catch (Exception e){
            //TODO:EXPECTED MESSAGE
            Assertions.assertEquals("", e.getMessage());
        }
    }
}
