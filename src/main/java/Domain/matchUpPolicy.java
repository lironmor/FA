package Domain;

import java.util.Date;

public class matchUpPolicy extends Policy {
    String[] matchUps = {"Game's stadium is home team's stadium", "Valid game time"};

    public matchUpPolicy() {
    }

    public boolean homeTeamStadium(Game game) throws Exception {
        if (game == null)
            return false;

        Stadium team_stadium = game.getHomeTeam().getStadium();

        if (team_stadium == null)
            throw new Exception("Home team must have a stadium");
        else {
            if (team_stadium.getName().equals(game.getStadium().getName()))
                return true;
            else
                return false;
        }
    }

    public boolean validGameTime(Game game){
        if (game == null)
            return false;

        int game_hours = game.getTimeAndDate().getHours();

        if (game_hours < 18){
            return false;
        }
        else if (game_hours > 22) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        Date date = new Date();
        date.setHours(18);
        date.setMinutes(00);

        Date date2 = new Date();
        date2.setHours(22);
        date2.setMinutes(00);

        System.out.println(date.after(date2));
    }






//    public boolean cantPlayAgainstItself(Game game){
//        if (game == null){
//            return false;
//        }
//
//        if (game.getAwayTeam() == game.getHomeTeam()){
//            return false;
//        }
//
//        return true;
//    }
//
//    public boolean sameSeasonSameLeague(Game game){
//        if (game == null){
//            return false;
//        }
//
//        Team away = game.getAwayTeam();
//        Team home = game.getHomeTeam();
//
//        if (away.getSeason() == home.getSeason()){
//            if (away.getLeague() == home.getLeague()){
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//
//        return false;
//    }
}
