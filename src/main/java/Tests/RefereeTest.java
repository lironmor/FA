//package Tests;
//
//import Domain.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//public class RefereeTest {
//
//    @Test
//    public void refereeConstructor_good() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "novice", new ArrayList<>());
//        Assertions.assertEquals("Roey Bokobza", referee.getFullName());
//        Assertions.assertEquals("roey@gmail.com", referee.getEmail());
//        Assertions.assertEquals("roeyboko", referee.getUserName());
//        Assertions.assertEquals("1234", referee.getPassword());
//        Assertions.assertEquals("main", referee.getRefereeRole());
//        Assertions.assertEquals("novice", referee.getDegree());
//        Assertions.assertEquals(new ArrayList<>(), referee.getComingUp());
//    }
//    @Test
//    public void refereeConstructor_ErrorDegree()  {
//        try {
//            Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "Not a Degree", new ArrayList<>());
//        }
//        catch(Exception e){
//            Assertions.assertEquals("Referee degree must be expert/veteran/novice !",e.getMessage());
//        }
//    }
//    @Test
//    public void refereeConstructor_ErrorRole()  {
//        try {
//            Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "Not a role", "novice", new ArrayList<>());
//        }
//        catch(Exception e){
//            Assertions.assertEquals("Referee roll must be main/sideline/forth !",e.getMessage());
//        }
//    }
//
//    @Test
//    public void refereeConstructor_setRoleToSideLine() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<>());
//        referee.roleChoose("side line");
//        Assertions.assertEquals("sideline", referee.getRefereeRole());
//    }
//
//    @Test
//    public void refereeConstructor_setRoleToMaxim() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<>());
//        referee.roleChoose("maxim the maxim");
//        Assertions.assertNull( referee.getRefereeRole());
//
//    }
//
//    @Test
//    public void refereeConstructor_setDegreeToVeteran() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<Game>());
//        referee.degreeChoose("veteran");
//        Assertions.assertEquals("veteran", referee.getDegree());
//    }
//
//    @Test
//    public void refereeConstructor_ChangeDegreeToExpert() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "novice", new ArrayList<Game>());
//        referee.degreeChoose("expert");
//        Assertions.assertEquals("expert", referee.getDegree());
//    }
//
//    @Test
//    public void refereeConstructor_setDegreeToMaxim() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<Game>());
//        referee.degreeChoose("maxim the maxim");
//        Assertions.assertNull(referee.getDegree());
//    }
//
//    @Test
//    public void makeGameReport_good() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<Game>());
//        Team t1 = new Team("2", "Maccabi Haifa", "2021/2022", "Israeli");
//        Team t2 = new Team("3", "Hapoel Tel-Aviv", "2021/2022", "Israeli");
//        Stadium std = new Stadium("Haifa");
//        Game g = new Game("1", new Date(), t1, t2, std);
//        referee.makeGameReport(g);
//        //just for checking we'll add an event
//        referee.addEventToReport(g, new Date(), 12, "red card for xavi", "red card");
//        Assertions.assertEquals(12, g.getGameReport().getReport().get(0).getMinuteInGame());
//        Assertions.assertEquals("red card for xavi", g.getGameReport().getReport().get(0).getDescription());
//    }
//
//    @Test
//    public void addEventToReport_good() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<Game>());
//        GameReport gr = new GameReport();
//        //creating a game
//        Date date = new Date();
//        Team t1 = new Team("2", "Maccabi Haifa", "2021/2022", "Israeli");
//        Team t2 = new Team("3", "Hapoel Tel-Aviv", "2021/2022", "Israeli");
//        Stadium std = new Stadium("Haifa");
//        Game g = new Game("1", new Date(), t1, t2, std);
//        g.addReport(gr);
//        referee.addEventToReport(g, date, 31, "yellow card to xavi", "yellow card");
//
//        Assertions.assertEquals(date, g.getGameReport().getReport().get(0).getDate());
//        Assertions.assertEquals("yellow card to xavi", g.getGameReport().getReport().get(0).getDescription());
//        Assertions.assertEquals(31, g.getGameReport().getReport().get(0).getMinuteInGame());
//    }
//
//    @Test
//    public void addEventToReport_ErrorType() throws Exception {
//        Referee referee = new Referee("Roey Bokobza", "roey@gmail.com", "roeyboko", "1234", "main", "expert", new ArrayList<Game>());
//        GameReport gr = new GameReport();
//        //creating a game
//        Date date = new Date();
//        Team t1 = new Team("2", "Maccabi Haifa", "2021/2022", "Israeli");
//        Team t2 = new Team("3", "Hapoel Tel-Aviv", "2021/2022", "Israeli");
//        Stadium std = new Stadium("Haifa");
//        Game g = new Game("1", new Date(), t1, t2, std);
//        //Check if an Excetion was thrown as it should.
//        try{
//            referee.addEventToReport(g, date, 31, "yellow card to xavi", "Not a Type");
//        }
//        catch (Exception e){
//            Assertions.assertEquals("Event type must be goal/foul/offside/injury/substitution/yellowCard/redCard",e.getMessage());
//        }
//    }
//
//}
//
