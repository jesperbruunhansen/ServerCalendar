package Controller;

/**
 * Created by jesperbruun on 13/11/14.
 */
public class SwitchController {

    public enum API {
        GET_ALL_EVENTS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        SERVER_ERROR(500, "Internal Server Error");

        private int statusCode;
        private String statusMessage;

        private API(int statusCode, String  statusMessage){
            this.statusCode = statusCode;
            this.statusMessage = statusMessage;
        }

        public int getStatusCode(){
            return statusCode;
        }
        public String getStatusMessage(){
            return statusMessage;
        }


    }

    private String jsonResponse;

    /**
     * Server calls this method, every time a client is requesting
     * @param value String from GET headers
     */
    public void keyValuePair(String value){

        value = value.trim();

        if(!value.isEmpty()){
            switch (value){
                case "getAllEvents" :
                    setJsonResponse("{This is a json representation of all events}");
                    break;
                case "getJustSomeEvents" :
                    setJsonResponse("{This is just some of the events}");
                    break;
                default:
                    setJsonResponse("You mad a bad request brah");
                    break;
            }
        }
        else {
            setJsonResponse("You made a bad reuest brah");
        }


    }

    private void setJsonResponse(String value) {
        this.jsonResponse = value;
    }

    public String getJsonResponse(){
        return this.jsonResponse;
    }



}
