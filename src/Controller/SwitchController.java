package Controller;

import Model.Calendar.GetCalendarData;
import Model.Forecast.Forecast;
import Model.QueryBuild.QueryBuilder;
import Server.ServerRequestHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController extends ServerRequestHandler{

    private String jsonResponse;

    /**
     * Server calls this method, every time a client is requesting
     * @param mapList String from GET headers
     */
    public void keyValuePair(List<Map<String, String>> mapList){

        String value = null;

        for(Map<String, String> valueMap : mapList){
            for(String key : valueMap.keySet()){
                if(key.equals(API.ID.toString())){
                   value = valueMap.get(key).trim();
                }
            }
        }

        GetCalendarData calendarData = new GetCalendarData();
        Forecast forecast = new Forecast();

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
                    calendarData.setCalendarEventsToDb();
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
                case "forecast" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse("Forecast!");
                    forecast.setForecastToDb();
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
