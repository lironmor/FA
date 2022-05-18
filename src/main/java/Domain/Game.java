package Domain;

import java.util.Date;

public class Game {
    private String gameID;
    private Date timeAndDate;
    private Team awayTeam;
    private Team homeTeam;
    private Stadium stadium;


    //    Team home , Team away
    public Game(String gameId, Team homeTeam, Team awayTeam) throws Exception {
        if(gameId == null || homeTeam == null || awayTeam == null) {
            throw new Exception("Not a valid game parameters");
        }
        this.gameID = gameId;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
    }

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
    public Team getAwayTeam() {
        return awayTeam;
    }


    public Team getHomeTeam() {
        return homeTeam;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
