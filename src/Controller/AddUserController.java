package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class AddUserController extends Controller implements ActionListener {

    private Screen screen;

    public AddUserController(Screen screen){
        this.screen = screen;
            screen.addUser.addListeners(this);
    }


    //ActionListener
    public void actionPerformed(ActionEvent e){

    }

}
