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
     * WebServer constructor. Will create a ServerSocket and hanging for-loop to wait for a connection the server.
     * When a connection has been accepted, a new thread from Runnable class will be instantiated for multithreading
     * purposes.
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


    /**
     * Set port nr for the server to open  a socket on.
     * @param port
     */
    public void setPortNr(int port) {
        this.portNr = port;
    }

    /**
     * Private get method, to get the current port nr.
     * @return
     */
    private int getPortNr() {
        return portNr;
    }


}