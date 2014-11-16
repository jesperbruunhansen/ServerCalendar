package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class UserList extends JPanel {

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
    public JButton btnDeleteUser;
    public JButton btnCalendars;
    public JButton btnEvents;
    public JButton btnNotes;
    public JButton btnLogout;
    public JPanel panelVer;
    // PANELS //
    public JScrollPane scrollPane;
    public JButton btnDeleteUser2;
    public JLabel lblUserList;

    public UserList() {

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

        btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.setBounds(0, 106, 190, 25);
        btnDeleteUser.setOpaque(true);
        btnDeleteUser.setForeground(Color.WHITE);
        btnDeleteUser.setFont(new Font("Dialog", Font.BOLD, 12));
        btnDeleteUser.setFocusPainted(false);
        btnDeleteUser.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnDeleteUser.setBackground(new Color(80, 141, 221));
        panelSide.add(btnDeleteUser);

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

        scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 36, 784, 570);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        panelContent.add(scrollPane);

        btnDeleteUser2 = new JButton("Delete User");
        btnDeleteUser2.setOpaque(true);
        btnDeleteUser2.setForeground(Color.WHITE);
        btnDeleteUser2.setFont(new Font("Dialog", Font.BOLD, 12));
        btnDeleteUser2.setFocusPainted(false);
        btnDeleteUser2.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnDeleteUser2.setBackground(new Color(204, 51, 51));
        btnDeleteUser2.setBounds(661, 630, 146, 32);
        panelContent.add(btnDeleteUser2);

        lblUserList = new JLabel("User List");
        lblUserList.setForeground(Color.GRAY);
        lblUserList.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblUserList.setBounds(23, 15, 132, 15);
        panelContent.add(lblUserList);


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

    public JButton getBtnDeleteUser() {
        return btnDeleteUser;
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
        btnDeleteUser.addActionListener(l);
        btnCalendars.addActionListener(l);
        btnEvents.addActionListener(l);
        btnNotes.addActionListener(l);
    }
}