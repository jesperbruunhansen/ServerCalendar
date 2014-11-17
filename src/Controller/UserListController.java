package Controller;

import Model.ViewModel;
import View.Screen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Casper on 15/11/14.
 */
public class UserListController extends Controller implements ActionListener, MouseListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();
    private JTable table;

    public UserListController(Screen screen){
        this.screen = screen;
        screen.userList.addListeners(this, this);
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

        }
    }

    public void mouseClicked(MouseEvent e) {

            System.out.println(e.getSource());

            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();
            //int row = target.getSelectedRow();
            //int column = target.getSelectedColumn();

            System.out.println("ROW: " + row);
            System.out.println("COLUMN: " +  column);

    }

    @Override
    public void mousePressed(MouseEvent e){
        System.out.print("mouse clicked");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.print("mouse clicked");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.print("mouse clicked");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.print("mouse clicked");
    }
}
