package Controller;

import Model.ViewModel;
import View.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 15/11/14.
 */
public class AddUserController extends Controller implements ActionListener {

    private Screen screen;
    private ViewModel viewmodel = new ViewModel();
    private String selectedRole = "empty";

    public AddUserController(Screen screen){
        this.screen = screen;
        screen.addUser.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If Create user is clicked
        if (e.getSource() == screen.addUser.getBtnCreateUser()) {
            
        	String email = screen.addUser.getTextEmail();
            String password = screen.addUser.getTextPassword();

            try{
				//Validating email
				if(email.isEmpty()){
					throw new IllegalArgumentException("Email required");
				}
//				else if(dca.emailCheck(email)){
//					throw new Exception("Email already exist!");
//				}
//				else if(cbsMail != true || email.indexOf("@") != 8){
//					throw new Exception("CBS email required");
//				}
				//Validating password
				else if(password.isEmpty()){
					throw new IllegalArgumentException("Password required");
				}
				else if(password.length() < 6){
					throw new Exception("Password must be least 6 characters");
				}
				else if(password.length() > 22){
					throw new Exception("Password is too long");
				}
                if(selectedRole == "empty"){
                    throw new IllegalArgumentException("Choose a role");
                }
		
				else{
					screen.addUser.setErrorMessage(email+" has been successfully added to the system");
				    viewmodel.addUser(email, password, selectedRole);
				
				}

			}
			catch(Exception exc){
				screen.addUser.setErrorMessage(exc.getMessage());

			}
            
        }

        //Checks if user or admin radiobutton is pressed and store value into variable
        if (e.getSource() == screen.addUser.getRadioUser()) {
            selectedRole = "0";
        }

        if (e.getSource() == screen.addUser.getRadioAdmin()) {
            selectedRole = "1";
        }
    
    }
    

}
