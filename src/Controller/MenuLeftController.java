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
            screen.MenuTopVisible(false);
            screen.show(Screen.WELCOME);

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

            //CLEAR SCREEN FOR INFORMATION
            screen.userList.lblConfirm.setVisible(false);
            screen.userList.setUserID();
        }

        //IF ADD USER BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);

            //CLEAR SCREEN FOR INFORMATION
            screen.addUser.clearAddUser();

        }

        //IF CALENDAR BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnCalendars()) {

            //POPULATE CALENDAR TABLE WITH DATA
            screen.calendar.setTable(viewmodel.tableData("calendar"),viewmodel.columnNames("calendar"));
            screen.show(Screen.CALENDAR);

            //CLEAR SCREEN FOR INFORMATION
            screen.calendar.lblConfirm.setVisible(false);
            screen.calendar.setUserID();
        }

        //IF EVENTS BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnEvents()) {

            //POPULATE CALENDAR TABLE WITH DATA
            screen.events.setTable(viewmodel.tableData("calendar"),viewmodel.columnNames("calendar"));
            screen.show(Screen.EVENTS);

            //CLEAR SCREEN FOR INFORMATION
            screen.events.btnChoose.setVisible(true);
            screen.events.btnDelete.setVisible(false);
            screen.events.lblConfirm.setVisible(false);
            screen.events.lblHead.setText("Events: Please choose a calendar from the list");
            screen.events.setUserID();
        }

        //IF NOTES BUTTON IS CLICKED
        if (e.getSource() == screen.menuLeft.getBtnNotes()) {

            //POPULATE CALENDAR TABLE WITH DATA
            screen.notes.setTable(viewmodel.tableData("calendar"),viewmodel.columnNames("calendar"));
            screen.show(Screen.NOTES);

            //CLEAR SCREEN FOR INFORMATION
            screen.notes.btnChooseCal.setVisible(true);
            screen.notes.btnChooseEvent.setVisible(false);
            screen.notes.btnDelete.setVisible(false);
            screen.notes.lblConfirm.setVisible(false);
            screen.notes.lblHead.setText("Notes: Please choose a calendar from the list");
            screen.notes.setUserID();
        }
    }

}
