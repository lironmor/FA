package Tests;

import Domain.League;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeagueTest {
    @Test
    public void leagueConstructor_Valid() {
        try {
            League league = new League("Seria A");
            Assertions.assertEquals("Seria A", league.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void leagueConstructor_NotValidName(){
        try {
            new League(null);
            Assertions.assertThrows(Exception.class, () -> {});
        }catch (Exception e){
            Assertions.assertEquals("Not a valid name",e.getMessage());
        }
    }

}
