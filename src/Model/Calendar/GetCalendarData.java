package Model.Calendar;

import Model.QueryBuild.Execute;
import com.google.gson.Gson;
import Model.QueryBuild.QueryBuilder;
import Model.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData extends Model{

    private Gson gson;
    private QueryBuilder queryBuilder;
    private ResultSet rs;


    //henter data fra URL og l??er ind til en string
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public String getAllUsers() {

        try{
            queryBuilder = new QueryBuilder();
            gson = new Gson();

            rs = queryBuilder.selectFrom("users").all().ExecuteQuery();

            List<Users> userList = new ArrayList<>();

            while (rs.next()){
                Users users = new Users();
                users.setUserId(rs.getInt("userid"));
                users.setUserName(rs.getString("email"));
                users.setActive(rs.getInt("active"));
                userList.add(users);
            }

            return gson.toJson(userList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        return null;

    }

    public void joinTest(){
        queryBuilder = new QueryBuilder();
        try {
            rs = queryBuilder
                    .selectFrom(new String[]{"events.event_id", "notes.text"}, "events")
                    .innerJoin("notes")
                    .on("events.event_id", "=", "notes.eventid")
                    .ExecuteQuery();

            while (rs.next()) {
                System.out.println("EventID: " + rs.getString("events.event_id"));
                System.out.println("Note: " + rs.getString("notes.text"));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                rs.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }


//        try {
//            getConnection();
//            getConn();
//            ps = doQuery("SELECT * FROM events INNER JOIN notes ON events.event_id = notes.eventid");
//            rs = ps.executeQuery();
//            while (rs.next()){
//                System.out.println("EventID: " + rs.getString("events.event_id"));
//                System.out.println("Note: " + rs.getString("notes.text"));
//            }
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }

    }

    public String getAllEvents(){
        try{
            ResultSet rsNote;
            queryBuilder = new QueryBuilder();
            gson = new Gson();

            rs = queryBuilder.selectFrom("events").all().ExecuteQuery();

            List<Event> eventList = new ArrayList<>();

            while (rs.next()){
                Event event = new Event();
                event.setActivityid(rs.getString("activity_id"));
                event.setEventid(rs.getString("event_id"));
                event.setLocation(rs.getString("location"));
                event.setCreatedby(rs.getInt("createdby"));
                event.setStringStart(rs.getString("start"));
                event.setStringEnd(rs.getString("end"));
                event.setTitle(rs.getString("title"));
                event.setText(rs.getString("text"));
                event.setCustomevent(rs.getInt("customevent"));
                event.setCalendarid(rs.getInt("CalenderID"));

                ArrayList<Note> noteList = new ArrayList<>();
                rsNote = new QueryBuilder().selectFrom("notes").where("eventid", "=", rs.getString("event_id")).ExecuteQuery();
                while(rsNote.next()){
                    Note note = new Note();
                    note.setCreatedby(rsNote.getInt("createdby"));
                    note.setText(rsNote.getString("text"));
                    note.setCreatedon(rsNote.getTimestamp("created").toString());
                    noteList.add(note);
                }

                event.setNoter(noteList);

                eventList.add(event);
            }
            return gson.toJson(eventList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setCalendarEventsToDb() throws Exception{


          String json = readUrl("http://calendar.cbs.dk/events.php/" + EncryptUserID.getUserId() + "/" + EncryptUserID.getKey() + ".json");


//        String json = readUrl("http://calendar.cbs.dk/events.php/caha13ag/02a24d4e002e6e3571227c39e2f63784.json");


        gson = new Gson();
        Events events = gson.fromJson(json, Events.class);
        queryBuilder = new QueryBuilder();

        String[] fields = {"activity_id", "event_id", "location", "createdby", "start", "end", "title", "text", "customevent", "CalenderID"};

        Date dateStart, dateEnd;
        long time;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy hh:mm");

        for (int i = 0; i < events.getEvents().size(); i++) {

            int monthStart = Integer.parseInt(events.getEvents().get(i).getStart().get(1)) + 1;
            String start =
                    events.getEvents().get(i).getStart().get(2) + "-" +
                            monthStart + "-" +
                            events.getEvents().get(i).getStart().get(0) + " " +
                            events.getEvents().get(i).getStart().get(3) + ":" +
                            events.getEvents().get(i).getStart().get(4);
            dateStart = formatter.parse(start);

            int monthEnd = Integer.parseInt(events.getEvents().get(i).getEnd().get(1)) + 1;
            String end =
                    events.getEvents().get(i).getEnd().get(2) + "-" +
                            monthEnd + "-" +
                            events.getEvents().get(i).getEnd().get(0) + " " +
                            events.getEvents().get(i).getEnd().get(3) + ":" +
                            events.getEvents().get(i).getEnd().get(4);
            dateEnd = formatter.parse(end);

            String[] values = {
                    events.getEvents().get(i).getActivityid(),  //Activity ID
                    events.getEvents().get(i).getEventid(),     //EventID
                    events.getEvents().get(i).getLocation(),    //Location
                    "1",                                        //CreatedBy
                    dateStart.toString(),                       //Start
                    dateEnd.toString(),                         //End
                    events.getEvents().get(i).getTitle(),       //Title
                    "Text - text",
                    "1",
                    "1",
            };
        queryBuilder.insertInto("events", fields).values(values).Execute();
        }

    }

}
