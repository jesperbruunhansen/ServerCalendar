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
            String calendarID = screen.calendar.getSelectedID();

            //VALIDATING THE USER INPUT
            if(calendarID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select a calendar on the list");
            }

            else{
                //DELETE THE CALENDAR FROM DATABASE
                viewmodel.delete("calendar", "calendarid", calendarID);
                showMessageDialog(null, "You deleted calendar with ID " + calendarID);

                //UPDATE TABLE WITH NEW DATA
                screen.calendar.setTable(viewmodel.tableData("calendar"),viewmodel.columnNames("calendar"));
                screen.calendar.lblConfirm.setVisible(false);
                screen.calendar.setUserID();

            }
        }
    }
}
