package Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jesperbruun on 14/11/14.
 */
public class ServerRequestHandler {

    protected enum HTTP {
        OK(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        SERVER_ERROR(500, "Internal Server Error");

        private int statusCode;
        private String statusMessage;

        private HTTP(int statusCode, String  statusMessage){
            this.statusCode = statusCode;
            this.statusMessage = statusMessage;
        }

        public int getStatusCode(){
            return statusCode;
        }
        public String getStatusMessage(){
            return statusMessage;
        }

        public String toString(){
            return "HTTP/1.1 " + getStatusCode() + " " + getStatusMessage();
        }

    }

    private static Map<String, String> paramValue;
    private static String HTTPHeader;

    private static final String MIME_TYPE       = "Content-Type: application/json";
    private static final String SERVER_INFO     = "Server: Bot";
    private static final String CALL_PARAMETER  = "call?";


    /**
     * Read response from client and set key and values from GET parameters
     * @param response
     */
    public static void parseGetParameters(String response) {
        if(!response.toLowerCase().contains("favicon")) {
            final String GET_CALL = "GET /" + CALL_PARAMETER;

            //Split string, and get clean GET result
            String get[] = response.split("HTTP/1.1");

            //Only grab the piece of string that contains our call parameter
            if (get[0].toLowerCase().contains(CALL_PARAMETER)) {

                //Split by &
                String[] parameters = get[0].substring(GET_CALL.length()).split("&");

                for (String param : parameters) {

                    //Split key and value
                    String[] pair = param.split("=");

                    //Create new object of Hashmap and insert array in key/value
                    paramValue = new HashMap<>();
                    paramValue.put(pair[0], pair[1]);
                    setHTTPResponseCode(HTTP.OK);
                }

            }
        }
        else {
            setHTTPResponseCode(HTTP.BAD_REQUEST);
            paramValue = null;
        }
    }


    /**
     * Get HashMap in key and value pair from GET parameters
     * @return
     */
    public static Map<String, String> getHeaderParams(){
        return paramValue;
    }

    public static String getHTTPResponseCode(){
        return ServerRequestHandler.HTTPHeader;
    }

    public static String getJSONMIMEType(){
        return ServerRequestHandler.MIME_TYPE;
    }

    public static String getHTTPServerInfo(){
        return ServerRequestHandler.SERVER_INFO;
    }

    protected static void setHTTPResponseCode(HTTP code){
        ServerRequestHandler.HTTPHeader = code.toString();
    }


}
