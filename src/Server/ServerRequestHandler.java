package Server;

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
        private String method;

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
    private static String callParameter = "";
    private static String postId = "";
    private static String postJsonData = "";
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

    /**
     * Triggered if a GET requst has been sent from client
     * @param params
     */
    private static void parseGetParams(String[] params){

        final String getPart = "GET /";

        //Browsers creates an extra GET request from their favicon
        //and we dont want to that extra call.
        if(!params[0].contains("favicon")){

            System.out.println(params[0]);
            callParameter = params[0].substring(params[0].indexOf(getPart) + getPart.length(),params[0].indexOf("HTTP")).trim(); //Create substring from HTTP header
            isGet = true;
            setHTTPResponseCode(HTTP.OK);
        }
        else {
            isFavicon = true;
        }

    }


    /**
     * Triggered when client sents POST request
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

            if(pair[0].equals(API.ID)){
                postId = pair[1];
            }
            if(pair[0].equals(API.JSON)){
                postJsonData = pair[1];
            }

        }
        setHTTPResponseCode(HTTP.OK);
    }


    protected static String getGetParameter(){return callParameter;}
    protected static String getPostId(){return postId;}
    protected static String getPostJsonData(){return postJsonData;}

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
