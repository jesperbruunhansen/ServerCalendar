package Model.Calendar;

import Model.Forecast.ForecastClass;
import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDate;


/**
 * Created by jesperbruun on 13/10/14.
 */
public class GetCalendarData extends Model {

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

    public void joinTest() {
        queryBuilder = new QueryBuilder();
        QueryBuilder queryBuilder1 = new QueryBuilder();

        try {
            CachedRowSetImpl cachedRowSet = queryBuilder.selectFrom("events").all().ExecuteQuery();
            CachedRowSetImpl cachedRowSet1 = queryBuilder1.selectFrom("forecast").all().ExecuteQuery();

            List<Date> forecastDays = new ArrayList<>();
            List<Date> eventDays = new ArrayList<>();

            while (cachedRowSet1.next()){
                Date forecastDay = cachedRowSet1.getDate("date");
                forecastDays.add(forecastDay);
            }
            cachedRowSet1.close();

            while (cachedRowSet.next()) {

                Date start = cachedRowSet.getDate("start");
                eventDays.add(start);
            }
            cachedRowSet.close();

            for(Date forecastDate : forecastDays){

                DateTime jodaForecast = new DateTime(forecastDate);

                for(Date date : eventDays){

                    DateTime jodaDate = new DateTime(date);

                    int result = DateTimeComparator.getInstance().getDateOnlyInstance().compare(jodaForecast, jodaDate);

                    if(result == 0){
                        System.out.println(jodaForecast.toString() + " is equal than " + jodaDate.toString());
                    }
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public static String getAllEvents() {
        try {
            CachedRowSetImpl cachedRowSet, cachedRowSetNote, cachedRowSetForecast;
            queryBuilder = new QueryBuilder();
            QueryBuilder queryBuilderNote = new QueryBuilder();
            QueryBuilder queryBuilderForecast = new QueryBuilder();
            gson = new Gson();

            cachedRowSet = queryBuilder.selectFrom("events").all().ExecuteQuery();
            cachedRowSetForecast = queryBuilderForecast.selectFrom("forecast").all().ExecuteQuery();


            //Create arraylist of forecast data
            List<ForecastClass> forecastDays = new ArrayList<>();
            while (cachedRowSetForecast.next()){
                ForecastClass fc = new ForecastClass();
                fc.setDateDate(cachedRowSetForecast.getDate("date"));
                fc.setCelsius(cachedRowSetForecast.getString("celsius"));
                fc.setDesc(cachedRowSetForecast.getString("description"));
                forecastDays.add(fc);
            }

           List<Event> eventList = new ArrayList<>();

            while (cachedRowSet.next()) {

                //Map db data to event-model
                Event event = new Event();
                event.setActivityid(cachedRowSet.getString("activity_id"));
                event.setEventid(cachedRowSet.getString("event_id"));
                event.setLocation(cachedRowSet.getString("location"));
                event.setCreatedby(cachedRowSet.getInt("createdby"));
                event.setDateStart(cachedRowSet.getDate("start"));
                event.setDateEnd(cachedRowSet.getDate("end"));
                event.setStrDateStart(cachedRowSet.getString("start"));
                event.setStrDateEnd(cachedRowSet.getString("end"));
                event.setTitle(cachedRowSet.getString("title"));
                event.setText(cachedRowSet.getString("text"));
                event.setCustomevent(cachedRowSet.getBoolean("customevent"));
                event.setCalendarid(cachedRowSet.getInt("CalenderID"));


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
                cachedRowSetNote = queryBuilderNote.selectFrom("notes").where("eventid", "=", cachedRowSet.getString("event_id")).ExecuteQuery();
                while (cachedRowSetNote.next()) {
                    Note note = new Note();
                    note.setCreatedby(cachedRowSetNote.getInt("createdby"));
                    note.setText(cachedRowSetNote.getString("text"));
                    note.setCreatedon(cachedRowSetNote.getTimestamp("created").toString());
                    noteList.add(note);
                }
                cachedRowSetNote.close();
                event.setNoter(noteList);

                eventList.add(event);
            }
            cachedRowSet.close();
            cachedRowSetForecast.close();

            return gson.toJson(eventList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCalendarEventsToDb() {

        try {
            String json = readUrl("http://calendar.cbs.dk/events.php/" + EncryptUserID.getUserId() + "/" + EncryptUserID.getKey() + ".json");

            gson = new Gson();
            Events events = gson.fromJson(json, Events.class);
            queryBuilder = new QueryBuilder();

            String[] fields = {"activity_id", "event_id", "location", "createdby", "start", "end", "title", "text", "customevent", "CalenderID"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (int i = 0; i < events.getEvents().size(); i++) {

                int monthStart = Integer.parseInt(events.getEvents().get(i).getStart().get(1)) + 1;
                String start =
                        events.getEvents().get(i).getStart().get(2) + "-" +
                                monthStart + "-" +
                                events.getEvents().get(i).getStart().get(0) + " " +
                                events.getEvents().get(i).getStart().get(3) + ":" +
                                events.getEvents().get(i).getStart().get(4);

                Date startDate = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(start);
                String strStartDate = sdf.format(startDate);

                int monthEnd = Integer.parseInt(events.getEvents().get(i).getEnd().get(1)) + 1;
                String end =
                        events.getEvents().get(i).getEnd().get(2) + "-" +
                                monthEnd + "-" +
                                events.getEvents().get(i).getEnd().get(0) + " " +
                                events.getEvents().get(i).getEnd().get(3) + ":" +
                                events.getEvents().get(i).getEnd().get(4);
                Date endDate = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(end);
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
                        "1",
                        "1",
                };

                queryBuilder.insertInto("events", fields).values(values).Execute();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
