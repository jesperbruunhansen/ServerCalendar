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

        //If Delete user button is clicked
        if (e.getSource() == screen.userList.getBtnDelete()) {

        }
    }

}
