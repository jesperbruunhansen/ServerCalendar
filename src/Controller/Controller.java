package Controller;

import View.Screen;
import Model.QueryBuild.QueryBuilder;
import java.sql.ResultSet;
import Model.ViewModel;

/**
 * Created by jesperbruun on 09/11/14.
 */
public class Controller{

    protected ViewModel viewModel = new ViewModel();
    //protected QueryBuilder queryBuilder = new QueryBuilder();
    //protected ResultSet resultSet;

    public void init(){

        /**
         * Start GUI layer of app.
         */
        Screen screen = new Screen();

        new LoginController(screen);
        new WelcomeController(screen);
        new UserListController(screen);
        new AddUserController(screen);
        new CalendarController(screen);
        new EventsController(screen);
        new NotesController(screen);

    }


}
