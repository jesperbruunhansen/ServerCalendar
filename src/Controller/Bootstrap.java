package Controller;

import Config.Config;
import Server.Server;

/**
 * Created by jesperbruun on 15/11/14.
 */
public class Bootstrap {

    public Bootstrap(){

        /**
         * Initialize database environment
         * If env. does not exist, create new based on SQL file in res/createDBscript.sql
         */
        //DatabaseInit.init();

        //Controller controller = new Controller();
        //controller.init();


        /**
         * Create new server object, to make server run on specified port nr.
         */
        Server server = new Server();
        server.runServer(Config.getServerPort());
    }

}
