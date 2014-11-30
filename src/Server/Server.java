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
                Runnable connectionHandler = new ConnectionHandler(remote);
                new Thread(connectionHandler).start();
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