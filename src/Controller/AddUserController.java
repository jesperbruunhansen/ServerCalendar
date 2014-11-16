package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class AddUserController extends Controller implements ActionListener {

    private Screen screen;

    public AddUserController(Screen screen){
        this.screen = screen;
        screen.addUser.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.addUser.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.addUser.getBtnUserList()) {
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.addUser.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Delete user button is clicked
        if (e.getSource() == screen.addUser.getBtnDeleteUser()) {
            screen.show(Screen.USERLIST);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.addUser.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.addUser.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.addUser.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }
    }

}
