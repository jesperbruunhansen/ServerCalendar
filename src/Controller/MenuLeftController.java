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
    private JTable table;

    public MenuLeftController(Screen screen){
        this.screen = screen;
        screen.menuLeft.addListeners(this);
        table = screen.userList.getTable();
    }


    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.menuLeft.getBtnLogout()) {
            screen.LoginVisible(true);
            screen.MenuVisible(false);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.menuLeft.getBtnUserList()) {
            screen.userList.setTable(viewmodel.userData(),viewmodel.columnNames());
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.menuLeft.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.menuLeft.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.menuLeft.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.menuLeft.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }
    }

}
