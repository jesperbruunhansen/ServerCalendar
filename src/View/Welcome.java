package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.SwingConstants;

/**
 * Created by Casper on 14/11/14.
 */
public class Welcome extends JPanel {

	public JPanel panelTop;
	public JPanel panelSide;
	public JPanel panelContent;
	public JButton btnLogout;
	public JButton btnBack;
	public JButton btnFor;
	public JLabel lblMenu;
	public JButton btnUserList;
	public JButton btnAddUser;
	public JButton btnCalendars;
	public JButton btnEvents;
	public JPanel panelBorder;
	public JButton btnDeleteUser;
	public JPanel panelBorder2;
	public JButton btnNotes;

	public Welcome() {
		
		setLayout(null);
		setBounds(150, 150, 1024, 768);

		panelTop = new JPanel();
		panelTop.setBackground(new Color(230, 230, 230, 250));
		panelTop.setBounds(0, 0, 1024, 70);
		add(panelTop);
		panelTop.setLayout(null);
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
		btnLogout.setBounds(890, 27, 113, 32);
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.setBackground(new Color(204, 102, 102));
		btnLogout.setBorder(BorderFactory.createLineBorder(Color.white));
		//btnLogout.setBorderPainted();
		btnLogout.setFocusPainted(false);
		btnLogout.setOpaque(true);
		panelTop.add(btnLogout);
		
		btnBack = new JButton("<");
		btnBack.setBounds(10, 27, 39, 32);
		btnBack.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
		btnBack.setForeground(new Color(214, 214, 214));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setBorder(BorderFactory.createLineBorder(Color.white));
		btnBack.setFocusPainted(false);
		btnBack.setOpaque(true);
		panelTop.add(btnBack);
		
		btnFor = new JButton(">");
		btnFor.setBounds(50, 27, 39, 32);
		btnFor.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
		btnFor.setForeground(new Color(214, 214, 214));
		btnFor.setBackground(new Color(255, 255, 255));
		btnFor.setBorder(BorderFactory.createLineBorder(Color.white));
		btnFor.setFocusPainted(false);
		btnFor.setOpaque(true);
		panelTop.add(btnFor);
		
		panelBorder = new JPanel();
		panelBorder.setBackground(new Color(183, 183, 183));
		panelBorder.setBounds(0, 68, 1024, 2);
		panelTop.add(panelBorder);
		
		panelSide = new JPanel();
		panelSide.setBackground(new Color(247, 245, 246, 250));
		panelSide.setBounds(0, 70, 190, 700);
		add(panelSide);
		panelSide.setLayout(null);
		
		lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
		lblMenu.setBounds(10, 11, 46, 14);
		panelSide.add(lblMenu);
		
		btnUserList = new JButton("User List");
		btnUserList.setBounds(0, 36, 190, 25);
		btnUserList.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnUserList.setForeground(new Color(255, 255, 255));
		btnUserList.setBackground(new Color(80, 141, 221));
		btnUserList.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
		btnUserList.setFocusPainted(false);
		btnUserList.setOpaque(true);
		panelSide.add(btnUserList);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(0, 70, 190, 25);
		btnAddUser.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnAddUser.setForeground(new Color(255, 255, 255));
		btnAddUser.setBackground(new Color(80, 141, 221));
		btnAddUser.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
		btnAddUser.setFocusPainted(false);
		btnAddUser.setOpaque(true);
		panelSide.add(btnAddUser);
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setBounds(0, 106, 190, 25);
		btnDeleteUser.setOpaque(true);
		btnDeleteUser.setForeground(Color.WHITE);
		btnDeleteUser.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnDeleteUser.setFocusPainted(false);
		btnDeleteUser.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
		btnDeleteUser.setBackground(new Color(80, 141, 221));
		panelSide.add(btnDeleteUser);
		
		btnCalendars = new JButton("Calendars");
		btnCalendars.setBounds(0, 157, 190, 25);
		btnCalendars.setOpaque(true);
		btnCalendars.setForeground(Color.WHITE);
		btnCalendars.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnCalendars.setFocusPainted(false);
		btnCalendars.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
		btnCalendars.setBackground(new Color(80, 141, 221));
		panelSide.add(btnCalendars);
		
		btnEvents = new JButton("Events");
		btnEvents.setBounds(0, 193, 190, 25);
		btnEvents.setOpaque(true);
		btnEvents.setForeground(Color.WHITE);
		btnEvents.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnEvents.setFocusPainted(false);
		btnEvents.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
		btnEvents.setBackground(new Color(80, 141, 221));
		panelSide.add(btnEvents);
		
		btnNotes = new JButton("Notes");
		btnNotes.setOpaque(true);
		btnNotes.setForeground(Color.WHITE);
		btnNotes.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnNotes.setFocusPainted(false);
		btnNotes.setBorder(BorderFactory.createLineBorder(new Color(186, 214, 235)));
		btnNotes.setBackground(new Color(80, 141, 221));
		btnNotes.setBounds(0, 229, 190, 25);
		panelSide.add(btnNotes);
		
		panelBorder2 = new JPanel();
		panelBorder2.setBackground(new Color(234, 232, 233));
		panelBorder2.setBounds(188, 0, 2, 700);
		panelSide.add(panelBorder2);
		
		panelContent = new JPanel();
		panelContent.setBackground(new Color(255, 255, 255));
		panelContent.setBounds(190, 70, 834, 700);
		add(panelContent);
		
	}
	
	public JButton getBtnLogout() {
		return btnLogout;
	}
	
	public void addListeners(ActionListener l){
		btnLogout.addActionListener(l);
	}
}