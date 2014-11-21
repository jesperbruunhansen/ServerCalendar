package Controller;

import Model.Calendar.GetCalendarData;
import Model.Forecast.Forecast;
import Model.Post.Login;
import Server.ServerRequestHandler;
import sun.rmi.runtime.Log;


/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController extends ServerRequestHandler{

    private static String jsonResponse;

    /**
     * When a GET request from a client has been registered.
     * The parameter value is set in ServerRequestHandler and passed back
     * to Server-class, which eventually calls this method
     */
    public static void getRequest(){

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
                    System.out.println("\twrong parameter given");
                    setHTTPResponseCode(HTTP.BAD_REQUEST);
                    setJsonResponse("{wrong parameter given}");
                    break;
            }
        }
        else {
            System.out.println("\tvalue is empty");
            setHTTPResponseCode(HTTP.BAD_REQUEST);
            setJsonResponse("{no parameters was given}");
        }

    }

    /**
     * Triggered when server gets a Post request from Client.
     * getPostId is inherited from ServerRequestHandler
     */
    public static void postRequest(){

        switch (getPostId()){
            case "login" :
                Login.authenticateUser(getPostJsonData().trim());
                setJsonResponse(Login.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "addNewUser" :
                System.out.println(getPostJsonData());
                setJsonResponse("{\"code\": \"New User\"}");
                setHTTPResponseCode(HTTP.OK);
                break;
            default:
                setJsonResponse("{\"response\": \"Error\"}");
                setHTTPResponseCode(HTTP.BAD_REQUEST);
                System.out.println("\tError in Post Request");
                break;
        }

    }

    private static void setJsonResponse(String value) {
        SwitchController.jsonResponse = value;
    }
    public static String getJsonResponse(){
        return SwitchController.jsonResponse;
    }

}
