package Domain;


public class Stadium implements IAssets{
    private String location;

    public Stadium(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}



