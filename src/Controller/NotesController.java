package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Casper on 16/11/14.
 */
public class NotesController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    public NotesController(Screen screen){
        this.screen = screen;
        screen.notes.addListeners(this);
    }

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //If DELETE BUTTON IS CLICKED
        if (e.getSource() == screen.notes.getBtnDelete()) {

            //RECEIVE THE NUMBER OF ROW THE USER CLICKED
            int noteID = screen.notes.getSelectedID();

            //CONVERT THE ID FROM INT TO STRING
            String userString = Integer.toString(noteID);

            //DELETE THE NOTE FROM DATABASE
            //viewmodel.deleteUser(userString);
            showMessageDialog(null, "You wanted to delete note " + userString);
        }
    }

}

