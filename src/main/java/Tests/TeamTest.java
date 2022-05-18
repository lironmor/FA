package Tests;

import Domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamTest {
    Stadium std;
    ArrayList<String> seasonNames;

    @BeforeAll
    public void stubsInit() throws Exception {
        //Exception wont be thrown here - creating stubs properly
        std = new Stadium("Tel Aviv", "Blumfield");
        seasonNames = new ArrayList<>();
    }

    @Test
    public void teamConstructor_Valid() {
        try{
            Team team = new Team("Hapoel",std);
            Assertions.assertEquals("Hapoel",team.getTeamName());
            Assertions.assertEquals(std.getName(),team.getStadium().getName());
            Assertions.assertEquals(0,team.getExpense());

        }catch(Exception e){};
    }

    @Test
    public void teamConstructor_nullTeamName(){
        try{
            Team team = new Team(null,std);
        }catch(Exception e){
            Assertions.assertEquals("Not a valid team",e.getMessage());
        }
    }

    @Test
    public void teamConstructor_nullStadium(){
        try{
            Team team = new Team("Hapoel",null);
        }catch(Exception e){
            Assertions.assertEquals("Not a valid team",e.getMessage());
        }
    }

    @Test
    public void addSeason_Valid(){
        try{
            Team team = new Team("Hapoel",std);
            Season season = new Season("Seria A",2020,2021);
            seasonNames.add(season.getId());
            team.addSeasonId(season.getId());
            Assertions.assertEquals(seasonNames,team.getSeasonIds());
            seasonNames.clear();
        }catch (Exception e){};
    }

    @Test
    public void addSeason_seasonAlredyExist(){
        try {
            Team team = new Team("Hapoel", std);
            Season season = new Season("Seria A", 2020, 2021);
            Season season2 = new Season("Seria A",2021,2022);
            //both need to be in the the list since they diffrent seasons.
            seasonNames.add(season.getId());
            seasonNames.add(season2.getId());
            team.addSeasonId(season.getId());
            team.addSeasonId(season2.getId());
            team.addSeasonId(season.getId());
        }catch(Exception e){
            seasonNames.clear();
            Assertions.assertEquals("Season alredy exist",e.getMessage());
        }
    }

    @Test
    public void addSeason_nullParameters(){
        try{
            Team team = new Team("Hapoel",std);
            team.addSeasonId(null);
        }catch(Exception e){
            Assertions.assertEquals("Not a valid season",e.getMessage());
        }
    }

}
