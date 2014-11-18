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

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If Delete user button is clicked
        if (e.getSource() == screen.userList.getBtnDelete()) {

            //Receive the number of row the user clicked
            int userID = screen.userList.getUserID();

            //Receive the number of row the user clicked
            String userString = Integer.toString(userID);
            System.out.print(userString);

                //Validating the user input
                if(userString.equals("0")){
                    showMessageDialog(null, "Please select a user on the list");
                }

                else{

                    //viewmodel.deleteUser(userString);
                    showMessageDialog(null, "User with ID " + userString + " is now deleted");
                }

        }

    }

}
