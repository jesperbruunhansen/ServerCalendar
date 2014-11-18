package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 16/11/14.
 */
public class CalendarController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public CalendarController(Screen screen){
        this.screen = screen;
        screen.calendar.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

    }

}
