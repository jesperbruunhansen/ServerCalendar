package Controller;

import View.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Casper on 15/11/14.
 */
public class LoginController extends Controller implements ActionListener, KeyListener, FocusListener {

    private Screen screen;
    private final static String email = "E-mail";
    private final static String password = "Password";

    public LoginController(Screen screen){
        this.screen = screen;
        screen.login.addListeners(this, this, this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e){

        //If login button is clicked
        if(e.getSource() == screen.login.getBtnLogin()){
            login();

        }
    }

    //KeyListener
    //If keypressed
    public void keyPressed(KeyEvent e) {

        //If enter key pressed
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //FocusListeners
    //FocusGanied
    public void focusGained(FocusEvent e) {

        //Username
        if(e.getSource() == screen.login.getFieldUsername()){
            //If username field equals "Email", then set to nothing
            if(screen.login.getUsername().equals(email)){
            		screen.login.setUsername("");
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

    //Focus lost
    public void focusLost(FocusEvent e) {

        if(e.getSource() == screen.login.getFieldUsername()){

            //If email field equals nothing, set text to "Email"
            if(screen.login.getUsername().equals("")){
                screen.login.setUsername(email);
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

    //Login method
    public void login(){

        String user = screen.login.getUsername();
        String pass = screen.login.getPassword();

        if(auth(user, pass)){

        //Send user to Welcome-Screen.
        screen.show(Screen.WELCOME);

        }
        else {
            //Log in failed
            //screen.login.setErrorMessage("Input mismatch!");
            showMessageDialog(null, "Input mismatch!");

        }
    }


    //Admin authenticator
    public boolean auth(String username, String password) {

        try {
            resultSet = queryBuilder.selectFrom("users").all().ExecuteQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("email");
                String pw = resultSet.getString("password");

                if(user.equals(username) && pw.equals(password)){
                    return true;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }

}
