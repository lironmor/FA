package Domain;

import java.util.ArrayList;
import java.util.Date;

enum Role {main,sideline,forth}
enum Degree {expert, veteran,novice}

public class Referee extends User{
    private Role refereeRole;
    private Degree degree;
    private ArrayList<Game> comingUp;

    public Referee(String fullName, String email, String userName, String password, String refereeRole, String degree, ArrayList<Game> comingUp) throws Exception {
        super(fullName, email, userName, password);
        roleChoose(refereeRole);
        degreeChoose(degree);
        if(degree == null){
            throw new Exception("Referee degree must be expert/veteran/novice !");
        }
        if(refereeRole == null) {
            throw new Exception("Referee roll must be main/sideline/forth");
        }
        this.comingUp = comingUp;
    }

    public ArrayList<Game> getUpComingGames(){
        return comingUp;
    }

    public void setComingUp(ArrayList<Game> comingUp) {
        this.comingUp = comingUp;
    }

    public void makeGameReport(Game g){
        GameReport rep = new GameReport();
        g.addReport(rep);
    }
    public void addEventToReport(Game g, Date date, int min, String descript,String type ) throws Exception {
        GameEvent event = new GameEvent(date,min,descript,type);
        g.getGameReport().addEventToReport(event);
    }

    public void roleChoose(String refRole){
        if(refRole == "main"){
            refereeRole = Role.main;
        }
        else if(refRole == "side line"){
            refereeRole = Role.sideline;
        }
        else if(refRole == "forth"){
            refereeRole = Role.forth;
        }
        else{
            refereeRole = null;
        }
    }
    public void degreeChoose(String deg){
        if(deg == "expert"){
            degree = Degree.expert;
        }
        else if(deg == "veteran"){
            degree = Degree.veteran;
        }
        else if(deg == "novice"){
            degree = Degree.novice;
        }
        else{
            degree = null;
        }
    }

    public String getRefereeRole() {
        return refereeRole.toString();
    }

    public String getDegree() {
        return degree.toString();
    }

    public ArrayList<Game> getComingUp() {
        return comingUp;
    }
}
