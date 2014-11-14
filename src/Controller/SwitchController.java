package Controller;

import Model.QueryBuild.QueryBuilder;
import Server.ServerRequestHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController extends ServerRequestHandler{

    private QueryBuilder queryBuilder = new QueryBuilder();
    private String jsonResponse;
    private ResultSet rs;

    /**
     * Server calls this method, every time a client is requesting
     * @param value String from GET headers
     */
    public void keyValuePair(String value){

        value = value.trim();

        if(!value.isEmpty()){
            switch (value){
                case "getAllEvents" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse("{This is a json representation of all events}");
                    break;
                case "getJustSomeEvents" :
                    setHTTPResponseCode(HTTP.OK);
                    setJsonResponse("{This is just some of the events}");
                    break;
                default:
                    setHTTPResponseCode(HTTP.BAD_REQUEST);
                    setJsonResponse("Something went wrong!");
                    break;
            }
        }
        else {


        }


    }

    private void setJsonResponse(String value) {
        this.jsonResponse = value;
    }

    public String getJsonResponse(){
        return this.jsonResponse;
    }



}
