package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jesperbruun on 14/11/14.
 */
public abstract class ServerRequestHandler {

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

    /**
     * Definition of API calls
     */
    protected enum API {
        ID("id"),
        JSON("json");

        private API(String key) {
            this.apiKey = key;
        }

        private String apiKey;

        public String toString() {
            return apiKey;
        }

    }


    private static Map<String, String> paramValue;
    private static List<Map<String, String>> keyParam;
    private static String HTTPHeader;

    private static final String MIME_TYPE       = "Content-Type: application/json";
    private static final String SERVER_INFO     = "Server: Bot";
    private static final String CALL_PARAMETER  = "call?";



    public static void parseHeader(String header){

        paramValue = null;
        keyParam = null;
        paramValue = new HashMap<>();
        keyParam = new ArrayList<>();

        //Split header string by newlines
        String[] headerArray = header.split("\\r?\\n");

        //Grap first 4 characters from header string (GET or POST)
        String httpMethod = headerArray[0].substring(0,4).trim();

        //System.out.println(headerArray[0]);
        System.out.println("HTTP method: " + httpMethod);

        switch (httpMethod){
            case "GET" :
                parseGetParams(headerArray);
                break;
            case "POST" :
                parsePostParams(headerArray);
                break;
            default:
                System.out.println("No HTTP method was found");
        }

    }
    private static void parseGetParams(String[] params){

        System.out.println(params[0]);

        //Browsers creates an extra GET request from their favicon
        //and we dont want to that extra call.
        if(!params[0].contains("favicon")){

            final String GET_CALL = "GET /" + CALL_PARAMETER;

            //Split string, and get clean GET result
            String get[] = params[0].split("HTTP/1.1");

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

                    keyParam.add(paramValue);
                }

                setHTTPResponseCode(HTTP.OK);

                //Console print key -> value
                for(Map<String, String> map : keyParam){
                    for (String key : map.keySet()){
                        System.out.println("\tKey: " + key + " -> " + map.get(key));
                    }
                }

            }
        }
        else {
            keyParam = null;
        }

    }

    private static void parsePostParams(String[] params){

        int length = params.length;
        String lastIndex = params[length - 1];

        String[] values = lastIndex.split("&");

        for(String param : values){

            //Split key and value
            String[] pair = param.split("=");

            //Create new object of Hashmap and insert array in key/value
            paramValue = new HashMap<>();
            paramValue.put(pair[0], pair[1]);
            setHTTPResponseCode(HTTP.OK);

            keyParam.add(paramValue);

        }

        //Console print key -> value
        for(Map<String, String> map : keyParam){
            for (String key : map.keySet()){
                System.out.println("\tKey: " + key + " -> " + map.get(key));
            }
        }

    }


    /**
     * Get HashMap in key and value pair from GET parameters
     * @return
     */
    public static List<Map<String, String>> getHeaderParams(){
        return keyParam;
    }
    public static Map<String, String> getMapParams(){
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
