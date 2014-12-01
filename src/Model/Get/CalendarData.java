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

    /**
     * Read data from any givin url.
     * @param urlString
     * @return ouput as string
     * @throws Exception
     */
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

    /**
     * Get all events from the giving user id. THe method will map data from forecast and notes into each event, and
     * return as unified JSON string.
     * Each event from CBS Calendar will be retrieved, then afterwards each event from the servers local database.
     * @param id userid as string
     * @return JSON String of all events, mapped with their individual notes, available forecast data
     */
    public synchronized static String getAllEvents(String id) {
        CachedRowSetImpl crs;
        Gson gson = new Gson();
        QueryBuilder queryBuilderForecast = new QueryBuilder();
        QueryBuilder queryBuilderNote = new QueryBuilder();
        QueryBuilder qbUser = new QueryBuilder();
        List<EventJson> eventList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            crs = qbUser.selectFrom(new String[]{"email"}, "users").where("userid", "=", id).ExecuteQuery();
            if (crs.next()) {

                if(!Forecast.isForecastUpToDate()){
                    Forecast.refreshForecast();
                };

                //Get events from CBS Calendar
                String email = crs.getString("email");
                EncryptUserID.setUserId(email.substring(0, email.indexOf("@")));
                String json = readUrl("http://calendar.cbs.dk/events.php/" + EncryptUserID.getUserId() + "/" + EncryptUserID.getKey() + ".json");

                Events events = gson.fromJson(json, Events.class);


                //Get local events from db
                PreparedStatement ps = doQuery("SELECT * FROM events WHERE calendarid IN (SELECT calendarid FROM userevents WHERE userid = ?);");
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    Event event = new Event();
                    event.setActivityid(rs.getString("activity_id"));
                    event.setEventid(rs.getString("event_id"));
                    event.setLocation(rs.getString("location"));
                    event.setCreatedby(rs.getInt("createdby"));
                    event.setStrDateStart(rs.getString("start"));
                    event.setStrDateEnd(rs.getString("end"));
                    event.setDescription(rs.getString("title"));
                    event.setText(rs.getString("text"));
                    event.setCalendarid(rs.getInt("calendarid"));

                    events.getEvents().add(event);
                }

                CachedRowSetImpl cachedRowSetForecast = queryBuilderForecast.selectFrom("forecast").all().ExecuteQuery();

                //Create arraylist of forecast data
                List<ForecastClass> forecastDays = new ArrayList<>();
                while (cachedRowSetForecast.next()) {
                    ForecastClass fc = new ForecastClass();
                    fc.setDateDate(cachedRowSetForecast.getDate("date"));
                    fc.setCelsius(cachedRowSetForecast.getString("celsius"));
                    fc.setDesc(cachedRowSetForecast.getString("description"));
                    forecastDays.add(fc);
                }
                cachedRowSetForecast.close();

                for (Event event : events.getEvents()) {

                    if(event.getStrDateStart() == null) {
                        //Convert CBS data-date into Date objects
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

                        //Convert CBS data-date into Date objects
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
                    }

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

                    //Map to pretty object
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

            } else {
                System.err.println("No userid was given");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * Get all calendars from the servers calendar, and where the user with the givin ID has access to
     * @param id  userid as string
     * @return JSON string of all calendars
     */
    public static String getAllCalendars(String id) {
        Gson g = new Gson();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GetAllCalendars> getAllCalendarsArrayList = new ArrayList<>();

        try {
            ps = doQuery("SELECT * FROM calendar WHERE calendarid IN (SELECT calendarid FROM userevents WHERE userid = ?) AND active = 1;");
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                GetAllCalendars getAllCalendars = new GetAllCalendars();
                getAllCalendars.setCalendarId(rs.getString("calendarid"));
                getAllCalendars.setCalendarName(rs.getString("name"));
                getAllCalendars.setCalendarCreatedBy(rs.getString("createdby"));

                getAllCalendarsArrayList.add(getAllCalendars);
            }

            return g.toJson(getAllCalendarsArrayList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}


class EncryptUserID {

    /**
     * Constant cipher seed - DO NOT CHANGE.
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

    /**
     * Encrypt string with MD5 hashing
     * @param str
     * @return MD5 hash of string
     */
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

    public static void setUserId(String userid) {
        EncryptUserID.userId = userid;
    }

    public static String getUserId() {
        return userId;
    }

    /**
     * Get the hashed key for CBS Calendar.
     * @return hashed key
     */
    public static String getKey() {
        return crypt(EncryptUserID.getUserId() + HASHKEY);
    }

}

/**
 * Mapper class for GSON library.
 */
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

/**
 * Mapper class for GSON library.
 */
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

/**
 * Mapper class for GSON library.
 */
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

/**
 * Mapper class for GSON library.
 */
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

/**
 * Mappe class for GSON library
 */

class Event {
    private String activityid;

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    private String eventid;
    private String type;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCalendarid(int calendarid) {
        this.calendarid = calendarid;
    }

    private String title;
    private String description;
    private String location;

    public void setTitle(String title) {
        this.title = title;
    }

    private int createdby;
    private ArrayList<String> start;
    private ArrayList<String> end;
    private ForecastClass forecast;
    private int calendarid;


    private transient Date dateStart;
    private transient Date dateEnd;

    private String strDateStart;
    private String strDateEnd;

    private String text;
    private ArrayList<Note> noter;


    public void setForecastClass(ForecastClass forecastClass) {
        this.forecast = forecastClass;
    }
    public ForecastClass getForecast(){return forecast;}

    public void setStrDateStart(String strDateStart) {
        this.strDateStart = strDateStart;
    }
    public String getStrDateStart(){return strDateStart;}

    public void setStrDateEnd(String strDateEnd) {
        this.strDateEnd = strDateEnd;
    }
    public String getStrDateEnd(){return strDateEnd;}

    public void setNoter(ArrayList<Note> noter) {
        this.noter = noter;
    }
    public ArrayList<Note> getNoter(){return noter;}

    public String getActivityid(){
        return activityid;
    }

    public String getEventid(){
        return eventid;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){this.description = description;}

    public String getLocation(){
        return location;
    }


    public ArrayList<String> getStart(){return start;}

    public void setDateStart(Date start){this.dateStart = start;}
    public Date getDateStart(){return dateStart;}

    public void setDateEnd(Date end){this.dateEnd = end;}

    public void setText(String text){this.text = text;}

    public ArrayList<String> getEnd(){
        return end;
    }

    public Event(){}

}
