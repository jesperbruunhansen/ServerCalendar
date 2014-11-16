package Controller;

import Model.Calendar.Event;
import Model.QueryBuild.QueryBuilder;
import View.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * Created by Casper on 15/11/14.
 */
public class UserListController extends Controller implements ActionListener {

    private Screen screen;

    public UserListController(Screen screen){
        this.screen = screen;
        screen.userList.addListeners(this);
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
            screen.show(Screen.USERLIST);
        }
        
   
        //If Add user button is clicked
        if (e.getSource() == screen.userList.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Delete user button is clicked
        if (e.getSource() == screen.userList.getBtnDeleteUser()) {
            screen.show(Screen.USERLIST);
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
    }
    
    
}
