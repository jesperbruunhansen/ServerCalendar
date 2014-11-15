package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class LoginController extends Controller implements ActionListener {

    private Screen screen;

    public LoginController(Screen screen){
        this.screen = screen;
        screen.login.addListeners(this);
    }



    //ActionListener
    public void actionPerformed(ActionEvent e){

        //If login button is clicked
        if(e.getSource() == screen.login.getBtnLogin()){
            screen.show(Screen.WELCOME);
        }
    }

}
