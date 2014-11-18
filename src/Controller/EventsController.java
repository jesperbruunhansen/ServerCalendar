package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Casper on 16/11/14.
 */
public class EventsController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public EventsController(Screen screen){
        this.screen = screen;
        screen.events.addListeners(this);
    }

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //If DELETE BUTTON IS CLICKED
        if (e.getSource() == screen.events.getBtnDelete()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            int calendarID = screen.events.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            String userString = Integer.toString(calendarID);

            //DELETE THE EVENT FROM DATABASE
            //viewmodel.deleteUser(userString);
            showMessageDialog(null, "You wanted to delete event " + userString);
        }
    }

}

