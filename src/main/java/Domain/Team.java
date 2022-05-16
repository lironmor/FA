package Domain;

import java.util.ArrayList;

public class Team {
    private String teamID;
    private String teamName;
    private int expense;
    private ArrayList<IAssets> assets;

    private String season;

    private String league;

    public Team(String teamID, String teamName, String season, String league) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.expense = 0;
        this.assets = new ArrayList<IAssets>();
        this.season = season;
        this.league = league;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public ArrayList<IAssets> getAssets() {
        return assets;
    }

    public void setAssets(ArrayList<IAssets> assets) {
        this.assets = assets;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void addAssets(IAssets asset) {
        this.assets.add(asset);
    }


}
