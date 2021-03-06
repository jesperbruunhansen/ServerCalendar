package Server;

import java.net.URLDecoder;

/**
 * Created by jesperbruun on 14/11/14.
 */
public abstract class ServerRequestHandler {

    /**
     * Definition of HTTP response codes
     */
    protected enum HTTP {
        OK(200, "OK"),
        CREATED(201, "Created"),
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
        JSON("jsonData");

        private API(String key) {
            this.apiKey = key;
        }

        private String apiKey;

        public String toString() {
            return apiKey;
        }

    }


    private static String HTTPHeader;

    private static final String MIME_TYPE       = "Content-Type: application/json";
    private static final String SERVER_INFO     = "Server: Bot";
    private static String callParameter;
    private static String callParameterId;
    private static String postId = "";
    private static String postJsonData;
    public  static boolean isPost;
    public static boolean isGet;
    public static boolean isFavicon;


    /**
     * Method to handle HTTP request from Client
     * @param header
     */
    public static void parseHeader(String header){

        isPost = false;
        isGet = false;
        isFavicon = false;

        //Split header string by newlines
        String[] headerArray = header.split("\\r?\\n");

        //Grap first 4 characters from header string (GET or Post)
        String httpMethod = headerArray[0].substring(0,4).trim();

        //System.out.println(headerArray[0]);
       // System.out.println("HTTP method: " + httpMethod);

        switch (httpMethod){
            case "GET" :
                parseGetParams(headerArray);
                break;
            case "POST" :
                parsePostParams(headerArray);
                break;
            default:
                System.err.println("No HTTP method was found");
                break;
        }

    }

    /**
     * Triggered if a GET requst has been sent from client
     * @param params
     */
    private static void parseGetParams(String[] params){

        final String getPart = "GET /";

        //GET /getAllNotes/12 HTTP/1.1

        //Browsers creates an extra GET request from their favicon
        //and we dont want to that extra call.
        String getString = params[0];

        if(!params[0].contains("favicon")){
            callParameter = getString.substring(getString.indexOf(getPart) + getPart.length(),getString.indexOf("HTTP")).trim(); //Create substring from HTTP header

            if(callParameter.contains("/")){
                String[] s = callParameter.split("/");

                callParameter = s[0].trim();
                callParameterId = s[1].trim();
            }
            else {
                callParameterId = "";
            }
            isGet = true;
        }
        else {
            isFavicon = true;
        }

    }


    /**
     * Triggered when client sents Post request
     * @param params
     */
    private static void parsePostParams(String[] params){
        isPost = true;

        int length = params.length;
        String lastIndex = params[length - 1];

        String[] values = lastIndex.split("&");

        for(String param : values){

            //Split key and value
            String[] pair = param.split("=");

            if(pair[0].trim().equals(API.ID.toString())){
                postId = pair[1];
            }
            if(pair[0].trim().equals(API.JSON.toString())){
                postJsonData = URLDecoder.decode(pair[1]);
            }
        }
    }

    /**
     * Retrieve the GET parameter from client request
     * @return GET parameter as string
     */
    protected static String getGetParameter(){return callParameter;}

    /**
     * Get the GET parameter ID from client request
     * @return GET parameter ID as string
     */
    protected static String getGetParameterId(){return callParameterId;}

    /**
     * Get the POST id from client request
     * @return POST id as string
     */
    protected static String getPostId(){return postId;}

    /**
     * Get the JSON data from a post request.
     * @return POST request in JSON format as string
     */
    protected static String getPostJsonData(){return postJsonData;}

    /**
     * Get HTTP response code from enums
     * @return http response as string
     */
    public static String getHTTPResponseCode(){
        return ServerRequestHandler.HTTPHeader;
    }

    /**
     * Get MIME type for header in response.
     * @return MIME type as string
     */
    public static String getJSONMIMEType(){
        return ServerRequestHandler.MIME_TYPE;
    }

    /**
     * Get HTTP Server info for header in response
     * @return info as string
     */
    public static String getHTTPServerInfo(){
        return ServerRequestHandler.SERVER_INFO;
    }

    /**
     * Set HTTP response code from sub-classes of this class. Can be called from any class that inherits this class
     * @param code
     */
    protected static void setHTTPResponseCode(HTTP code){
        ServerRequestHandler.HTTPHeader = code.toString();
    }




}
