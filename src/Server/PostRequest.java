package Server;

/**
 * Created by jesperbruun on 20/11/14.
 */
public class PostRequest {

    private static String id;
    private static String jsonData;


    public static void setId(String id){
        PostRequest.id = id;
    }
    public static void setJsonData(String jsonData){
        PostRequest.jsonData = jsonData;
    }

    public static String getId(){
        return id;
    }
    public static String getJsonData(){
        return jsonData;
    }

}
