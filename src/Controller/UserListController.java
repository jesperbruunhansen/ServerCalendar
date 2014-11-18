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
public class UserListController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();
    private JTable table;

    public UserListController(Screen screen){
        this.screen = screen;
        screen.userList.addListeners(this);
        table = screen.userList.getTable();
    }


    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.userList.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.userList.getBtnUserList()) {
            screen.userList.setTable(viewmodel.userData(),viewmodel.columnNames());
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.userList.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.userList.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.userList.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.userList.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }

        //If Delete user button is clicked
        if (e.getSource() == screen.userList.getBtnDeleteUser()) {
            System.out.print(screen.userList.getLblDelete());
        }
    }

}
