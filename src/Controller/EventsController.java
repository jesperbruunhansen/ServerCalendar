package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 16/11/14.
 */
public class EventsController extends Controller implements ActionListener {

    private Screen screen;

    public EventsController(Screen screen){
        this.screen = screen;
        screen.events.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.events.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.events.getBtnUserList()) {
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.events.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Delete user button is clicked
        if (e.getSource() == screen.events.getBtnDeleteUser()) {
            screen.show(Screen.USERLIST);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.events.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.events.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.events.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }
    }

}

