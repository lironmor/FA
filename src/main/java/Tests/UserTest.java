package Tests;

import Domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void userConstructor(){
        try{
            User user = new User("R B", "roey@gmail.com", "rb", "1234");
            Assertions.assertEquals("R B", user.getFullName());
            Assertions.assertEquals("roey@gmail.com", user.getEmail());
            Assertions.assertEquals("rb", user.getUserName());
            Assertions.assertEquals("1234", user.getPassword());
        }
        catch (Exception e){
            //TODO:EXPECTED MESSAGE
            Assertions.assertEquals("", e.getMessage());
        }

    }
}
