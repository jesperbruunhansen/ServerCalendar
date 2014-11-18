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

        //FRAME SETTINGS
        setTitle("HAIT Calendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 1024, 768);
        setResizable(false);

        //CREATING NEW CONTENTPANE
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 153, 204));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        //ADDING SCREENS
        login = new Login();
        contentPane.add(login, LOGIN);
        login.setVisible(false);

        //MENU SCREENS
        menuTop = new MenuTop();
        contentPane.add(menuTop, MENUTOP);

        menuLeft = new MenuLeft();
        contentPane.add(menuLeft, MENULEFT);

        //CONTENT SCREENS
        content = new JPanel();
        content.setBounds(190, 57, 834, 711);
        content.setLayout(new CardLayout(0, 0));
        contentPane.add(content, CONTENT);

        welcome = new Welcome();
        content.add(welcome, WELCOME);

        userList = new UserList();
        content.add(userList, USERLIST);

        addUser = new AddUser();
        content.add(addUser, ADDUSER);

        calendar = new Calendar();
        content.add(calendar, CALENDAR);

        events = new Events();
        content.add(events, EVENTS);

        notes = new Notes();
        content.add(notes, NOTES);

        //GET LAYOUT FOR CONTENT SCREENS
        c = (CardLayout) content.getLayout();

        //SET FRAME VISIBLE
        setVisible(true);
    }

    //SHOW SCREEN METHOD
    public void show(String card) {
        c.show(content, card);
    }

    //SET LOGIN SCREEN VISIBLE TRUE/FALSE
    public void LoginVisible(boolean set) {
        login.setVisible(set);
    }

    //SET MENU PANEL VISIBLE TRUE/FALSE
    public void MenuVisible(boolean set) {
        menuLeft.setVisible(set);
    }


}