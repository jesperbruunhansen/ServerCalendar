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

    /**
     * Take a socket object, when a new instance is created. The socket object is passed from the Server class
     * @param s
     */
    public ConnectionHandler(Socket s) {
        remote = s;
    }

    /**
     * Run-method implemented by the Runnable interface. The method are called from the Server class, and takes care
     * of the multi-threading purposes of our server.
     * Run-method is a backbone method, taking care of request from the clients, passing through the application
     * and sending correct responses back the client.
     */
    @Override
    public void run() {
        try {
            inputChars = null;

            BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
            out = new PrintWriter(remote.getOutputStream());

            int charsRead;
            inputChars = new char[1024];
            if ((charsRead = in.read(inputChars)) != -1) {
                System.out.println("Chars read from stream: " + charsRead);
            }
            header = new String(inputChars);

            System.out.println(header+"\n");

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
