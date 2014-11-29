package Model.Get;

import Model.Forecast.Forecast;
import Model.Forecast.ForecastClass;
import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDate;


/**
 * Created by jesperbruun on 13/10/14.
 */
public class CalendarData extends Model {

    private static Gson gson;
    private static QueryBuilder queryBuilder;

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

    public static String getAllEvents(String id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        CachedRowSetImpl crs;
        Gson gson = new Gson();
        List<EventJson> eventList = new ArrayList<>();

        try{
            if(id.isEmpty() || id == null){
                throw new Exception("No user_id was given");
            }
            else{

                //Is forecast NOT up to date, then refresh the data
                //if(!Forecast.isForecastUpToDate()){
                //    System.out.println("\tForecast has to be updated");
                //    Forecast.refreshForecast();
                //    System.out.println("\tForecast has been updated");
                // }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                QueryBuilder qbUser = new QueryBuilder();
                crs = qbUser.selectFrom(new String[]{"email"}, "users").where("userid", "=", id).ExecuteQuery();
                if(crs.next()){
                    String email = crs.getString("email");
                    EncryptUserID.setUserId(email.substring(0, email.indexOf("@")));
                    String json = readUrl("http://calendar.cbs.dk/events.php/" + EncryptUserID.getUserId() + "/" + EncryptUserID.getKey() + ".json");

                    Events events = gson.fromJson(json, Events.class);


                    QueryBuilder queryBuilderForecast = new QueryBuilder();
                    QueryBuilder queryBuilderNote = new QueryBuilder();
                    CachedRowSetImpl cachedRowSetForecast = queryBuilderForecast.selectFrom("forecast").all().ExecuteQuery();

                    //Create arraylist of forecast data
                    List<ForecastClass> forecastDays = new ArrayList<>();
                    while (cachedRowSetForecast.next()){
                        ForecastClass fc = new ForecastClass();
                        fc.setDateDate(cachedRowSetForecast.getDate("date"));
                        fc.setCelsius(cachedRowSetForecast.getString("celsius"));
                        fc.setDesc(cachedRowSetForecast.getString("description"));
                        forecastDays.add(fc);
                    }
                    cachedRowSetForecast.close();

                    for(Event event : events.getEvents()){

                        int monthStart = Integer.parseInt(event.getStart().get(1)) + 1;
                        String start =
                                event.getStart().get(2) + "-" +
                                        monthStart + "-" +
                                        event.getStart().get(0) + " " +
                                        event.getStart().get(3) + ":" +
                                        event.getStart().get(4);
                        Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(start);
                        event.setDateStart(startDate);
                        event.setStrDateStart(sdf.format(startDate));

                        int monthEnd = Integer.parseInt(event.getEnd().get(1)) + 1;
                        String end =
                                event.getEnd().get(2) + "-" +
                                        monthEnd + "-" +
                                        event.getEnd().get(0) + " " +
                                        event.getEnd().get(3) + ":" +
                                        event.getEnd().get(4);
                        Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(end);
                        event.setDateEnd(endDate);
                        event.setStrDateEnd(sdf.format(endDate));

                        //Map forecast-data to events
                        LocalDate eventDay = new DateTime(event.getDateStart()).toLocalDate();
                        for (int i = 0; i < forecastDays.size(); i++) {
                            LocalDate forecastDate = new DateTime(forecastDays.get(i).getDateDate()).toLocalDate();
                            if (eventDay.equals(forecastDate)) {
                                event.setForecastClass(forecastDays.get(i));
                                break;
                            }
                        }

                        //Merge notes to events
                        ArrayList<Note> noteList = new ArrayList<>();
                        CachedRowSetImpl cachedRowSetNote = queryBuilderNote.selectFrom("notes").where("eventid", "=", event.getEventid()).ExecuteQuery();
                        while (cachedRowSetNote.next()) {
                            Note note = new Note();
                            note.setCreatedby(cachedRowSetNote.getInt("createdby"));
                            note.setText(cachedRowSetNote.getString("text"));
                            note.setCreatedon(cachedRowSetNote.getTimestamp("created").toString());
                            noteList.add(note);
                        }
                        event.setNoter(noteList);
                        cachedRowSetNote.close();

                        EventJson eventJson = new EventJson();
                        eventJson.setActivityid(event.getActivityid());
                        eventJson.setEventid(event.getEventid());
                        eventJson.setEnd(event.getStrDateEnd());
                        eventJson.setStart(event.getStrDateStart());
                        eventJson.setLocation(event.getLocation());
                        eventJson.setTitle(event.getDescription());
                        eventJson.setNotes(event.getNoter());
                        eventJson.setWeatherdata(event.getForecast());
                        eventList.add(eventJson);

                    }
                    return gson.toJson(eventList);

                }
                else {
                    throw new Exception("userid does not exist");
                }
                // ps = doQuery("SELECT * FROM events WHERE calendarid IN (SELECT calendarid FROM userevents WHERE userid = ?);");
                //ps.setString(1, id);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }


    public static String getAllCalendars(String id){
        Gson g = new Gson();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GetAllCalendars> getAllCalendarsArrayList = new ArrayList<>();

        try {
            ps = doQuery("SELECT * FROM calendar WHERE calendarid IN (SELECT calendarid FROM userevents WHERE userid = ?) AND active = 1;");
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                GetAllCalendars getAllCalendars = new GetAllCalendars();
                getAllCalendars.setCalendarId(rs.getString("calendarid"));
                getAllCalendars.setCalendarName(rs.getString("name"));
                getAllCalendars.setCalendarCreatedBy(rs.getString("createdby"));

                getAllCalendarsArrayList.add(getAllCalendars);
            }

            return g.toJson(getAllCalendarsArrayList);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /*public static void setCalendarEventsToDb() {

        try {
            String json = readUrl("http://calendar.cbs.dk/events.php/" + EncryptUserID.getUserId() + "/" + EncryptUserID.getKey() + ".json");


            queryBuilder = new QueryBuilder();

            String[] fields = {"activity_id", "event_id", "location", "createdby", "start", "end", "title", "text", "customevent", "calendarid"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (int i = 0; i < events.getEvents().size(); i++) {

                int monthStart = Integer.parseInt(events.getEvents().get(i).getStart().get(1)) + 1;
                String start =
                        events.getEvents().get(i).getStart().get(2) + "-" +
                                monthStart + "-" +
                                events.getEvents().get(i).getStart().get(0) + " " +
                                events.getEvents().get(i).getStart().get(3) + ":" +
                                events.getEvents().get(i).getStart().get(4);

                Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(start);
                String strStartDate = sdf.format(startDate);

                int monthEnd = Integer.parseInt(events.getEvents().get(i).getEnd().get(1)) + 1;
                String end =
                        events.getEvents().get(i).getEnd().get(2) + "-" +
                                monthEnd + "-" +
                                events.getEvents().get(i).getEnd().get(0) + " " +
                                events.getEvents().get(i).getEnd().get(3) + ":" +
                                events.getEvents().get(i).getEnd().get(4);
                Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(end);
                String strEndDate = sdf.format(endDate);


                String[] values = {
                        events.getEvents().get(i).getActivityid(),  //Activity ID
                        events.getEvents().get(i).getEventid(),     //EventID
                        events.getEvents().get(i).getLocation(),    //Location
                        "1",                                        //CreatedBy
                        strStartDate,                       //Start
                        strEndDate,                         //End
                        events.getEvents().get(i).getDescription(),       //Title
                        "Text - text",
                        "0",
                        "1",
                };

                queryBuilder.insertInto("events", fields).values(values).Execute();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

}
class GetAllCalendars {

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public void setCalendarCreatedBy(String calendarCreatedBy) {
        this.calendarCreatedBy = calendarCreatedBy;
    }

    private String calendarId;
    private String calendarName;
    private String calendarCreatedBy;
}
class Note {

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    private int createdby;
    private String text;
    private String createdon;
}
class Events {
    ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> event) {
        this.events = event;
    }

    @Override
    public String toString() {
        return Arrays.toString(events.toArray());

    }

}

class EncryptUserID {

    /**
     * Constant cipher seed - DO NOT CHANGE.
     * http://www.miraclesalad.com/webtools/md5.php - Du kan her saette userid foerst og derefter hashkey for at teste
     */
    private static final String HASHKEY = "v.eRyzeKretW0r_t";
    private static String userId;
    private static String key;
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Enkryptere en tekst streng som bliver parset til funktionen
    public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            } else {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

    public static void setUserId(String userid){EncryptUserID.userId = userid;}

    public static String getUserId() {
        return userId;
    }

    public static String getKey() {
        return crypt(EncryptUserID.getUserId() + HASHKEY);
    }

}
class EventJson {
    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setWeatherdata(ForecastClass weatherdata) {
        this.weatherdata = weatherdata;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    private String activityid;
    private String eventid;
    private String title;
    private String location;
    private String start;
    private String end;
    private ForecastClass weatherdata;
    private ArrayList<Note> notes;

}
