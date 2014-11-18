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


    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //IF LOGOUT BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnLogout()) {

            //SHOW & HIDE SCREENS
            screen.LoginVisible(true);
            screen.MenuVisible(false);

            //CLEAR SCREENS FOR INFORMATION
            screen.login.setMail("E-mail");
            screen.login.setPassword("Password");
            screen.addUser.clearAddUser();
        }

        //IF USER LIST BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnUserList()) {

            //POPULATE USERLIST TABLE WITH DATA
            screen.userList.setTable(viewmodel.tableData("users"),viewmodel.columnNames("users"));
            screen.show(Screen.USERLIST);
        }

        //IF ADD USER BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //IF CALENDAR BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnCalendars()) {

            //POPULATE CALANDAR TABLE WITH DATA
            screen.calendar.setTable(viewmodel.tableData("calender"),viewmodel.columnNames("calender"));
            screen.show(Screen.CALENDAR);
        }

        //IF EVENTS BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnEvents()) {

            //POPULATE CALANDAR TABLE WITH DATA
            screen.events.setTable(viewmodel.tableData("events"),viewmodel.columnNames("events"));
            screen.show(Screen.EVENTS);
        }

        //IF NOTES BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnNotes()) {

            //POPULATE CALANDAR TABLE WITH DATA
            screen.notes.setTable(viewmodel.tableData("notes"),viewmodel.columnNames("notes"));
            screen.show(Screen.NOTES);
        }
    }

}
