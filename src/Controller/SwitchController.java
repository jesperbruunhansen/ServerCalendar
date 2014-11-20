package Controller;

import Model.Calendar.GetCalendarData;
import Model.Forecast.Forecast;
import Server.ServerRequestHandler;

import java.net.URLDecoder;

/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController extends ServerRequestHandler{

    private String jsonResponse;

    /**
     * When a GET request from a client has been registered.
     * The parameter value is set in ServerRequestHandler and passed back
     * to Server-class, which eventually calls this method
     */
    public void getRequest(){

        String parameterValue = getGetParameter();
        GetCalendarData calendarData = new GetCalendarData();
        Forecast forecast = new Forecast();

        if(!parameterValue.isEmpty()){
            switch (parameterValue){
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
                    System.out.println("wrong parameter given");
                    setHTTPResponseCode(HTTP.BAD_REQUEST);
                    setJsonResponse("{wrong parameter given}");
                    break;
            }
        }
        else {
            System.out.println("value is empty");
            setHTTPResponseCode(HTTP.BAD_REQUEST);
            setJsonResponse("{no parameters was given}");
        }

    }

    /**
     * Triggered when server gets a POST request from Client.
     * getPostId is inherited from ServerRequestHandler
     */
    public void postRequest(){

        System.out.println(getPostJsonData());

        switch (getPostId()){
            case "login" :
                setJsonResponse("Login method has been requested");
                setHTTPResponseCode(HTTP.OK);
                break;
            case "addNewUser" :
                setJsonResponse("New user has been requested");
                setHTTPResponseCode(HTTP.OK);
                break;
            default:
                setJsonResponse("Somethings bad has happened");
                setHTTPResponseCode(HTTP.BAD_REQUEST);
                System.out.println("\tError in Post Request");
                break;
        }

    }


    private void setJsonResponse(String value) {
        this.jsonResponse = value;
    }

    public String getJsonResponse(){
        return this.jsonResponse;
    }



}
