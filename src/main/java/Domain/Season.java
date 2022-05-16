package Domain;

import java.util.ArrayList;

public class Season {
    private String startDate;

    private String endDate;

    private ArrayList<League> leagues;

    public Season(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.leagues = new ArrayList<>();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<League> getLeagues() {
        return leagues;
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }
}
