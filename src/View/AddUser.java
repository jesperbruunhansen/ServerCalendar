package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 14/11/14.
 */
public class AddUser extends JPanel {

    public JPanel panel;
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
    public JLabel lblMessage;

    public AddUser() {

        //OVERALL PANEL SETTINGS
        setLayout(null);
        setBounds(0, 0, 850, 711);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 850, 711);
        add(panel);
        panel.setLayout(null);

        //CONTENT ON PANEL
        lblAddUser = new JLabel("Add User");
        lblAddUser.setForeground(Color.GRAY);
        lblAddUser.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblAddUser.setBounds(27, 15, 71, 15);
        panel.add(lblAddUser);

        lblHeader = new JLabel();
        lblHeader.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblHeader.setText("Please input the following information to create a user");
        lblHeader.setBounds(27, 70, 374, 17);
        panel.add(lblHeader);

        lblEmail = new JLabel();
        lblEmail.setText("E-mail");
        lblEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblEmail.setBounds(27, 104, 100, 17);
        panel.add(lblEmail);

        lblPassword = new JLabel();
        lblPassword.setText("Password");
        lblPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblPassword.setBounds(27, 139, 100, 17);
        panel.add(lblPassword);

        textEmail = new JTextField();
        textEmail.setBounds(130, 99, 300, 28);
        panel.add(textEmail);
        textEmail.setColumns(10);

        textPassword = new JTextField();
        textPassword.setColumns(10);
        textPassword.setBounds(130, 133, 300, 28);
        panel.add(textPassword);

        radioUser = new JRadioButton("User");
        radioUser.setBackground(Color.WHITE);
        radioUser.setFont(new Font("Dialog", Font.PLAIN, 12));
        radioUser.setBounds(27, 215, 100, 23);
        panel.add(radioUser);

        radioAdmin = new JRadioButton("Admin");
        radioAdmin.setBackground(Color.WHITE);
        radioAdmin.setFont(new Font("Dialog", Font.PLAIN, 12));
        radioAdmin.setBounds(130, 215, 100, 23);
        panel.add(radioAdmin);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioUser);
        buttonGroup.add(radioAdmin);

        lblCreateRegularUser = new JLabel();
        lblCreateRegularUser.setText("Create regular user or admin");
        lblCreateRegularUser.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblCreateRegularUser.setBounds(27, 193, 374, 17);
        panel.add(lblCreateRegularUser);

        btnCreateUser = new JButton("Create User");
        btnCreateUser.setOpaque(true);
        btnCreateUser.setForeground(Color.WHITE);
        btnCreateUser.setFont(new Font("Dialog", Font.BOLD, 12));
        btnCreateUser.setFocusPainted(false);
        btnCreateUser.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnCreateUser.setBackground(new Color(102, 204, 153));
        btnCreateUser.setBounds(27, 271, 146, 32);
        panel.add(btnCreateUser);

        lblMessage = new JLabel("InfoBox");
        lblMessage.setForeground(Color.GRAY);
        lblMessage.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblMessage.setBounds(27, 327, 403, 16);
        lblMessage.setVisible(false);
        panel.add(lblMessage);

    }

    //RETURN CREATE USER BUTTON
    public JButton getBtnCreateUser() {
        return btnCreateUser;
    }

    //RETURN CONTENT OF EMAIL FIELD
    public String getTextEmail(){
    	String email = textEmail.getText();
    	return email;
    }

    //RETURN CONTENT OF PASSWORD FIELD
    public String getTextPassword() {
    	String password = textPassword.getText();
    	return password;
    }

    //RETURN RADIO BUTTON USER
    public JRadioButton getRadioUser() {
        return radioUser;
    }

    //RETURN RADIO BUTTON ADMIN
    public JRadioButton getRadioAdmin() {
        return radioAdmin;
    }

    //SET THE ERROR MESSAGE
    public void setErrorMessage(String s){
        lblMessage.setVisible(true);
        lblMessage.setText(s);
    }

    //CLEAR ALL FIELDS SET ON THE PANEL
    public void clearAddUser(){
        textEmail.setText("");
        textPassword.setText("");
        lblMessage.setText("");
        buttonGroup.clearSelection();
    }

    // ADD ACTIONLISTENER
    public void addListeners(ActionListener l){
        btnCreateUser.addActionListener(l);
        radioUser.addActionListener(l);
        radioAdmin.addActionListener(l);
    }
}
