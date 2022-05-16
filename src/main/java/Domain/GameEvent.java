package Domain;

import java.util.Date;

enum EventType{goal, offside, foul, redCard, yellowCard, injury, substitution}
public class GameEvent {
    Date date;
    int minuteInGame;
    String description;
    EventType type;

    public GameEvent(Date dat , int min, String descript, String type) throws Exception {
        date = dat;
        minuteInGame = min;
        description = descript;
        typeChoose(type);
        if(this.type == null) {
            throw new Exception("Event type must be goal/foul/offside/injury/substitution/yellowCard/redCard");
        }
    }

    public void typeChoose(String typ){
        if (typ == "goal"){
            type = EventType.goal;
        }
        else if (typ == "yellow card"){
            type = EventType.yellowCard;
        }
        else if (typ == "offside"){
            type = EventType.offside;
        }
        else if (typ == "foul"){
            type = EventType.foul;
        }
        else if (typ == "red card"){
            type = EventType.redCard;
        }
        else if (typ == "injury"){
            type = EventType.injury;
        }
        else if (typ == "substitution"){
            type = EventType.substitution;
        }
        else{
            type = null;
        }

    }

    public Date getDate() {
        return date;
    }

    public int getMinuteInGame() {
        return minuteInGame;
    }

    public String getDescription() {
        return description;
    }

    public EventType getType() {
        return type;
    }
}
