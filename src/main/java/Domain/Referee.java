package Domain;

import java.util.ArrayList;
import java.util.Date;

enum Role {main,sideline,forth}
enum Degree {expert, veteran,novice}

public class Referee extends User{
    private Role refereeRole;
    private Degree degree;

    public Referee(String fullName, String email, String userName, String password, String refereeRole, String degree) throws Exception {
        super(fullName, email, userName, password);
        roleChoose(refereeRole);
        degreeChoose(degree);
        if(this.degree == null){
            throw new Exception("Referee degree must be expert/veteran/novice !");
        }
        if(this.refereeRole == null) {
            throw new Exception("Referee roll must be main/sideline/forth !");
        }
    }


    public void roleChoose(String refRole){
        if(refRole.equals("main")){
            refereeRole = Role.main;
        }
        else if(refRole.equals("side line")){
            refereeRole = Role.sideline;
        }
        else if(refRole.equals("forth")){
            refereeRole = Role.forth;
        }
        else{
            refereeRole = null;
        }
    }
    public void degreeChoose(String deg){
        if(deg.equals("expert")){
            degree = Degree.expert;
        }
        else if(deg.equals("veteran")){
            degree = Degree.veteran;
        }
        else if(deg.equals("novice")){
            degree = Degree.novice;
        }
        else{
            degree = null;
        }
    }

    public String getRefereeRole() {
        if (refereeRole != null)
            return refereeRole.toString();
        else
            return null;
    }

    public String getDegree() {
        if(degree == null){
            return null;
        }
        return degree.toString();
    }
}
