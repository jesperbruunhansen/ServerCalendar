package Server;

import Config.Config;
import Controller.SwitchController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by jesperbruun on 09/11/14.
 */
public class Server {

    /**
     * Definition of API calls
     */
    private enum API {
        ID("id");

        private API(String key) {
            this.apiKey = key;
        }

        private String apiKey;

        public String toString() {
            return apiKey;
        }

    }

    private SwitchController switchController = new SwitchController();
    private int portNr;
    private BufferedReader in;
    private PrintWriter out;
    private String header = "";
    private char[] inputChars;
    private int charsRead = 0;

    /**
     * WebServer constructor.
     */
    public void runServer() {
        ServerSocket s;
        System.out.println("(press ctrl-c to exit)");
        try {
            // create the main server socket
            s = new ServerSocket(getPortNr());
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
                inputChars = null;

                in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                out = new PrintWriter(remote.getOutputStream());


                int charsRead;
                inputChars = new char[2048];
                if ((charsRead = in.read(inputChars)) != -1)
                {
                    System.out.println("Chars read from stream: " + charsRead);
                }
                header = new String(inputChars);

                //System.out.println(header);

                ServerRequestHandler.parseHeader(header);

                out.println("HTTP/1.1 200 OK");
                out.println(ServerRequestHandler.getJSONMIMEType());
                out.println(ServerRequestHandler.getHTTPServerInfo());
                out.println("");

//                    out.println(ServerRequestHandler.getHTTPServerInfo());'
//                ServerRequestHandler.parseGetParameters(header.substring(0, header.indexOf("\n")));
//
//                if(ServerRequestHandler.getHeaderParams() != null){
//                    switchController.keyValuePair(ServerRequestHandler.getHeaderParams().get(API.ID.toString()));
//                    System.out.println("Connection!");
//                    // Send the response
//                    // Send the headers
//                    out.println(ServerRequestHandler.getHTTPResponseCode());
//                    out.println(ServerRequestHandler.getJSONMIMEType());
//                    out.println(ServerRequestHandler.getHTTPServerInfo());
//                    // this blank line signals the end of the headers
//                    out.println("");
//                    // Send the HTML page
//
//                    /*
//                    *  SEND JSON CONTENT TO CLIENT
//                    */
//
//                    out.println(switchController.getJsonResponse());
//                }

                out.flush();
                remote.close();
            } catch (Exception e) {
                e.getStackTrace();
            }

        }

    }

    public void setPortNr(int port) {
        this.portNr = port;
    }

    private int getPortNr() {
        return portNr;
    }


}