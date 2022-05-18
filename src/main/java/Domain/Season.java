package Domain;
import com.google.common.primitives.UnsignedInteger;

import java.util.ArrayList;
import java.util.Date;

public class Season {
    private Integer startYear;
    private Integer endYear;
    private ArrayList<String> teamNames;
    private String leagueName;
    private String id;

    public Season(String leagueName, Integer startYear, Integer endYear) throws Exception {
        this.leagueName = leagueName;
        if(endYear - startYear != 1 || startYear < new Date().getYear()){
            throw new Exception("not a valid season");
        }
        this.id = leagueName + " " + startYear + "/"+ endYear;
        this.teamNames = new ArrayList<>();
    }

    public ArrayList<String> getTeamNames() {
        return teamNames;
    }

    public void addTeamName(String teamName) throws Exception {
        if(teamName == null) {
            throw new Exception("team is null");
        } else {
            for (String name: teamNames ) {
                if(name.equals(teamName)) {
                    throw new Exception("team is already exist in the season");
                }
            }
        }
        teamNames.add(teamName);
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
