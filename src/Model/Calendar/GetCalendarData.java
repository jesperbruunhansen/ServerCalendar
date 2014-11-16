package Model.Calendar;

import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData extends Model {

    private Gson gson;
    private QueryBuilder queryBuilder;
  //  private ResultSet rs;

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

        try {
            queryBuilder = new QueryBuilder();
            gson = new Gson();
            CachedRowSetImpl cachedRowSet;

            cachedRowSet = queryBuilder.selectFrom("users").all().ExecuteQuery();

            List<Users> userList = new ArrayList<>();

            while (cachedRowSet.next()) {
                Users user = new Users();
                user.setUserId(cachedRowSet.getInt("userid"));
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

//    public void joinTest() {
//        queryBuilder = new QueryBuilder();
//        try {
//            rs = queryBuilder
//                    .selectFrom(new String[]{"events.event_id", "notes.text"}, "events")
//                    .innerJoin("notes")
//                    .on("events.event_id", "=", "notes.eventid")
//                    .ExecuteQuery();
//
//            while (rs.next()) {
//                System.out.println("EventID: " + rs.getString("events.event_id"));
//                System.out.println("Note: " + rs.getString("notes.text"));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }


    public String getAllEvents() {
        try {
            CachedRowSetImpl cachedRowSet, cachedRowSetNote;
            queryBuilder = new QueryBuilder();
            QueryBuilder queryBuilderNote = new QueryBuilder();
            gson = new Gson();

            cachedRowSet = queryBuilder.selectFrom("events").all().ExecuteQuery();

            List<Event> eventList = new ArrayList<>();

            while (cachedRowSet.next()) {
                Event event = new Event();
                event.setActivityid(cachedRowSet.getString("activity_id"));
                event.setEventid(cachedRowSet.getString("event_id"));
                event.setLocation(cachedRowSet.getString("location"));
                event.setCreatedby(cachedRowSet.getInt("createdby"));
                event.setStringStart(cachedRowSet.getString("start"));
                event.setStringEnd(cachedRowSet.getString("end"));
                event.setTitle(cachedRowSet.getString("title"));
                event.setText(cachedRowSet.getString("text"));
                event.setCustomevent(cachedRowSet.getBoolean("customevent"));
                event.setCalendarid(cachedRowSet.getInt("CalenderID"));

                ArrayList<Note> noteList = new ArrayList<>();
                cachedRowSetNote = queryBuilderNote.selectFrom("notes").where("eventid", "=", cachedRowSet.getString("event_id")).ExecuteQuery();
                while (cachedRowSetNote.next()) {
                    Note note = new Note();
                    note.setCreatedby(cachedRowSetNote.getInt("createdby"));
                    note.setText(cachedRowSetNote.getString("text"));
                    note.setCreatedon(cachedRowSetNote.getTimestamp("created").toString());
                    noteList.add(note);
                }
                event.setNoter(noteList);

                eventList.add(event);
            }
            return gson.toJson(eventList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCalendarEventsToDb() throws Exception {


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
