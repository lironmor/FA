package Tests;

import Domain.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StadiumTest {

    @Test
    public void stadiumConstructor_valid(){
        try{
            Stadium stadium = new Stadium("Here","name");
            Assertions.assertEquals("Here", stadium.getLocation());
            Assertions.assertEquals("name", stadium.getName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void stadiumConstructor_notValidLocation(){
        try{
            new Stadium(null,"name");
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("Not a valid stadium",e.getMessage());
        }
    }

    @Test
    public void stadiumConstructor_notValidName(){
        try{
            new Stadium("here",null);
            Assertions.assertThrows(Exception.class, () -> {});
        }catch(Exception e){
            Assertions.assertEquals("Not a valid stadium",e.getMessage());
        }
    }


}
