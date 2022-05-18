package Domain;

import java.util.Date;

public class Game {
    private String gameID;
    private Date timeAndDate;
    private String awayTeamName;
    private String homeTeamName;
//    private GameReport gameReport;
    private Stadium stadium;


    //    Team home , Team away
    public Game(String gameId, String homeTeamName, String awayTeamName, Stadium stadium) throws Exception {
        if(gameId == null || homeTeamName == null || awayTeamName == null || stadium == null) {
            throw new Exception("parameters are null");
        }
        this.gameID= gameId;
        this.awayTeamName = awayTeamName;
        this.homeTeamName = homeTeamName;
        this.stadium = stadium;
    }

//    public void addReport(GameReport rep) {
////        if(UserController.getInstance().getLoggedInUser() instanceof Referee){
//            gameReport = rep;
////        }
////        else{
////            System.out.println("Only a referee can add the game report.");
////        }
//    }

    public String getGameID() {
        return gameID;
    }

    public Date getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Date timeAndDate) {
        this.timeAndDate = timeAndDate;
    }
//
    public String getAwayTeamName() {
        return awayTeamName;
    }

//    public void setAwayTeamName(Team awayTeam) {
//        this.awayTeam = awayTeam;
//    }
//
//    public Team getHomeTeam() {
//        return homeTeam;
//    }
//
//    public void setHomeTeam(Team homeTeam) {
//        this.homeTeam = homeTeam;
//    }

//    public GameReport getGameReport() {
//        return gameReport;
//    }

//    public void setGameReport(GameReport gameReport) {
//        this.gameReport = gameReport;
//    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

//    public Team getHomeTeam() {
//        return homeTeam;
//    }

    public Stadium getStadium() {
        return stadium;
    }
}
