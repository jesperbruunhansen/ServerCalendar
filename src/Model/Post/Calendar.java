package Model.Post;

import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

/**
 * Created by Casper on 23/11/14.
 */
public class Calendar extends Model {

    private static CachedRowSetImpl rs;
    private static String jsonResponse;

    /**
     * Create calendar from JSON request.
     * Requires userid, calendar-name and private/public properties
     * @param jsonPostRequest
     */
    public static void createCalendar(String jsonPostRequest){

        //jsonResponse = null;
        System.out.println(jsonPostRequest);

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate CalendarCredentials
        CalendarCredentials calendarCredentials = gson.fromJson(jsonPostRequest, CalendarCredentials.class);

        //Check if client filled correct necessary information
        if(calendarCredentials.getUserid().equals("")) {
            jsonResponse = "{\"response\": \"USERID MISSING\"}";

        } else if (calendarCredentials.getName().equals("")) {
            jsonResponse = "{\"response\": \"CALENDAR NAME MISSING\"}";

        } else if (calendarCredentials.getPrivatePublic().equals("")) {
            jsonResponse = "{\"response\": \"PRIVATE/PUBLIC MISSING\"}";

        } else {
            try {
                //Create calendar in database
                queryBuilder
                        .insertInto("calendar", new String[]{"name", "createdby", "privatepublic"})
                        .values(new String[]{calendarCredentials.getName(), calendarCredentials.getUserid(), calendarCredentials.getPrivatePublic()})
                        .Execute();

                jsonResponse = "{\"response\": \"CALENDAR CREATED\"}";

                //Receive the CalendarID which was just created
                rs = queryBuilder.selectFrom("calendar").where("name", "=", calendarCredentials.getName()).ExecuteQuery();
                while (rs.next()) {
                    String calenderID = rs.getString("calendarid");
                    calendarCredentials.setCalendarid(calenderID);
                }

                //Write to database "userevents" with User- & CalendarID
                queryBuilder
                        .insertInto("userevents", new String[]{"userid", "calendarid"})
                        .values(new String[]{calendarCredentials.getUserid(), calendarCredentials.getCalendarid()})
                        .Execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN WRITING TO DATABASE\"}";
            }

        }

    }

    /**
     * Delete calendar from JSON request.
     * Requires calendar id.
     * @param jsonPostRequest
     */
    public static void deleteCalendar(String jsonPostRequest){

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate CalendarCredentials
        CalendarCredentials calendarCredentials = gson.fromJson(jsonPostRequest, CalendarCredentials.class);

        //Check if client filled correct necessary information
        if(calendarCredentials.getCalendarid().equals("")) {
            jsonResponse = "{\"response\": \"CALENDARID MISSING\"}";

        } else {
            try {
                //Delete calendar from database
                queryBuilder.deleteFrom("calendar").where("calendarid", "=", calendarCredentials.getCalendarid()).Execute();
                jsonResponse = "{\"response\": \"CALENDAR DELETED\"}";

            } catch (SQLException e) {
                e.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN DELETING FROM DATABASE\"}";
            }

        }
    }

    public static void updateCalendar(String jsonPostRequest) {

    }

    /**
     * Share calendar from given JSON request
     * Requires calendar id and email from user
     * @param jsonPostRequest
     */
    public static void shareCalendar(String jsonPostRequest){

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate CalendarCredentials
        CalendarCredentials calendarCredentials = gson.fromJson(jsonPostRequest, CalendarCredentials.class);

        //Check if client filled correct necessary information
        if(calendarCredentials.getCalendarid().equals("")) {
            jsonResponse = "{\"response\": \"CALENDARID MISSING\"}";

        } else if (calendarCredentials.getEmail().equals("")) {
            jsonResponse = "{\"response\": \"EMAIL MISSING\"}";

        } else {
            try {

                //Converts user email to userid
                rs = queryBuilder.selectFrom("users").where("email", "=", calendarCredentials.getEmail()).ExecuteQuery();
                while (rs.next()) {
                    String userid = rs.getString("userid");
                    calendarCredentials.setUserid(userid);
                }

                //Add userid to calendarid in the uservents database
                queryBuilder
                        .insertInto("userevents", new String[]{"userid", "calendarid"})
                        .values(new String[]{calendarCredentials.getUserid(), calendarCredentials.getCalendarid()})
                        .Execute();

                jsonResponse = "{\"response\": \"CALENDAR SHARED\"}";

            } catch (SQLException e) {
                e.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN WRITING TO DATABASE\"}";
            }

        }

    }

    public static String getJsonResponse() {
        return jsonResponse;
    }

    /**
     * Mapper class library
     */
    private class CalendarCredentials{

        private String userid;
        private String name;
        private String privatepublic;
        private String calendarid;
        private String email;


        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCalendarid() {
            return calendarid;
        }

        public void setCalendarid(String calendarid) {
            this.calendarid = calendarid;
        }

        public String getName() {
            return name;
        }

        public String getPrivatePublic() {
            return privatepublic;
        }

        public String getEmail() {
            return email;
        }

    }

}
