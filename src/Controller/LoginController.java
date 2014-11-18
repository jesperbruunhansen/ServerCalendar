package Controller;

import Model.ViewModel;
import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Casper on 15/11/14.
 */
public class LoginController extends Controller implements ActionListener, KeyListener, FocusListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();

    private final static String email = "E-mail";
    private final static String password = "Password";

    public LoginController(Screen screen){
        this.screen = screen;
        screen.login.addListeners(this, this, this);
    }

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e){

        //LOGIN BUTTON IS CLICKED
        if(e.getSource() == screen.login.getBtnLogin()){
            //Run method
            login();

        }
    }

    //IF KEYLISTENER IS TRIGGERED
    //KEYPRESSED
    public void keyPressed(KeyEvent e) {

        //If enter key pressed
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }

    //KEYTYPED
    public void keyTyped(KeyEvent e) {

    }

    //KEYRELEASE
    public void keyReleased(KeyEvent e) {

    }

    //IF KEYLISTENER IS TRIGGERED
    //FOCUSGAINED
    public void focusGained(FocusEvent e) {

        //Username
        if(e.getSource() == screen.login.getFieldMail()){

            //If username field equals "Email", then set to nothing
            if(screen.login.getMail().equals(email)){
            		screen.login.setMail("");
            }
        }

        //Password
        if(e.getSource() == screen.login.getFieldPassword()){

            //If password field equals "Password", then set to nothing
            if(screen.login.getPassword().equals(password)){
                screen.login.setPassword("");
            }
        }

    }

    //FOCUSLOST
    public void focusLost(FocusEvent e) {

        if(e.getSource() == screen.login.getFieldMail()){

            //If email field equals nothing, set text to "Email"
            if(screen.login.getMail().equals("")){
                screen.login.setMail(email);
            }
        }

        //Password
        if(e.getSource() == screen.login.getFieldPassword()){

            //If password field equals nothing, set text to "Password"
            if(screen.login.getPassword().equals("")){
                screen.login.setPassword(password);
            }
        }
    }

    //LOGIN METHOD
    public void login(){

        String user = screen.login.getMail();
        String pass = screen.login.getPassword();

        //AUTHENTICATE USER
        if(viewmodel.auth(user, pass)){

            //Check if user has ADMIN privileges
            if(viewmodel.authAdm()){

                //Send user to Welcome-Screen.
                screen.LoginVisible(false);
                screen.MenuVisible(true);
            } else {
                //Show error message
                showMessageDialog(null, "You do not have admin privileges");
            }

        }
        else {
            //Log in failed
            showMessageDialog(null, "Input mismatch!");

        }
    }

}
