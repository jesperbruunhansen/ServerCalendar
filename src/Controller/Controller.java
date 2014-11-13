package Controller;

import Config.Config;
import Model.DatabaseInit;
import View.Screen;
import Server.Server;

import java.util.*;

/**
 * Created by jesperbruun on 09/11/14.
 */
public class Controller{

    public Controller(){

        /**
         * Initialize database environment
         * If env. does not exist, create new based on SQL file in res/createDBscript.sql
         */
        //DatabaseInit.init();


        /**
         * Start GUI layer of app.
         */
        //Screen app = new Screen();


        /**
         * Create new server object, to make server run on specified port nr.
         */
        Server server = new Server();
        server.runServer(Config.getServerPort());
    }





}
