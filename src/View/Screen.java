package View;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;

/**
 * Created by Casper on 14/11/14.
 */
@SuppressWarnings("serial")
public class Screen extends JFrame {

    //Final representation of screens
    public static final String CONTENT = "content";
    public static final String MENUTOP = "menutop";
    public static final String MENULEFT = "menuleft";
    public static final String LOGIN = "login";
    public static final String WELCOME = "welcome";
    public static final String USERLIST = "userlist";
    public static final String ADDUSER = "adduser";
    public static final String CALENDAR = "calendar";
    public static final String EVENTS = "events";
    public static final String NOTES = "notes";

    public JPanel contentPane;
    public JPanel content;
    public final MenuTop menuTop;
    public final MenuLeft menuLeft;
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
        contentPane.setLayout(null);
        setContentPane(contentPane);

        //Add Login-screen
        login = new Login();
        contentPane.add(login, LOGIN);
        login.setVisible(false);

        menuTop = new MenuTop();
        contentPane.add(menuTop, MENUTOP);

        menuLeft = new MenuLeft();
        contentPane.add(menuLeft, MENULEFT);

        content = new JPanel();
        content.setBounds(190, 57, 834, 711);
        content.setLayout(new CardLayout(0, 0));
        contentPane.add(content, CONTENT);

        //Add Welcome-screen
        welcome = new Welcome();
        content.add(welcome, WELCOME);

        //Add User List-screen
        userList = new UserList();
        content.add(userList, USERLIST);

        //Add Add-User-screen
        addUser = new AddUser();
        content.add(addUser, ADDUSER);

        //Add Calendar-screen
        calendar = new Calendar();
        content.add(calendar, CALENDAR);

        //Add Events-screen
        events = new Events();
        content.add(events, EVENTS);

        //Add Notes-screen
        notes = new Notes();
        content.add(notes, NOTES);

        //Get Layout for contentpane
        c = (CardLayout) content.getLayout();

        setVisible(true);
    }

    public void show(String card) {
        c.show(content, card);
    }

    public void LoginVisible(boolean set) {
        login.setVisible(set);
    }

    public void MenuVisible(boolean set) {
        menuLeft.setVisible(set);
    }


}