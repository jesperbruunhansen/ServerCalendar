package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

/**
 * Created by Casper on 14/11/14.
 */
public class UserList extends JPanel {
	
	public JLabel lblUser;

	public UserList() {
		
		lblUser = new JLabel("User");
		add(lblUser);
		
	}

    //public void addListeners(ActionListener l){
    //    test.addActionListener(l);
    //}
	
}