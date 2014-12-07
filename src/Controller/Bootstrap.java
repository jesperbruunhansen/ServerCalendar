package Controller;

import Config.Config;
import Server.Server;
import Model.DatabaseInit;

/**
 * Created by jesperbruun on 15/11/14.
 */

/**
 * Initializer of the server. Constructor will run database initialization
 * to make sure the correct database environment is available.
 */
public class Bootstrap {

    public Bootstrap(){
        /**
         * Initialize database environment
         * If env. does not exist, create new based on SQL file in res/createDBscript.sql
         */
        DatabaseInit.init();

    }

    /**
     * Start the server. Makes sure the controller class with GUI and Logic are instantiated
     */
    public void run() {
        /**
         * Create new screen object
         */
       //Controller controller = new Controller();
       //controller.init();

        /**
         * Create new server object, to make server run on specified port nr.
         */
        Server server = new Server();
        server.setPortNr(Config.getServerPort());
        server.runServer();
    }



}
