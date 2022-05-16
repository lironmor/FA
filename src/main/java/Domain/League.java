package Domain;

import java.util.ArrayList;

public class League {
    private int id;

    private ArrayList<Season> seasons;

    public League(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void addSeason(Season season) {
        this.seasons.add(season);
    }
}
