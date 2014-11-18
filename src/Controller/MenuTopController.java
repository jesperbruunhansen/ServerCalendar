package Controller;

import Model.ViewModel;
import View.Screen;
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
        screen.menuTop.addListeners(this);
    }


    //ActionListener
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == screen.menuTop.getBtnBack()) {

        }

        if (e.getSource() == screen.menuTop.getBtnFor()) {

        }
    }

}
