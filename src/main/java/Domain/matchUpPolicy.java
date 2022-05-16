package Domain;

import java.util.ArrayList;

public class matchUpPolicy extends Policy {
    String[] matchUps = {"Team can't play against itself", "The teams belongs the same season and same league"};

    public matchUpPolicy() {
    }

    public boolean cantPlayAgainstItself(Game game) throws Exception {
        if (game == null){
            throw new Exception("Game is null");
        }

        if (game.getAwayTeam() == game.getHomeTeam()){
            return false;
        }

        return true;
    }

    public boolean sameSeasonSameLeague(Game game) throws Exception {
        if (game == null){
            throw new Exception("Game is null");
        }

        Team away = game.getAwayTeam();
        Team home = game.getHomeTeam();

        if (away.getSeason() == home.getSeason()){
            if (away.getLeague() == home.getLeague()){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }
}
