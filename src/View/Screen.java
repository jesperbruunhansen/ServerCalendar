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
    public static final String ADDUSER = "adduser";
    public static final String CALENDAR = "calendar";
    public static final String EVENTS = "events";
    public static final String NOTES = "notes";
	
	public JPanel contentPane;
	public final Login login;
	public final Welcome welcome;
    public final UserList userList;
    public final AddUser addUser;
    public final Calendar calendar;
    public final Events events;
    public final Notes notes;

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

		//Add Login-screen
		login = new Login();
		contentPane.add(login, LOGIN);
		
		//Add Welcome-screen
		welcome = new Welcome();
		contentPane.add(welcome, WELCOME);

        //Add User List-screen
        userList = new UserList();
        contentPane.add(userList, USERLIST);

        //Add Add-User-screen
        addUser = new AddUser();
        contentPane.add(addUser, ADDUSER);

        //Add Calendar-screen
        calendar = new Calendar();
        contentPane.add(calendar, CALENDAR);

        //Add Events-screen
        events = new Events();
        contentPane.add(events, EVENTS);

        //Add Notes-screen
        notes = new Notes();
        contentPane.add(notes, NOTES);

		//Get Layout for contentpane
		c = (CardLayout) getContentPane().getLayout();

        show(Screen.LOGIN);
        setVisible(true);
	}

	public void show(String card) {
		c.show(getContentPane(), card);
	}

}