package Domain;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private int expense;
    private Stadium stadium;
    private ArrayList<String> seasonIds;

    public Team(String teamName, Stadium stadium) throws Exception {
        if(teamName == null || stadium == null) {
            throw new Exception("parameters are null");
        }
        this.teamName = teamName;
        this.expense = 0;
        this.stadium = stadium;
        this.seasonIds = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) throws Exception {
        if(teamName != null) {
            this.teamName = teamName;
        } else {
            throw new Exception("teamName is null");
        }
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public void addSeasonId(String seasonId) throws Exception {
        if(seasonId == null) {
            throw new Exception("season is null");
        } else {
            for (String sId : seasonIds) {
                if(sId.equals(seasonId)){
                    throw new Exception("team already belong to this season");
                }
            }
        }
        seasonIds.add(seasonId);
    }

    public Stadium getStadium() {
        return stadium;
    }
}
