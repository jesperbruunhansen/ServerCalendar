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

    protected QueryBuilder queryBuilder;
    protected ResultSet resultSet;

    public void init(){

        /**
         * Start GUI layer of app.
         */
        Screen screen = new Screen();

        new LoginController(screen);
        new WelcomeController(screen);
        new UserListController(screen);

    }


}
