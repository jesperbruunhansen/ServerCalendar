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


            //RECIEVE THE NUMBER OF ROW THE USER CLICKED
            String UserID = screen.userList.getUserID();

            //VALIDATING THE USER INPUT
                if(UserID.equals("0")){
                    //User failed to select an item
                    showMessageDialog(null, "Please select a user on the list");
                }

                else{
                    //DELETE THE USER FROM DATABASE
                    viewmodel.delete("users", "userid", UserID);
                    showMessageDialog(null, "User with ID " + UserID + " is now deleted");

                    //UPDATE TABLE WITH NEW DATA
                    screen.userList.setTable(viewmodel.tableData("users"),viewmodel.columnNames("users"));
                    screen.userList.lblConfirm.setVisible(false);
                    screen.userList.setUserID();
                }

        }

    }

}
