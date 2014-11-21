package Model.Post;

import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

/**
 * Created by jesperbruun on 21/11/14.
 */
public class Login {

    private static CachedRowSetImpl rs;
    private static String jsonResponse;

    public static void authenticateUser(String jsonPostRequest){

        jsonResponse = null;
        System.out.println(jsonPostRequest);

        Gson gson = new Gson();
        LoginCredentials loginCredentials = gson.fromJson(jsonPostRequest, LoginCredentials.class);

        QueryBuilder queryBuilder = new QueryBuilder();

        try{
            rs = queryBuilder.selectFrom("users").all().ExecuteQuery();
            while (rs.next()){
                String username = rs.getString("email");
                String password = rs.getString("password");
                Boolean active = rs.getBoolean("active");

                if(active && username.equals(loginCredentials.getUsername()) && password.equals(loginCredentials.getPassword())){
                    jsonResponse = "{\"response\": \"LOGIN OK\"}";
                    break;
                }
                else{
                    jsonResponse = "{\"response\": \"LOGIN FAILED!\"}";
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static String getJsonResponse() {
        return jsonResponse;
    }

    private class LoginCredentials{

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        private String username;
        private String password;
    }

}
