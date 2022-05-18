package Tests;

import Domain.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StadiumTest {

    @Test
    public void stadiumConstructor(){
        try{
            Stadium stadium = new Stadium("Here");
            Assertions.assertEquals("Here", stadium.getLocation());
        }
        catch(Exception e){
            //TODO:EXPECTED MESSAGE
            Assertions.assertEquals("", e.getMessage());
        }
    }
}
