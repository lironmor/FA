package Domain;

import java.util.ArrayList;

public class League {
    private String name;
    private ArrayList<String> seasonIds;


    public League(String name) {
        this.name = name;
        this.seasonIds = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getSeasonIds() {
        return seasonIds;
    }

    public void addSeasonId(String seasonId) throws Exception {
        if(seasonId == null){
            throw new Exception("season is null");
        }
        seasonIds.add(seasonId);
    }
}
