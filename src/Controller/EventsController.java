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

    private String calendarID;
    private String eventID;

    public EventsController(Screen screen){
        this.screen = screen;
        screen.events.addListeners(this);
    }

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //If CHOOSE BUTTON IS CLICKED
        if (e.getSource() == screen.events.getBtnChoose()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            calendarID = screen.events.getSelectedID();

            //VALIDATING THE USER INPUT
            if(calendarID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select a calendar on the list");
            }

            else{
                //RETRIEVE EVENT LIST FROM DATABASE BASED ON CHOSEN CALENDAR
                screen.events.setTable(viewmodel.tableEvent("events", "CalenderID", calendarID),viewmodel.columnNames("events"));

                //HIDE SHOW BUTTONS & UPDATE LABELS
                screen.events.btnChoose.setVisible(false);
                screen.events.btnDelete.setVisible(true);
                screen.events.lblConfirm.setVisible(false);
                screen.events.lblHead.setText("Events: Please choose an event to delete");
                screen.events.setUserID();
            }

        }

        //If DELETE BUTTON IS CLICKED
        if (e.getSource() == screen.events.getBtnDelete()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            eventID = screen.events.getSelectedID();

            //VALIDATING THE USER INPUT
            if(eventID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select an event on the list");
            }

            else{
                //DELETE THE EVENT FROM DATABASE
                viewmodel.delete("events", "id", eventID);
                showMessageDialog(null, "You wanted to delete event " + eventID);

                //UPDATE TABLE WITH NEW DATA
                screen.events.setTable(viewmodel.tableEvent("events", "CalenderID", calendarID),viewmodel.columnNames("events"));
                screen.events.lblConfirm.setVisible(false);
                screen.events.setUserID();
            }
        }
    }

}

