package Model.Get;

import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesperbruun on 29/11/14.
 */
public class GetUsers {

    public static String getAllUsers() {

        try {
            QueryBuilder queryBuilder = new QueryBuilder();
            Gson gson = new Gson();
            CachedRowSetImpl cachedRowSet;

            cachedRowSet = queryBuilder.selectFrom("users").all().ExecuteQuery();

            List<Users> userList = new ArrayList<>();

            while (cachedRowSet.next()) {
                Users user = new Users();
                user.setUserName(cachedRowSet.getString("email"));
                user.setActive(cachedRowSet.getBoolean("active"));
                userList.add(user);
            }

            return gson.toJson(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}
class Users {
    public void setUserName(String userName) {
        this.username = userName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private String username;
    private boolean active;

    public Users(){}

    public String getUserName(){return username; }
    public boolean getActiveStatus(){return active;}
}
