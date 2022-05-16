package Domain;

import java.util.Date;

public class Game {
    private String gameID;
    private Date timeAndDate;
    private Team awayTeam;
    private Team homeTeam;
    private GameReport gameReport;
    private Stadium stadium;


    //    Team home , Team away
    public Game(String id, Date time, Team home, Team away, Stadium stadium){
        gameID= id;
        timeAndDate = time;
        awayTeam = away;
        homeTeam = home;
        this.stadium = stadium;
    }

    public void addReport(GameReport rep) {
        gameReport = rep;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public Date getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Date timeAndDate) {
        this.timeAndDate = timeAndDate;
    }
//
//    public Team getAwayTeam() {
//        return awayTeam;
//    }
//
//    public void setAwayTeam(Team awayTeam) {
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

    public GameReport getGameReport() {
        return gameReport;
    }

    public void setGameReport(GameReport gameReport) {
        this.gameReport = gameReport;
    }
}
