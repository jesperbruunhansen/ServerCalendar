package Server;

import Controller.SwitchController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jesperbruun on 29/11/14.
 */
public class ConnectionHandler implements Runnable {

    private Socket remote;
    private PrintWriter out;
    private String header = "";
    private char[] inputChars;
    private int charsRead = 0;


    public ConnectionHandler(Socket s) {
        remote = s;
    }

    @Override
    public void run() {
        try {
            inputChars = null;

            System.out.println("ID: " + Thread.currentThread().getId());

            BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
            out = new PrintWriter(remote.getOutputStream());

            int charsRead;
            inputChars = new char[2048];
            if ((charsRead = in.read(inputChars)) != -1) {
                System.out.println("Chars read from stream: " + charsRead);
            }
            header = new String(inputChars);

            //System.out.println(header+"\n");

            //Send header from client and parse parameters
            ServerRequestHandler.parseHeader(header);

            //Get rid of anything else but GET/Post parameters
            if (!ServerRequestHandler.isFavicon) {

                //Set header parameters to GiantSwitch
                if (ServerRequestHandler.isGet) {
                    SwitchController.getRequest();
                } else if (ServerRequestHandler.isPost) {
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
                out.flush();
                remote.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
