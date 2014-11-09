package Controller;

import Config.Config;
import Model.DatabaseInit;
import View.Screen;

/**
 * Created by jesperbruun on 09/11/14.
 */
public class Controller {


    public Controller(){

        /**
         * Initialize database environment
         * If env. does not exist, create new based on SQL file in res/createDBscript.sql
         */
        DatabaseInit.init();




        Screen app = new Screen();

    }



}
