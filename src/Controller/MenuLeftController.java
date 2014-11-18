package Controller;

import Model.ViewModel;
import View.Screen;
import View.UserList;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class MenuLeftController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public MenuLeftController(Screen screen){
        this.screen = screen;
        screen.menuLeft.addListeners(this);
    }


    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.menuLeft.getBtnLogout()) {
            screen.LoginVisible(true);
            screen.MenuVisible(false);
            screen.login.setMail("E-mail");
            screen.login.setPassword("Password");
            screen.addUser.clearAddUser();
        }

        //If User list button is clicked
        if (e.getSource() == screen.menuLeft.getBtnUserList()) {
            screen.userList.setTable(viewmodel.userData("users"),viewmodel.columnNames("users"));
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.menuLeft.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.menuLeft.getBtnCalendars()) {
            screen.calendar.setTable(viewmodel.userData("calender"),viewmodel.columnNames("calender"));
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.menuLeft.getBtnEvents()) {
            screen.events.setTable(viewmodel.userData("events"),viewmodel.columnNames("events"));
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.menuLeft.getBtnNotes()) {
            screen.events.setTable(viewmodel.userData("notes"),viewmodel.columnNames("notes"));
            screen.show(Screen.NOTES);
        }
    }

}
