package Tests;

import Domain.Season;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SeasonTest {

    @Test
    public void seasonConstructor(){
        try{
            Season season = new Season("01/01/2022", "31/12/2022");
            Assertions.assertEquals("01/01/2022", season.getStartDate());
            Assertions.assertEquals("31/12/2022", season.getEndDate());
        }
        catch(Exception e){
            //TODO:EXPECTED MESSAGE
            Assertions.assertEquals("", e.getMessage());
        }
    }
}
