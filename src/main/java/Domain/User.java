package Domain;

public abstract class  User {

    private String fullName;
    private String userName;
    private String email;
    private String password;


    // constructors / standard setters / getters
    public User (String fullName, String email,String userName , String password) {
        this.fullName = fullName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
