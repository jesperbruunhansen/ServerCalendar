package Controller;

import Model.ViewModel;
import View.Screen;
import View.UserList;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Casper on 15/11/14.
 */
public class UserListController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public UserListController(Screen screen){
        this.screen = screen;
        screen.userList.addListeners(this);
    }

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //DELETE BUTTON CLICKED
        if (e.getSource() == screen.userList.getBtnDelete()) {

            //Receive the number of row the user clicked
            int userID = screen.userList.getUserID();

            //CONVERT THE ID FROM INT TO STRING
            String userString = Integer.toString(userID);

                //Validating the user input
                if(userString.equals("0")){
                    //User failed to select an item
                    showMessageDialog(null, "Please select a user on the list");
                }

                else{
                    //DELETE THE USER FROM DATABASE
                    //viewmodel.deleteUser(userString);
                    showMessageDialog(null, "User with ID " + userString + " is now deleted");
                }

        }

    }

}
