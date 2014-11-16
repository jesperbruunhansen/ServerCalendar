package Controller;

import Model.Calendar.GetCalendarData;
import Model.QueryBuild.QueryBuilder;
import Server.ServerRequestHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController extends ServerRequestHandler{

    private String jsonResponse;

    /**
     * Server calls this method, every time a client is requesting
     * @param value String from GET headers
     */
    public void keyValuePair(String value){

        value = value.trim();

        GetCalendarData calendarData = new GetCalendarData();

        if(!value.isEmpty()){
            switch (value){
                case "getAllEvents" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(calendarData.getAllEvents());
                    break;
                case "getJustSomeEvents" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse("{This is just some of the events}");
                    break;
                case "setAllEvents" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse("Alle data er blevet smidt i db");
                    try {
                        //calendarData.setCalendarEventsToDb();
                    }
                    catch (Exception e){

                    }
                    break;
                case "getAllUsers" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(calendarData.getAllUsers());
                    break;
                case "joinTest" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse("OK");
                    calendarData.joinTest();
                    break;
                default:
                    setHTTPResponseCode(HTTP.BAD_REQUEST);
                    setJsonResponse("Something went wrong!");
                    break;
            }
        }
        else {
            setHTTPResponseCode(HTTP.BAD_REQUEST);
            setJsonResponse("{failed}");
        }


    }

    private void setJsonResponse(String value) {
        this.jsonResponse = value;
    }

    public String getJsonResponse(){
        return this.jsonResponse;
    }



}
