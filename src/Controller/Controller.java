package Controller;

import Config.Config;
import Model.DatabaseInit;
import Model.QueryBuild.QueryBuilder;
import View.Screen;
import Server.Server;

import java.sql.ResultSet;
import java.util.*;

/**
 * Created by jesperbruun on 09/11/14.
 */
public class Controller{


    protected Screen screen;
    protected QueryBuilder queryBuilder;
    protected ResultSet resultSet;

    public void init(){

        /**
         * Start GUI layer of app.
         */
        screen = new Screen();
    }


}
