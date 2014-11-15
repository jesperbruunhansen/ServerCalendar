package Model.Calendar;

/**
 * Created by jesperbruun on 15/11/14.
 */
public class Users {
    public void setUserId(int userId) {
        this.userid = userId;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setActive(int active) {
        this.active = active;
    }

    private int userid;
    private String username;
    private int active;

    public Users(int userId, String userName, int active){
        this.userid = userId;
        this.username = userName;
        this.active = active;
    }
    public Users(){}

    public int getUserId(){return userid;}

    public String getUserName(){return username; }
    public int getActiveStatus(){return active;}
}
