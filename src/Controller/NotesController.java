package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Casper on 16/11/14.
 */
public class NotesController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    private String calendarID;
    private String eventID;
    private String noteID;

    public NotesController(Screen screen){
        this.screen = screen;
        screen.notes.addListeners(this);
    }

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        /**
         * CALENDARS
         */
        //If DELETE CALENDAR BUTTON IS CLICKED
        if (e.getSource() == screen.notes.getBtnChooseCal()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            int calid = screen.notes.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            calendarID = Integer.toString(calid);

            //VALIDATING THE USER INPUT
            if(calendarID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select a calendar on the list");
            }

            else{
                //RETRIEVE EVENT LIST FROM DATABASE BASED ON CHOSEN CALENDAR
                screen.notes.setTable(viewmodel.tableEvent("events", "CalenderID", calendarID),viewmodel.columnNames("events"));

                //HIDE SHOW BUTTONS & UPDATE LABELS
                screen.notes.btnChooseCal.setVisible(false);
                screen.notes.btnChooseEvent.setVisible(true);
                screen.notes.lblConfirm.setVisible(false);
                screen.notes.lblHead.setText("Notes: Please choose an event to delete");
            }

        }

        /**
         * EVENTS
         */

        //If CHOOSE EVENT BUTTON IS CLICKED
        if (e.getSource() == screen.notes.getBtnChooseEvent()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            int evid = screen.notes.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            eventID = Integer.toString(evid);

            //VALIDATING THE USER INPUT
            if(eventID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select an event on the list");
            }

            else{
                //RETRIEVE EVENT LIST FROM DATABASE BASED ON CHOSEN CALENDAR
                screen.notes.setTable(viewmodel.tableEvent("notes", "eventid", eventID),viewmodel.columnNames("notes"));

                //HIDE SHOW BUTTONS & UPDATE LABELS
                screen.notes.btnChooseEvent.setVisible(false);
                screen.notes.btnDelete.setVisible(true);
                screen.notes.lblConfirm.setVisible(false);
                screen.notes.lblHead.setText("Notes: Please choose a note to delete");
            }

        }

        /**
         * NOTES
         */
        //If DELETE BUTTON IS CLICKED
        if (e.getSource() == screen.notes.getBtnDelete()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            int notid = screen.notes.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            noteID = Integer.toString(notid);

            //VALIDATING THE USER INPUT
            if(noteID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select note on the list");
            }

            else{
                //DELETE THE EVENT FROM DATABASE
                //viewmodel.delete("notes", "noteid", noteID);
                showMessageDialog(null, "You wanted to delete note " + noteID);

                //UPDATE TABLE WITH NEW DATA
                screen.notes.setTable(viewmodel.tableEvent("notes", "eventid", eventID),viewmodel.columnNames("notes"));
            }
        }
    }

}

