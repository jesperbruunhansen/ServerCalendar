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

    private SwitchController switchController = new SwitchController();

    /**
     * WebServer constructor.
     */
    public void runServer(int port) {
        ServerSocket s;
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
        for (; ; ) {
            try {
                // wait for a connection
                Socket remote = s.accept();
                // remote is now the connected socket
                //System.out.println("Connection!");
                BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                PrintWriter out = new PrintWriter(remote.getOutputStream());

                String header = "";
                // read the data sent.
                for (String line; (line = in.readLine()) != null; ) {
                    if (line.isEmpty()) break;
                    header += line + "\n";
                }

                ServerRequestHandler.parseGetParameters(header.substring(0, header.indexOf("\n")));

                if(ServerRequestHandler.getHeaderParams() != null){
                    switchController.keyValuePair(ServerRequestHandler.getHeaderParams().get("id"));
                    System.out.println("Connection!");
                    // Send the response
                    // Send the headers
                    out.println(ServerRequestHandler.getHTTPResponseCode());
                    out.println(ServerRequestHandler.getJSONMIMEType());
                    out.println(ServerRequestHandler.getHTTPServerInfo());
                    // this blank line signals the end of the headers
                    out.println("");
                    // Send the HTML page

                    /*
                    *  SEND JSON CONTENT TO CLIENT
                    */

                    out.println(switchController.getJsonResponse());
                }

                //if(getHeaderParams() != null){
                //    switchController.keyValuePair(getHeaderParams().get("id"));
                //    out.println(switchController.getJsonResponse());
                //}




                out.flush();
                remote.close();
            } catch (Exception e) {
                e.getStackTrace();
            }

        }

    }


}
