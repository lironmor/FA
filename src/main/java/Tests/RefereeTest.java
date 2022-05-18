package Tests;

import Domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RefereeTest {

    @Test
    public void refereeConstructor_good() throws Exception {
        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "novice");
        Assertions.assertEquals("Roey Bokobza", referee.getFullName());
        Assertions.assertEquals("roey@gmail.com", referee.getEmail());
        Assertions.assertEquals("roeyboko", referee.getUserName());
        Assertions.assertEquals("1234", referee.getPassword());
        Assertions.assertEquals("main", referee.getRefereeRole());
        Assertions.assertEquals("novice", referee.getDegree());
    }
    @Test
    public void refereeConstructor_ErrorDegree()  {
        try {
            Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "Not a Degree");
        }
        catch(Exception e){
            Assertions.assertEquals("Referee degree must be expert/veteran/novice !",e.getMessage());
        }
    }
    @Test
    public void refereeConstructor_ErrorRole()  {
        try {
            Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "Not a role", "novice");
        }
        catch(Exception e){
            Assertions.assertEquals("Referee roll must be main/sideline/forth !",e.getMessage());
        }
    }

    @Test
    public void refereeConstructor_setRoleToSideLine() throws Exception {
        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert");
        referee.roleChoose("side line");
        Assertions.assertEquals("sideline", referee.getRefereeRole());
    }

    @Test
    public void refereeConstructor_setRoleToMaxim() throws Exception {
        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert");
        referee.roleChoose("maxim the maxim");
        Assertions.assertNull( referee.getRefereeRole());

    }

    @Test
    public void refereeConstructor_setDegreeToVeteran() throws Exception {
        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert");
        referee.degreeChoose("veteran");
        Assertions.assertEquals("veteran", referee.getDegree());
    }

    @Test
    public void refereeConstructor_ChangeDegreeToExpert() throws Exception {
        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "novice");
        referee.degreeChoose("expert");
        Assertions.assertEquals("expert", referee.getDegree());
    }

    @Test
    public void refereeConstructor_setDegreeToMaxim() throws Exception {
        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert");
        referee.degreeChoose("maxim the maxim");
        Assertions.assertNull(referee.getDegree());
    }

}

