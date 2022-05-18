package Tests;

import Domain.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

public class StadiumTest {

    @Test
    public void stadiumConstructor_valid(){
        try{
            Stadium stadium = new Stadium("Here","name");
            Assertions.assertEquals("Here", stadium.getLocation());
            Assertions.assertEquals("name", stadium.getName());
        }catch(Exception e){};
    }

    @Test
    public void stadiumConstructor_notValidLocation(){
        try{
            Stadium std = new Stadium(null,"name");
        }catch(Exception e){
            Assertions.assertEquals("Not a valid stadium",e.getMessage());
        }
    }

    @Test
    public void stadiumConstructor_notValidName(){
        try{
            Stadium std = new Stadium("here",null);
        }catch(Exception e){
            Assertions.assertEquals("Not a valid stadium",e.getMessage());
        }
    }


}
