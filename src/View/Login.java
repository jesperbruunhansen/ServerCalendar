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
	public JTextField textMail;

	public Login() {

		//OVERALL PANEL SETTINGS
        setLayout(null);
        setBounds(0, 0, 1024, 768);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 100));
		panel.setBounds(358, 131, 300, 450);
		add(panel);
		panel.setLayout(null);

        //CONTENT ON PANEL
		image = new ImageIcon(getClass().getResource("images/login.jpg"));
		lbWall = new JLabel(image);
		lbWall.setBounds(0, 0, 1024, 768);
		add(lbWall);
		
		image = new ImageIcon(getClass().getResource("images/hait.png"));
		
		textMail = new JTextField();
        textMail.setHorizontalAlignment(SwingConstants.CENTER);
        textMail.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 13));
        textMail.setBounds(36, 283, 225, 30);
        textMail.setText("E-mail");
        textMail.setColumns(10);
        textMail.setBorder(null);
        textMail.setColumns(10);
		panel.add(textMail);
		
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

	}

    //RETURN LOGIN BUTTON
	public JButton getBtnLogin() {
    		return btnLogin;
	}

    //RETURN EMAIL FIELD
    public JTextField getFieldMail(){
        return textMail;
    }

    //RETURN PASSWORD FIELD
    public JTextField getFieldPassword(){
        return textPass;
    }

    //RETURN CONTENT OF EMAIL FIELD
    public String getMail(){
        return textMail.getText();
    }

    //RETURN CONTENT OF PASSWORD FIELD
    public String getPassword(){
        return textPass.getText();
    }

    //RESET TEXT ON PASSWORD FIELD
    public void setMail(String s){
        textMail.setText(s);
    }

    //RESET TEXT ON PASSWORD FIELD
    public void setPassword(String s){
        textPass.setText(s);
    }

    //ADD ACTION; KEY; FOCUSLISTENERS
    public void addListeners(ActionListener l, KeyListener kl, FocusListener fl){
        btnLogin.addActionListener(l);
        textMail.addKeyListener(kl);
        textPass.addKeyListener(kl);
        textMail.addFocusListener(fl);
        textPass.addFocusListener(fl);
    }

}
