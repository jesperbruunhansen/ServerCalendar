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
    ViewModel viewmodel = new ViewModel();

    public AddUserController(Screen screen){
        this.screen = screen;
        screen.addUser.addListeners(this);
    }

    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.addUser.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.addUser.getBtnUserList()) {
            screen.show(Screen.USERLIST);
        }

        //If Add user button is clicked
        if (e.getSource() == screen.addUser.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.addUser.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.addUser.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.addUser.getBtnNotes()) {
            screen.show(Screen.NOTES);
     
        }
    
        if (e.getSource() == screen.addUser.getBtnCreateUser()) {
            
        	String email = screen.addUser.getTextEmail();
           
            String password = screen.addUser.getTextPassword();
            int error = 0;
            try{
				
				//Validating email
				if(email.isEmpty()){
					error++;
					throw new IllegalArgumentException("Email required");
				}
//				else if(dca.emailCheck(email)){
//					error++;
//					throw new Exception("Email already exist!");
//				}
//				else if(cbsMail != true || email.indexOf("@") != 8){
//					error++;
//					throw new Exception("CBS email required");
//				}
				//Validating names
				else if(email.isEmpty()){
					error++;
					throw new IllegalArgumentException("Email required");
				}
				//Validating password
				else if(password.isEmpty()){
					error++;
					throw new IllegalArgumentException("Password required");
				}
				else if(password.length() < 6){
					error++;
					throw new Exception("Password must be least 6 characters");
				}
				else if(password.length() > 22){
					error++;
					throw new Exception("Password is too long");
				}
		
				else{
//					screen.admAddUser.setTxtBox().setVisible(true);
//					screen.admAddUser.setTextbox(email+" has been successfully added to the system");
				viewmodel.addUser(email, password);
				
				}

			}
			catch(Exception exc){
//				screen.admAddUser.setTxtBox().setVisible(true);
//				if(error != 0){
//					screen.admAddUser.setTextbox(exc.getMessage());
//				}
//				else{
//					System.out.println(exc.getMessage());
//					screen.admAddUser.setTextbox("Amount cannot be empty");
//				}
			}
            
        }
    
    }
    

}
