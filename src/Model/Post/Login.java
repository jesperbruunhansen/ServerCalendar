package Model.Post;

import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

/**
 * Created by jesperbruun on 21/11/14.
 */
public class Login extends Model {

    private static CachedRowSetImpl rs;
    private static String jsonResponse;
    private static String authReply;


    public static void authenticateUser(String jsonPostRequest){

        jsonResponse = null;
        System.out.println("\t" + jsonPostRequest);
        Gson gson = new Gson();

        LoginCredentials loginCredentials = gson.fromJson(jsonPostRequest, LoginCredentials.class);

        QueryBuilder queryBuilder = new QueryBuilder();


        try{
            rs = queryBuilder.selectFrom("users").all().ExecuteQuery();
            while (rs.next()){
                String userId = rs.getString("userid");
                String username = rs.getString("email");
                String password = rs.getString("password");
                Boolean active = rs.getBoolean("active");

                if(active && username.equals(loginCredentials.getUsername()) && password.equals(loginCredentials.getPassword())){
                    jsonResponse = "{\"authentication\": \"true\", \"userid\":\""+ userId + "\"}";
                    break;
                }
                else{
                    jsonResponse = "{\"authentication\": \"false\"}";
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
