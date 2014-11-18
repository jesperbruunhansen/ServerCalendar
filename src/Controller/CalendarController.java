package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;

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

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //If DELETE BUTTON IS CLICKED
        if (e.getSource() == screen.calendar.getBtnDelete()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            int calendarID = screen.calendar.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            String userString = Integer.toString(calendarID);

            //DELETE THE CALENDAR FROM DATABASE
            //viewmodel.deleteUser(userString);
            showMessageDialog(null, "You wanted to delete calendar " + userString);
        }
    }
}
