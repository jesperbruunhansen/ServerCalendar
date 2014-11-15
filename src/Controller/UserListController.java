package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class UserListController extends Controller implements ActionListener {

    private Screen screen;

    public UserListController(Screen screen){
        this.screen = screen;
    //    screen.userList.addListeners(this);
    }


    //ActionListener
    public void actionPerformed(ActionEvent e){

    }

}




