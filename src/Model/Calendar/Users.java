package Model.Calendar;

/**
 * Created by jesperbruun on 15/11/14.
 */
public class Users {
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setActive(int active) {
        this.active = active;
    }

    private int userId;
    private String userName;
    private int active;

    public Users(int userId, String userName, int active){
        this.userId = userId;
        this.userName = userName;
        this.active = active;
    }
    public Users(){}

    public int getUserId(){return userId;}

    public String getUserName(){return userName; }
    public int getActiveStatus(){return active;}
}
