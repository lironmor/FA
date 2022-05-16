package Domain;

import java.util.ArrayList;

public class Team {
    String teamID;
    String teamName;
    int expense;
    ArrayList<IAssets> assets;

    public Team(String teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.expense = 0;
        this.assets = new ArrayList<IAssets>();
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

    public void addAssets(IAssets asset) {
        this.assets.add(asset);
    }
}
