package Controller;

import View.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class WelcomeController extends Controller implements ActionListener {

    private Screen screen;

    public WelcomeController(Screen screen){
        this.screen = screen;
        screen.welcome.addListeners(this);
    }



    //ActionListener
    public void actionPerformed(ActionEvent e){

        //If login button is clicked
        if(e.getSource() == screen.welcome.getBtnLogout()){
            screen.show(Screen.LOGIN);
        }
    }

}
