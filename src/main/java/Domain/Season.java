package Domain;
import com.google.common.primitives.UnsignedInteger;

import java.util.ArrayList;
import java.util.Date;

public class Season {
    private Integer startYear;
    private Integer endYear;
    private ArrayList<String> teamNames;
    private String leagueName;
    private ArrayList<String> gameIds;
    private String id;

    public Season(String leagueName, Integer startYear, Integer endYear) throws Exception {
        this.leagueName = leagueName;
        if(endYear - startYear != 1 || startYear >= 2022){
            throw new Exception("Not a valid season");
        }
        if(leagueName == null) {
            throw new Exception("Not a valid season");
        }
        this.id = leagueName + " " + startYear + "/"+ endYear;
        this.teamNames = new ArrayList<>();
        this.gameIds = new ArrayList<>();
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public ArrayList<String> getTeamNames() {
        return teamNames;
    }

    public void addTeamName(String teamName) throws Exception {
        if(teamName == null) {
            throw new Exception("Not a valid team");
        } else {
            for (String name: teamNames ) {
                if(name.equals(teamName)) {
                    throw new Exception("Team is already exist in the season");
                }
            }
        }
        teamNames.add(teamName);
    }

    public void addGame(Game game) throws Exception {
        if(game == null){
            throw new Exception("game is null");
        } else {
            for (String gameId : gameIds) {
                if(gameId.equals(game.getGameID())){
                    throw new Exception("game is already set to the season");
                }
            }
            if(!teamNames.contains(game.getHomeTeam().getTeamName()) || !teamNames.contains(game.getAwayTeam().getTeamName())){
                throw new Exception("game not belong to this season");
            }
            gameIds.add(game.getGameID());
        }
    }

    public ArrayList<String> getGameIds() {
        return gameIds;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public String getId() {
        return id;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
}
