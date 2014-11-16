package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 16/11/14.
 */
public class NotesController extends Controller implements ActionListener {

    private Screen screen;

    public NotesController(Screen screen){
        this.screen = screen;
        screen.notes.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.notes.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.notes.getBtnUserList()) {
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.notes.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Delete user button is clicked
        if (e.getSource() == screen.notes.getBtnDeleteUser()) {
            screen.show(Screen.USERLIST);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.notes.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.notes.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.notes.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }
    }

}

