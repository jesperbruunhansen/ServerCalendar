package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class AddUser extends JPanel {
	
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
	public JLabel lblAddUser;
	public JLabel lblHeader;
	public JLabel lblEmail;
	public JLabel lblPassword;
	public JTextField textEmail;
	public JTextField textPassword;
	public JRadioButton radioUser;
	public JRadioButton radioAdmin;
    public ButtonGroup buttonGroup;
	public JLabel lblCreateRegularUser;
	public JButton btnCreateUser;
    public JLabel lblBox;

	public AddUser() {
		
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
        txtLabel.setBounds(855, 30, 152, 20);
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

		lblAddUser = new JLabel("Add User");
		lblAddUser.setForeground(Color.GRAY);
		lblAddUser.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAddUser.setBounds(27, 15, 71, 15);
		panelContent.add(lblAddUser);
		
		lblHeader = new JLabel();
        lblHeader.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblHeader.setText("Please input the following information to create a user");
        lblHeader.setBounds(27, 70, 374, 17);
		panelContent.add(lblHeader);
		
		lblEmail = new JLabel();
        lblEmail.setText("E-mail");
        lblEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblEmail.setBounds(27, 104, 100, 17);
		panelContent.add(lblEmail);
		
		lblPassword = new JLabel();
        lblPassword.setText("Password");
        lblPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblPassword.setBounds(27, 139, 100, 17);
        panelContent.add(lblPassword);
		
		textEmail = new JTextField();
		textEmail.setBounds(130, 99, 300, 28);
		panelContent.add(textEmail);
		textEmail.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(130, 133, 300, 28);
		panelContent.add(textPassword);
		
		radioUser = new JRadioButton("User");
        radioUser.setBackground(Color.WHITE);
        radioUser.setFont(new Font("Dialog", Font.PLAIN, 12));
        radioUser.setBounds(27, 215, 100, 23);
		panelContent.add(radioUser);

        radioAdmin = new JRadioButton("Admin");
        radioAdmin.setBackground(Color.WHITE);
        radioAdmin.setFont(new Font("Dialog", Font.PLAIN, 12));
        radioAdmin.setBounds(130, 215, 100, 23);
		panelContent.add(radioAdmin);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioUser);
        buttonGroup.add(radioAdmin);

        lblCreateRegularUser = new JLabel();
        lblCreateRegularUser.setText("Create regular user or admin");
        lblCreateRegularUser.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblCreateRegularUser.setBounds(27, 193, 374, 17);
		panelContent.add(lblCreateRegularUser);
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setOpaque(true);
		btnCreateUser.setForeground(Color.WHITE);
		btnCreateUser.setFont(new Font("Dialog", Font.BOLD, 12));
		btnCreateUser.setFocusPainted(false);
		btnCreateUser.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
		btnCreateUser.setBackground(new Color(102, 204, 153));
		btnCreateUser.setBounds(27, 271, 146, 32);
		panelContent.add(btnCreateUser);

        lblBox = new JLabel("InfoBox");
        lblBox.setForeground(Color.GRAY);
        lblBox.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblBox.setBounds(27, 327, 403, 16);
        lblBox.setVisible(false);
        panelContent.add(lblBox);
			
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

    public JButton getBtnCreateUser() {
        return btnCreateUser;
    }
    
    public String getTextEmail(){
    	String email = textEmail.getText();
    	return email;
    }
    
    public String getTextPassword() {
    	String password = textPassword.getText();
    	return password;
    }

    public JRadioButton getRadioUser() {
        return radioUser;
    }

    public JRadioButton getRadioAdmin() {
        return radioAdmin;
    }

    //Set error message
    public void setErrorMessage(String s){
        lblBox.setVisible(true);
        lblBox.setText(s);
    }
    
    public void addListeners(ActionListener l){
        btnLogout.addActionListener(l);
        btnUserList.addActionListener(l);
        btnAddUser.addActionListener(l);
        btnCalendars.addActionListener(l);
        btnEvents.addActionListener(l);
        btnNotes.addActionListener(l);
        btnCreateUser.addActionListener(l);
        radioUser.addActionListener(l);
        radioAdmin.addActionListener(l);
    }
}
