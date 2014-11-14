package Server;

import Config.Config;
import Controller.SwitchController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jesperbruun on 09/11/14.
 */
public class Server {

    private static Map<String, String> paramValue;
    private static final String CALL_PARAMETER = "call?";
    private String idValue;
    private SwitchController switchController = new SwitchController();


    /**
     * WebServer constructor.
     */
    public void runServer(int port) {
        ServerSocket s;

        //System.out.println("Webserver starting up on port " + port);
        System.out.println("(press ctrl-c to exit)");
        try {
            // create the main server socket
            s = new ServerSocket(port);
            System.out.println("Opening socket on port " + s.getLocalPort());
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return;
        }

        System.out.println("Waiting for connection");
        for (;;) {
            try {
                // wait for a connection
                Socket remote = s.accept();
                // remote is now the connected socket
                //System.out.println("Connection!");
                BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                PrintWriter out = new PrintWriter(remote.getOutputStream());
                System.out.println("Connection!");

                String header = "";
                // read the data sent.
                for (String line; (line = in.readLine()) != null;) {
                    if (line.isEmpty()) break;
                   header += line;
                }

                parseGetParameters(header);

                if(getHeaderParams() != null){
                    switchController.keyValuePair(getHeaderParams().get("id"));
                    out.println(switchController.getJsonResponse());
                }

                // Send the response
                // Send the headers
                out.println("HTTP/1.0 200 OK");
                out.println("Content-Type: application/json");
                out.println("Server: Bot");
                // this blank line signals the end of the headers
                out.println("");
                // Send the HTML page


                out.flush();
                remote.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

    }


    /**
     * Read response from client and set key and values from GET parameters
     * @param response
     */
    private static void parseGetParameters(String response) {

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
            }

        }
        else {
            //Set to null, if GET request for some reason is not from an API call
            paramValue = null;
        }
    }

    /**
     * Get HashMap in key and value pair from GET parameters
     * @return
     */
    public Map<String, String> getHeaderParams(){
        return paramValue;
    }




}
