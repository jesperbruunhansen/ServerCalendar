package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 16/11/14.
 */
public class CalendarController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public CalendarController(Screen screen){
        this.screen = screen;
        screen.calendar.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {
        //If Delete user button is clicked
        if (e.getSource() == screen.calendar.getBtnDelete()) {

            //Receive the number of row the user clicked
            int userID = screen.calendar.getUserID();

            //Receive the number of row the user clicked
            String userString = Integer.toString(userID);

            //Delete the user from the database
            System.out.print("You wanted to delete event " + userString);
            //viewmodel.deleteUser(userString);

        }
    }

}
