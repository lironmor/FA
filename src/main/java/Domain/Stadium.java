package Domain;


public class Stadium implements IAssets{
    private String location;
    private String name;

    public Stadium(String location, String name) throws Exception {
        if(location == null || name == null){
            throw new Exception("parameters are null");
        }
        this.location = location;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



