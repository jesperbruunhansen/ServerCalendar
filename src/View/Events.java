package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;

/**
 * Created by Casper on 16/11/14.
 */
public class Events extends JPanel {

    // PANELS //
    public JPanel panelTop;
    public JPanel panelSide;
    public JPanel panelContent;
    public JPanel panelHor;
    public JButton btnBack;
    public JButton btnFor;
    public JTextPane txtLabel;
    public JLabel lblMenu;
    public JButton btnUserList;
    public JButton btnAddUser;
    public JButton btnCalendars;
    public JButton btnEvents;
    public JButton btnNotes;
    public JButton btnLogout;
    public JPanel panelVer;
    // PANELS //

    public Events() {

        /////////////////////////////// PANELS ///////////////////////////////
        setLayout(null);
        setBounds(150, 150, 1024, 768);

        panelTop = new JPanel();
        panelTop.setBackground(new Color(230, 230, 230, 250));
        panelTop.setBounds(0, 0, 1024, 57);
        add(panelTop);
        panelTop.setLayout(null);

        panelHor = new JPanel();
        panelHor.setBackground(new Color(183, 183, 183));
        panelHor.setBounds(0, 55, 1024, 2);
        panelTop.add(panelHor);

        btnBack = new JButton("<");
        btnBack.setBounds(10, 23, 30, 27);
        btnBack.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
        btnBack.setForeground(Color.GRAY);
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnBack.setFocusPainted(false);
        btnBack.setOpaque(true);
        panelTop.add(btnBack);

        btnFor = new JButton(">");
        btnFor.setBounds(42, 23, 30, 27);
        btnFor.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
        btnFor.setForeground(Color.GRAY);
        btnFor.setBackground(new Color(255, 255, 255));
        btnFor.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnFor.setFocusPainted(false);
        btnFor.setOpaque(true);
        panelTop.add(btnFor);

        txtLabel = new JTextPane();
        txtLabel.setForeground(Color.GRAY);
        txtLabel.setBackground(new Color(230, 230, 230));
        txtLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtLabel.setText("HA-IT Calendar Admin");
        txtLabel.setBounds(855, 30, 152, 15);
        panelTop.add(txtLabel);

        panelSide = new JPanel();
        panelSide.setBackground(new Color(247, 245, 246, 250));
        panelSide.setBounds(0, 57, 190, 711);
        add(panelSide);
        panelSide.setLayout(null);

        lblMenu = new JLabel("Menu");
        lblMenu.setForeground(Color.GRAY);
        lblMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblMenu.setBounds(10, 15, 46, 15);
        panelSide.add(lblMenu);

        btnUserList = new JButton("User List");
        btnUserList.setBounds(0, 36, 190, 25);
        btnUserList.setFont(new Font("Dialog", Font.BOLD, 12));
        btnUserList.setForeground(new Color(255, 255, 255));
        btnUserList.setBackground(new Color(80, 141, 221));
        btnUserList.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnUserList.setFocusPainted(false);
        btnUserList.setOpaque(true);
        panelSide.add(btnUserList);

        btnAddUser = new JButton("Add User");
        btnAddUser.setBounds(0, 70, 190, 25);
        btnAddUser.setFont(new Font("Dialog", Font.BOLD, 12));
        btnAddUser.setForeground(new Color(255, 255, 255));
        btnAddUser.setBackground(new Color(80, 141, 221));
        btnAddUser.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnAddUser.setFocusPainted(false);
        btnAddUser.setOpaque(true);
        panelSide.add(btnAddUser);

        btnCalendars = new JButton("Calendars");
        btnCalendars.setBounds(0, 157, 190, 25);
        btnCalendars.setOpaque(true);
        btnCalendars.setForeground(Color.WHITE);
        btnCalendars.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCalendars.setFocusPainted(false);
        btnCalendars.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
        btnCalendars.setBackground(new Color(80, 141, 221));
        panelSide.add(btnCalendars);

        btnEvents = new JButton("Events");
        btnEvents.setBounds(0, 193, 190, 25);
        btnEvents.setOpaque(true);
        btnEvents.setForeground(Color.WHITE);
        btnEvents.setFont(new Font("Dialog", Font.BOLD, 12));
        btnEvents.setFocusPainted(false);
        btnEvents.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnEvents.setBackground(new Color(80, 141, 221));
        panelSide.add(btnEvents);

        btnNotes = new JButton("Notes");
        btnNotes.setOpaque(true);
        btnNotes.setForeground(Color.WHITE);
        btnNotes.setFont(new Font("Dialog", Font.BOLD, 12));
        btnNotes.setFocusPainted(false);
        btnNotes.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
        btnNotes.setBackground(new Color(80, 141, 221));
        btnNotes.setBounds(0, 229, 190, 25);
        panelSide.add(btnNotes);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(0, 630, 190, 32);
        btnLogout.setFont(new Font("Dialog", Font.BOLD, 12));
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setBackground(new Color(204, 51, 51));
        btnLogout.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnLogout.setFocusPainted(false);
        btnLogout.setOpaque(true);
        panelSide.add(btnLogout);

        panelVer = new JPanel();
        panelVer.setBackground(new Color(234, 232, 233));
        panelVer.setBounds(188, 0, 1, 711);
        panelSide.add(panelVer);

        panelContent = new JPanel();
        panelContent.setBackground(new Color(255, 255, 255));
        panelContent.setBounds(190, 57, 834, 711);
        add(panelContent);
        panelContent.setLayout(null);

        /////////////////////////////// PANELS ///////////////////////////////

    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnUserList() {
        return btnUserList;
    }

    public JButton getBtnAddUser() {
        return btnAddUser;
    }

    public JButton getBtnCalendars() {
        return btnCalendars;
    }

    public JButton getBtnEvents() {
        return btnEvents;
    }

    public JButton getBtnNotes() {
        return btnNotes;
    }

    public void addListeners(ActionListener l){
        btnLogout.addActionListener(l);
        btnUserList.addActionListener(l);
        btnAddUser.addActionListener(l);
        btnCalendars.addActionListener(l);
        btnEvents.addActionListener(l);
        btnNotes.addActionListener(l);
    }
}