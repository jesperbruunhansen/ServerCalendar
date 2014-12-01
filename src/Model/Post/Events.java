package Model.Post;

import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Casper on 23/11/14.
 */
public class Events extends Model {

    private static CachedRowSetImpl rs;
    private static String jsonResponse;
    private static String httpResponse;

    public static void createEvent(String jsonPostRequest){

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate EventCredentials
        EventCredentials eventCredentials = gson.fromJson(jsonPostRequest, EventCredentials.class);

        //Check if client filled correct necessary information
        if(eventCredentials.getUserid().equals("")) {
            jsonResponse = "{\"response\": \"USERID MISSING\"}";
        } else if (eventCredentials.getCalendarid().equals("")) {
            jsonResponse = "{\"response\": \"CALENDAR ID MISSING\"}";

        }  else if (eventCredentials.getLocation().equals("")) {
            jsonResponse = "{\"response\": \"LOCATION MISSING\"}";

        } else if (eventCredentials.getTitle().equals("")) {
            jsonResponse = "{\"response\": \"TITLE MISSING\"}";

        } else if (eventCredentials.getStart().equals("")) {
            jsonResponse = "{\"response\": \"START MISSING\"}";

        } else if (eventCredentials.getEnd().equals("")) {
            jsonResponse = "{\"response\": \"END MISSING\"}";

        } else {
            try {
                //Create calendar in database

                long eventId = System.currentTimeMillis() / 1000L;

                String customevent = "1";
                queryBuilder
                        .insertInto("events", new String[]{"event_id", "location", "createdby", "start", "end", "title", "customevent", "calendarid"})
                        .values(new String[]{String.valueOf(eventId) + eventCredentials.getUserid(), eventCredentials.getLocation(), eventCredentials.getUserid()
                        , eventCredentials.getStart(), eventCredentials.getEnd(), eventCredentials.getTitle(), customevent, eventCredentials.getCalendarid()})
                        .Execute();

                jsonResponse = "{\"response\": \"EVENT CREATED\"}";

            } catch (SQLException ex) {
                ex.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN WRITING TO DATABASE\"}";
            }

        }
    }

    public static void deleteEvent(String jsonPostRequest){

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate EventCredentials
        EventCredentials eventCredentials = gson.fromJson(jsonPostRequest, EventCredentials.class);

        //Check if client filled correct necessary information
        if(eventCredentials.getEventId().equals("")) {
            jsonResponse = "{\"response\": \"EVENTID MISSING\"}";

        } else {
            try {
                //Delete event from database
                queryBuilder.deleteFrom("events").where("id", "=", eventCredentials.getEventId()).Execute();
                jsonResponse = "{\"response\": \"EVENT DELETED\"}";

            } catch (SQLException e) {
                e.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN DELETING FROM DATABASE\"}";
            }

        }

    }

    public static void updateEvent(String jsonPostRequest){

        /*PreparedStatement ps = doQuery("SELECT * FROM events WHERE id = ? AND useriud = ? ;");
        ps.setString(1, "23");
        ps.setString(2, "asdf");
       ResultSet rs =  ps.executeQuery();*/
    }
    public static String getJsonResponse() {
        return jsonResponse;
    }

    private class EventCredentials{

        private String userid;
        private String calendarid;
        private String eventid;
        private String title;
        private String location;
        private String id;
        private String start;
        private String end;

        public String getUserid() {
            return userid;
        }

        public String getCalendarid() {
            return calendarid;
        }

        public String getTitle() {
            return title;
        }

        public String getLocation() {
            return location;
        }

        public String getEventId() {
            return eventid;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

    }

}
