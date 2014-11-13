package Controller;

/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController {

    private enum API {
        USER,
        EVENT,
        NOTE,
        ID
    }
    private String getAllEvents;


    /**
     * Server calls this method, every time a client is requesting
     * @param key String from GET headers
     * @param value String from GET headers
     */
    public void keyValuePair(String key, String value){

        System.out.println("VALUE = " + value);


        //if(key.toLowerCase().equals("id")){
        //    API id = API.ID;
        //    switchMethod(Integer.valueOf(value));
        ///}
        //else {
        //    //return error no API found
        //}


    }

    public void switchMethod(int value) {

        System.out.print("SwitchMethod has run!");

    }

    public String getJsonResponse(){

        return this.getAllEvents;

    }



}
