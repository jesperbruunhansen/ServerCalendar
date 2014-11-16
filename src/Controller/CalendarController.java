package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 16/11/14.
 */
public class CalendarController extends Controller implements ActionListener {

    private Screen screen;

    public CalendarController(Screen screen){
        this.screen = screen;
        screen.calendar.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.calendar.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.calendar.getBtnUserList()) {
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.calendar.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }


        //If Calendar button is clicked
        if (e.getSource() == screen.calendar.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.calendar.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.calendar.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }
    }

}
