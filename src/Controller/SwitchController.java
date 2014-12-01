package Controller;

import Model.Get.CalendarData;
import Model.Get.NoteData;
import Model.Get.QouteData;
import Model.Get.UserData;
import Model.Forecast.Forecast;
import Model.Post.Calendar;
import Model.Post.Login;
import Model.Post.Events;
import Model.Post.Notes;
import Server.ServerRequestHandler;


/**
 * Created by jesperbruun on 13/11/14.
 * API of our server. The Server class will trigger either the GET or POST method, to determine what action to take,
 * and what response to return.
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

        if(!parameterValue.isEmpty()){
            switch (parameterValue){
                case "getAllEvents" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(CalendarData.getAllEvents(getGetParameterId()));
                    break;
                case "getQuote" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(QouteData.getQuote());
                    break;
                case "getAllNotes" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(NoteData.getAllNotes(getGetParameterId()));
                    break;
                case "getAllCalendars" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(CalendarData.getAllCalendars(getGetParameterId()));
                    break;
                case "getAllUsers" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse(UserData.getAllUsers());
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
                System.out.println("Login has been triggerd");
                Login.authenticateUser(getPostJsonData().trim());
                setJsonResponse(Login.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "addCalendar" :
                Calendar.createCalendar(getPostJsonData().trim());
                setJsonResponse(Calendar.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "deleteCalendar" :
                Calendar.deleteCalendar(getPostJsonData().trim());
                setJsonResponse(Calendar.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "updateCalendar" :
                Calendar.updateCalendar(getPostJsonData().trim());
                setJsonResponse(Calendar.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "shareCalendar" :
                Calendar.shareCalendar(getPostJsonData().trim());
                setJsonResponse(Calendar.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "addEvent" :
                Events.createEvent(getPostJsonData().trim());
                setJsonResponse(Events.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "deleteEvent" :
                Events.deleteEvent(getPostJsonData().trim());
                    setJsonResponse(Events.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "updateEvent" :
                Events.updateEvent(getPostJsonData().trim());
                setJsonResponse(Events.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "addNote" :
                Notes.createNote(getPostJsonData().trim());
                setJsonResponse(Notes.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "deleteNote" :
                Notes.deleteNote(getPostJsonData().trim());
                setJsonResponse(Notes.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            case "updateNote" :
                Notes.updateNote(getPostJsonData().trim());
                setJsonResponse(Notes.getJsonResponse());
                setHTTPResponseCode(HTTP.OK);
                break;
            default:
                setJsonResponse("{\"response\": \"Error\"}");
                setHTTPResponseCode(HTTP.BAD_REQUEST);
                System.out.println("\tError in Post Request");
                break;
        }

    }

    /**
     * Set the response from each switch case
     * @param value
     */
    private static void setJsonResponse(String value) {
        SwitchController.jsonResponse = value;
    }

    /**
     * Get the response from each switch case, for retrieval in our Server class
     * @return
     */
    public static String getJsonResponse(){
        return SwitchController.jsonResponse;
    }

}
