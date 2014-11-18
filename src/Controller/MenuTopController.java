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
public class MenuTopController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public MenuTopController(Screen screen){
        this.screen = screen;
        //screen.menuTop.addListeners(this);
    }


    //ActionListener
    public void actionPerformed(ActionEvent e) {

    }

}
