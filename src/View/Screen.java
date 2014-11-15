package View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

/**
 * Created by Casper on 14/11/14.
 */
public class Screen extends JFrame {

	//Final representation of screens
	public static final String LOGIN = "login";
	public static final String WELCOME = "welcome";
    public static final String USERLIST = "userlist";
	
	public JPanel contentPane;
	public final Login login;
	public final Welcome welcome;
    public final UserList userList;

	CardLayout c;
	
	public Screen() {

        setTitle("HAIT Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 1024, 768);
		setResizable(false);

		//Create new contentpane
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 204));
		contentPane.setLayout(new CardLayout(0, 0));
		setContentPane(contentPane);

		//Add login-screen
		login = new Login();
		contentPane.add(login, LOGIN);
		
		//Add Welcome-screen
		welcome = new Welcome();
		contentPane.add(welcome, WELCOME);

        //Add User List-screen
        userList = new UserList();
        contentPane.add(userList, USERLIST);

		//Get Layout for contentpane
		c = (CardLayout) getContentPane().getLayout();

        show(Screen.LOGIN);
        setVisible(true);
	}

	public void show(String card) {
		c.show(getContentPane(), card);
	}
	
	public Login getLogin() {
		return login;
	}

}