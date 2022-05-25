package Domain;

import java.util.Date;

public class matchUpPolicy extends Policy {
    String[] matchUps = {"Game's stadium is home team's stadium", "Valid game time"};

    public matchUpPolicy() {
    }

    public boolean homeTeamStadium(Game game) throws Exception {
        if (game == null)
            throw new Exception("Not a valid game");

        Stadium team_stadium = game.getHomeTeam().getStadium();

        if (team_stadium == null)
            throw new Exception("Not a valid game");
        else {
            if (team_stadium.getName().equals(game.getStadium().getName()))
                return true;
            else
                return false;
        }
    }

    public boolean validGameTime(Game game) throws Exception {
        if (game == null)
            throw new Exception("Not a valid game");

        int game_hours = game.getTimeAndDate().getHours();

        if (game_hours < 18){
            return false;
        }
        else if (game_hours > 22) {
            return false;
        }

        return true;
    }
}
