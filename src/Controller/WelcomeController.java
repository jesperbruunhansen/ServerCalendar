package Controller;

import Model.Calendar.Users;
import Model.QueryBuild.QueryBuilder;
import View.Screen;
import View.UserList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.google.gson.Gson;

/**
 * Created by Casper on 15/11/14.
 */
public class WelcomeController extends Controller implements ActionListener {

    private Screen screen;

    private Gson gson;
    private QueryBuilder queryBuilder;
    private ResultSet rs;
    
    public WelcomeController(Screen screen){
        this.screen = screen;
        screen.welcome.addListeners(this);
    }

    public void getAllUsers() {

        try{
            queryBuilder = new QueryBuilder();
            gson = new Gson();

            rs = queryBuilder.selectFrom("users").all().ExecuteQuery();

            List<Users> userList = new ArrayList<>();

            while (rs.next()){
                Users users = new Users();
                users.setUserId(rs.getInt("userid"));
                users.setUserName(rs.getString("email"));
                users.setActive(rs.getInt("active"));
                userList.add(users);
            }
            
            for(Users user : userList){
            	System.out.println(user.getUserName());
            	
            }
            
            

            //return gson.toJson(userList);
        }
        catch (Exception e){
            e.printStackTrace();
        }

       //	 return null;
 }
    
    
    //ActionListener
    public void actionPerformed(ActionEvent e) {

        //If login button is clicked
        if (e.getSource() == screen.welcome.getBtnLogout()) {
            screen.show(Screen.LOGIN);
            screen.login.setUsername("E-mail");
            screen.login.setPassword("Password");
        }

        //If User list button is clicked
        if (e.getSource() == screen.welcome.getBtnUserList()) {
        
        	getAllUsers();
        	 
        }

        //If Add user button is clicked
        if (e.getSource() == screen.welcome.getBtnAddUser()) {
            screen.show(Screen.ADDUSER);
        }

        //If Delete user button is clicked
        if (e.getSource() == screen.welcome.getBtnDeleteUser()) {
            screen.show(Screen.USERLIST);
        }

        //If Calendar button is clicked
        if (e.getSource() == screen.welcome.getBtnCalendars()) {
            screen.show(Screen.CALENDAR);
        }

        //If Events button is clicked
        if (e.getSource() == screen.welcome.getBtnEvents()) {
            screen.show(Screen.EVENTS);
        }

        //If Notes button is clicked
        if (e.getSource() == screen.welcome.getBtnNotes()) {
            screen.show(Screen.NOTES);
        }
    }

}
