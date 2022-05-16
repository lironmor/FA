package Domain;

import java.util.ArrayList;

public class matchUpPolicy extends Policy {
    String[] matchUps = {"Team can't play against itself", "The teams belongs the same season and same league"};

    public matchUpPolicy() {
    }

    public boolean cantPlayAgainstItself(Game game){
        if (game == null){
            return false;
        }

        if (game.getAwayTeam() == game.getHomeTeam()){
            return false;
        }

        return true;
    }

    public boolean sameSeasonSameLeague(Game game){
        if (game == null){
            return false;
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
