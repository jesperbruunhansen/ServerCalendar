package Model.Post;

import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

/**
 * Created by Casper on 23/11/14.
 */
public class Notes {

    private static CachedRowSetImpl rs;
    private static String jsonResponse;

        public static void createNote(String jsonPostRequest){

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate NoteCredentials
        NoteCredentials noteCredentials = gson.fromJson(jsonPostRequest, NoteCredentials.class);

        //Check if client filled correct necessary information
        if(noteCredentials.getUserid().equals("")) {
            jsonResponse = "{\"response\": \"USERID MISSING\"}";

        } else if (noteCredentials.getEventid().equals("")) {
            jsonResponse = "{\"response\": \"EVENTID MISSING\"}";

        } else if (noteCredentials.getText().equals("")) {
            jsonResponse = "{\"response\": \"TEXT MISSING\"}";

        } else {
            try {
                //Create note in database
                queryBuilder
                        .insertInto("notes", new String[]{"eventid", "createdby", "text"})
                        .values(new String[]{noteCredentials.getEventid(), noteCredentials.getUserid(), noteCredentials.getText()})
                        .Execute();
                jsonResponse = "{\"response\": \"NOTE CREATED\"}";

            } catch (SQLException ex) {
                ex.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN WRITING TO DATABASE\"}";
            }

        }

    }

    public static void deleteNote(String jsonPostRequest){

        //Implement Gson
        Gson gson = new Gson();

        //Create new QueryBuilder
        QueryBuilder queryBuilder = new QueryBuilder();

        //Get JsonRequest and populate NoteCredentials
        NoteCredentials noteCredentials = gson.fromJson(jsonPostRequest, NoteCredentials.class);

        //Check if client filled correct necessary information
        if(noteCredentials.getNoteid().equals("")) {
            jsonResponse = "{\"response\": \"NOTEID MISSING\"}";

        } else {
            try {
                //Delete note from database
                queryBuilder.deleteFrom("notes").where("noteid", "=", noteCredentials.getNoteid()).Execute();
                jsonResponse = "{\"response\": \"NOTE DELETED\"}";

            } catch (SQLException e) {
                e.printStackTrace();
                jsonResponse = "{\"response\": \"ERROR WHEN DELETING FROM DATABASE\"}";
            }

        }

    }

    public static void updateNote(String jsonPostRequest){

    }

    public static String getJsonResponse() {
        return jsonResponse;
    }

    private class NoteCredentials{

        private String userid;
        private String eventid;
        private String noteid;
        private String text;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getEventid() {
            return eventid;
        }

        public void setEventid(String eventid) {
            this.eventid = eventid;
        }

        public String getNoteid() {
            return noteid;
        }

        public void setNoteid(String noteid) {
            this.noteid = noteid;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

    }

}
