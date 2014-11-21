package Server;

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

    private int portNr;
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

                BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
                out = new PrintWriter(remote.getOutputStream());


                int charsRead;
                inputChars = new char[2048];
                if ((charsRead = in.read(inputChars)) != -1)
                {
                    System.out.println("Chars read from stream: " + charsRead);
                }
                header = new String(inputChars);
                System.out.println(header);

                //Send header from client and parse parameters
                ServerRequestHandler.parseHeader(header);

                //Get rid of anything else but GET/Post parameters
                if(!ServerRequestHandler.isFavicon){

                    //Set header parameters to GiantSwitch
                    if(ServerRequestHandler.isGet){
                        SwitchController.getRequest();
                    }
                    else if(ServerRequestHandler.isPost){
                        SwitchController.postRequest();
                    }

                    // Send the response
                    // Send the headers
                    out.println(ServerRequestHandler.getHTTPResponseCode());
                    out.println(ServerRequestHandler.getJSONMIMEType());
                    out.println(ServerRequestHandler.getHTTPServerInfo());

                    // this blank line signals the end of the headers
                    out.println("");

                    // Send JSON to the page
                    out.println(SwitchController.getJsonResponse());
                }

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