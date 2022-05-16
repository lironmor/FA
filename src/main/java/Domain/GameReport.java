package Domain;

import java.util.ArrayList;


public class GameReport {
    ArrayList<GameEvent> report;

    public GameReport(){
        report = new ArrayList<>();
    }
    public void addEventToReport(GameEvent event){
        report.add(event);
    }

    public ArrayList<GameEvent> getReport() {
        return report;
    }
}
