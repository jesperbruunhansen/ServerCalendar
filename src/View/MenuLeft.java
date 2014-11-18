package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Casper on 14/11/14.
 */
public class MenuLeft extends JPanel {

    public JPanel panel;
    public JLabel lblMenu;
    public JButton btnUserList;
    public JButton btnAddUser;
    public JButton btnCalendars;
    public JButton btnEvents;
    public JButton btnNotes;
    public JButton btnLogout;
    public JPanel border;

    public MenuLeft() {

        //OVERALL PANEL SETTINGS
        setLayout(null);
        setBounds(0, 0, 190, 768);
        setBackground(new Color(0, 0, 0, 0));

        panel = new JPanel();
        panel.setBackground(new Color(247, 245, 246, 250));
        panel.setBounds(0, 57, 190, 768);
        add(panel);
        panel.setLayout(null);

        //CONTENT ON PANEL
        lblMenu = new JLabel("Menu");
        lblMenu.setForeground(Color.GRAY);
        lblMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblMenu.setBounds(10, 15, 46, 15);
        panel.add(lblMenu);

        btnUserList = new JButton("User List");
        btnUserList.setBounds(0, 36, 190, 25);
        btnUserList.setFont(new Font("Dialog", Font.BOLD, 12));
        btnUserList.setForeground(new Color(255, 255, 255));
        btnUserList.setBackground(new Color(80, 141, 221));
        btnUserList.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnUserList.setFocusPainted(false);
        btnUserList.setOpaque(true);
        panel.add(btnUserList);

        btnAddUser = new JButton("Add User");
        btnAddUser.setBounds(0, 70, 190, 25);
        btnAddUser.setFont(new Font("Dialog", Font.BOLD, 12));
        btnAddUser.setForeground(new Color(255, 255, 255));
        btnAddUser.setBackground(new Color(80, 141, 221));
        btnAddUser.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnAddUser.setFocusPainted(false);
        btnAddUser.setOpaque(true);
        panel.add(btnAddUser);

        btnCalendars = new JButton("Calendars");
        btnCalendars.setBounds(0, 157, 190, 25);
        btnCalendars.setOpaque(true);
        btnCalendars.setForeground(Color.WHITE);
        btnCalendars.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCalendars.setFocusPainted(false);
        btnCalendars.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
        btnCalendars.setBackground(new Color(80, 141, 221));
        panel.add(btnCalendars);

        btnEvents = new JButton("Events");
        btnEvents.setBounds(0, 193, 190, 25);
        btnEvents.setOpaque(true);
        btnEvents.setForeground(Color.WHITE);
        btnEvents.setFont(new Font("Dialog", Font.BOLD, 12));
        btnEvents.setFocusPainted(false);
        btnEvents.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnEvents.setBackground(new Color(80, 141, 221));
        panel.add(btnEvents);

        btnNotes = new JButton("Notes");
        btnNotes.setOpaque(true);
        btnNotes.setForeground(Color.WHITE);
        btnNotes.setFont(new Font("Dialog", Font.BOLD, 12));
        btnNotes.setFocusPainted(false);
        btnNotes.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
        btnNotes.setBackground(new Color(80, 141, 221));
        btnNotes.setBounds(0, 229, 190, 25);
        panel.add(btnNotes);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(0, 630, 190, 32);
        btnLogout.setFont(new Font("Dialog", Font.BOLD, 12));
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setBackground(new Color(204, 51, 51));
        btnLogout.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);
        panel.add(btnLogout);

        border = new JPanel();
        border.setBackground(new Color(234, 232, 233));
        border.setBounds(188, 0, 1, 711);
        panel.add(border);

    }

    //RETURN USERLIST BUTTON
    public JButton getBtnUserList() {
        return btnUserList;
    }

    //RETURN ADD USER BUTTON
    public JButton getBtnAddUser() {
        return btnAddUser;
    }

    //RETURN CALENDAR BUTTON
    public JButton getBtnCalendars() {
        return btnCalendars;
    }

    //RETURN EVENTS BUTTON
    public JButton getBtnEvents() {
        return btnEvents;
    }

    //RETURN NOTES BUTTON
    public JButton getBtnNotes() {
        return btnNotes;
    }

    //RETURN LOGOUT BUTTON
    public JButton getBtnLogout() {
        return btnLogout;
    }

    // ADD ACTIONLISTENER
    public void addListeners(ActionListener l){
        btnUserList.addActionListener(l);
        btnAddUser.addActionListener(l);
        btnCalendars.addActionListener(l);
        btnEvents.addActionListener(l);
        btnNotes.addActionListener(l);
        btnLogout.addActionListener(l);
    }
}
