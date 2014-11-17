package View;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Created by Casper on 14/11/14.
 */
public class Login extends JPanel {
	
	public JPanel panel;
	public ImageIcon image;
	public JLabel lbWall;
	public JLabel lbIcon;
	public JButton btnLogin;
	public JPasswordField textPass;
	public JTextField textUser;
    //public JLabel lblBox;

	public Login() {
		setLayout(null);
		setBounds(150, 150, 1024, 768);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 100));
		panel.setBounds(358, 131, 300, 450);
		add(panel);
		panel.setLayout(null);
		
		image = new ImageIcon(getClass().getResource("images/login.jpg"));
		lbWall = new JLabel(image);
		lbWall.setBounds(0, 0, 1024, 768);
		add(lbWall);
		
		image = new ImageIcon(getClass().getResource("images/hait.png"));
		
		textUser = new JTextField();
		textUser.setHorizontalAlignment(SwingConstants.CENTER);
		textUser.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 13));
		textUser.setBounds(36, 283, 225, 30);
		textUser.setText("E-mail");
		textUser.setColumns(10);
		textUser.setBorder(null);
		textUser.setColumns(10);
		panel.add(textUser);
		
		textPass = new JPasswordField();
		textPass.setHorizontalAlignment(SwingConstants.CENTER);
		textPass.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 13));
		textPass.setBounds(36, 324, 225, 30);
		textPass.setText("Password");
		textPass.setColumns(10);
		textPass.setBorder(null);
		panel.add(textPass);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBounds(36, 382, 225, 30);
		btnLogin.setBackground(new Color(0, 204, 153));
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setOpaque(true);
		panel.add(btnLogin);
		
		lbIcon = new JLabel(image);
		lbIcon.setBounds(0, 15, 300, 257);
		panel.add(lbIcon);

        /*lblBox = new JLabel("Box");
        lblBox.setForeground(Color.RED);
        lblBox.setHorizontalAlignment(SwingConstants.CENTER);
        lblBox.setFont(new Font("Dialog", Font.BOLD, 13));
        lblBox.setBounds(36, 423, 225, 16);
        lblBox.setVisible(false);
        panel.add(lblBox);*/

	}

    //Return login button
	public JButton getBtnLogin() {
    		return btnLogin;
	}

    //Return username TextField
    public JTextField getFieldUsername(){
        return textUser;
    }

    //Returns password-field
    public JTextField getFieldPassword(){
        return textPass;
    }

    //Returns contents of username as a string
    public String getUsername(){
        return textUser.getText();
    }

    //Set username textfield
    public void setUsername(String s){
        textUser.setText(s);
    }

    //Returns contents of password as a string
    public String getPassword(){
        return textPass.getText();
    }

    //Set set password field
    public void setPassword(String s){
        textPass.setText(s);
    }

/*    //Set errormessage
    public void setErrorMessage(String s){
        lblBox.setVisible(true);
        lblBox.setText(s);
    }*/

    //Add action, key and focuslisteners
    public void addListeners(ActionListener l, KeyListener kl, FocusListener fl){
        btnLogin.addActionListener(l);
        textUser.addKeyListener(kl);
        textPass.addKeyListener(kl);
        textUser.addFocusListener(fl);
        textPass.addFocusListener(fl);
    }

}
