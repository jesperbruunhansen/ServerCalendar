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

    //IF ACTIONLISTENER IS TRIGGERED
    public void actionPerformed(ActionEvent e) {

        //CREATE USER BUTTON IS CLICKED
        if (e.getSource() == screen.addUser.getBtnCreateUser()) {

            //Save email & password into variables
        	String email = screen.addUser.getTextEmail();
            String password = screen.addUser.getTextPassword();

            //VALIDATE EMAIL AND PASSWORD
            try{
				//VALIDATE EMAIL
				if(email.isEmpty()){
					throw new IllegalArgumentException("Email required");
				}
				else if(viewModel.emailCheck(email)){
					throw new Exception("Email already exist!");
				}
				else if(!email.contains("@")){
					throw new Exception("You need to type a mail with @");
				}
				//VALIDATE PASSWORD
				else if(password.isEmpty()){
					throw new IllegalArgumentException("Password required");
				}
				else if(password.length() < 6){
					throw new Exception("Password must be least 6 characters");
				}
				else if(password.length() > 22){
					throw new Exception("Password is too long");
				}
                //VALIDATE SELECTED ROLE
                if(selectedRole == "empty"){
                    throw new IllegalArgumentException("Choose a role");
                }

                //IF NO ERRORS ADD NEW USER TO DATABASE
				else{
                    viewmodel.addUser(email, password, selectedRole);
                    screen.addUser.setErrorMessage(email+" has been successfully added to the system");

                    //CLEAR INPUT IN ALL FIELDS
                    screen.addUser.clearAddUser();
				}
			}
            //CATCH ERROR MESSAGE
			catch(Exception exc){
				screen.addUser.setErrorMessage(exc.getMessage());

			}
            
        }

        //SAVE RADIO BUTTON PRESS INTO VARIABLE
        if (e.getSource() == screen.addUser.getRadioUser()) {
            selectedRole = "0";
        }
        if (e.getSource() == screen.addUser.getRadioAdmin()) {
            selectedRole = "1";
        }
    }
}
