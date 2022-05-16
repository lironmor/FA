package Tests;

import Domain.Game;
import Domain.Stadium;
import Domain.Team;
import Domain.matchUpPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class matchUpPolicyTest {

    @Test
    public void cantPlayAgainstItself_sameTeam(){
        Team team = new Team("1", "Macabi TLV", "2021-2022", "Israeli Premier League");
        Stadium stadium = new Stadium("TLV");
        Game game = new Game("1", new Date(), team, team, stadium);
        matchUpPolicy matchUp = new matchUpPolicy();
        Assertions.assertEquals(false, matchUp.cantPlayAgainstItself(game));
    }

    @Test
    public void cantPlayAgainstItself_diffTeam(){
        Team team1 = new Team("1", "Macabi TLV", "2021-2022", "Israeli Premier League");
        Team team2 = new Team("2", "Macabi Haifa", "2021-2022", "Israeli Premier League");
        Stadium stadium = new Stadium("TLV");
        Game game = new Game("1", new Date(), team1, team2, stadium);
        matchUpPolicy matchUp = new matchUpPolicy();
        Assertions.assertEquals(true, matchUp.cantPlayAgainstItself(game));
    }

    @Test
    public void sameSeasonSameLeague_same(){
        Team team1 = new Team("1", "Macabi TLV", "2021-2022", "Israeli Premier League");
        Team team2 = new Team("2", "Macabi Haifa", "2021-2022", "Israeli Premier League");
        Stadium stadium = new Stadium("TLV");
        Game game = new Game("1", new Date(), team1, team2, stadium);
        matchUpPolicy matchUp = new matchUpPolicy();
        Assertions.assertEquals(true, game);
    }

    @Test
    public void sameSeasonSameLeague_diffSeasonSameLeague(){
        Team team1 = new Team("1", "Macabi TLV", "2020-2021", "Israeli Premier League");
        Team team2 = new Team("2", "Macabi Haifa", "2021-2022", "Israeli Premier League");
        Stadium stadium = new Stadium("TLV");
        Game game = new Game("1", new Date(), team1, team2, stadium);
        matchUpPolicy matchUp = new matchUpPolicy();
        Assertions.assertEquals(false, game);
    }

    @Test
    public void sameSeasonSameLeague_diffSeasonDiffLeague(){
        Team team1 = new Team("1", "Macabi TLV", "2020-2021", "Liga Leumit");
        Team team2 = new Team("2", "Macabi Haifa", "2021-2022", "Israeli Premier League");
        Stadium stadium = new Stadium("TLV");
        Game game = new Game("1", new Date(), team1, team2, stadium);
        matchUpPolicy matchUp = new matchUpPolicy();
        Assertions.assertEquals(false, game);
    }

    @Test
    public void sameSeasonSameLeague_sameSeasonDiffLeague(){
        Team team1 = new Team("1", "Macabi TLV", "2021-2022", "Liga Leumit");
        Team team2 = new Team("2", "Macabi Haifa", "2021-2022", "Israeli Premier League");
        Stadium stadium = new Stadium("TLV");
        Game game = new Game("1", new Date(), team1, team2, stadium);
        matchUpPolicy matchUp = new matchUpPolicy();
        Assertions.assertEquals(false, game);
    }
}
