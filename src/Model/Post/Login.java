package Model.Post;

import Model.Calendar.Event;
import Model.Calendar.Note;
import Model.Forecast.Forecast;
import Model.Forecast.ForecastClass;
import Model.Model;
import Model.QueryBuild.QueryBuilder;
import com.google.gson.Gson;
import com.sun.rowset.CachedRowSetImpl;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesperbruun on 21/11/14.
 */
public class Login extends Model {

    private static CachedRowSetImpl rs;
    private static String jsonResponse;
    private static Boolean isAuthenticated;


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
                    //jsonResponse = "{\"message\": \"LOGIN OK\"}";
                    isAuthenticated = true;
                    getEvents(userId);
                    break;
                }
                else{
                    jsonResponse = "{\"authentication\": \"falsed\"}";
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }


    public static void getEvents(String id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Gson gson = new Gson();
        List<Event> eventList = new ArrayList<>();

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


                ps = doQuery("SELECT * FROM events WHERE calendarid IN (SELECT calendarid FROM userevents WHERE userid = ?);");
                ps.setString(1, id);
                rs = ps.executeQuery();


                while (rs.next()){

                    Event event = new Event();
                    event.setActivityid(rs.getString("activity_id"));
                    event.setEventid(rs.getString("event_id"));
                    event.setLocation(rs.getString("location"));
                    event.setCreatedby(rs.getInt("createdby"));
                    event.setDateStart(rs.getDate("start"));
                    event.setDateEnd(rs.getDate("end"));
                    event.setStrDateStart(rs.getString("start"));
                    event.setStrDateEnd(rs.getString("end"));
                    event.setTitle(rs.getString("title"));
                    event.setText(rs.getString("text"));
                    event.setCustomevent(rs.getBoolean("customevent"));
                    event.setCalendarid(rs.getInt("calendarid"));


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
                    CachedRowSetImpl cachedRowSetNote = queryBuilderNote.selectFrom("notes").where("eventid", "=", rs.getString("event_id")).ExecuteQuery();
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
                cachedRowSetForecast.close();
            }

            //Map to ClientResponse object
            ClientResponse clientResponse = new ClientResponse();
            clientResponse.setEventList(eventList);
            clientResponse.setUser_id(id);

            //Serialize object to json and set to response
            jsonResponse = gson.toJson(clientResponse);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
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
class ClientResponse {
    private List<Event> events = new ArrayList<>();
    private String userid;

    public void setUser_id(String user_id) {
        this.userid = user_id;
    }

    public void setEventList(List<Event> eventList) {
        this.events = eventList;
    }


}

