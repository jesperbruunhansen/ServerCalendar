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
            int calid = screen.calendar.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            String calendarID = Integer.toString(calid);

            //VALIDATING THE USER INPUT
            if(calendarID.equals("0")){
                //User failed to select an item
                showMessageDialog(null, "Please select a calendar on the list");
            }

            else{
                //DELETE THE CALENDAR FROM DATABASE
                //viewmodel.delete("Calender", "CalenderID", calendarID);
                showMessageDialog(null, "You wanted to delete calendar " + calendarID);

                //UPDATE TABLE WITH NEW DATA
                screen.calendar.setTable(viewmodel.tableData("calender"),viewmodel.columnNames("calender"));

            }
        }
    }
}
