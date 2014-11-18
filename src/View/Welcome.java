package View;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;

/**
 * Created by Casper on 14/11/14.
 */
public class Welcome extends JPanel {

    public JPanel panelContent;
    public JTextPane txtWelcome;
    public ImageIcon image;
    public JLabel lbIcon;

    public Welcome() {

        //OVERALL PANEL SETTINGS
        setLayout(null);
        setBounds(0, 0, 850, 711);

        panelContent = new JPanel();
        panelContent.setBackground(new Color(255, 255, 255));
        panelContent.setBounds(0, 0, 850, 711);
        add(panelContent);
        panelContent.setLayout(null);

        //CONTENT ON PANEL
        txtWelcome = new JTextPane();
        txtWelcome.setForeground(Color.DARK_GRAY);
        txtWelcome.setBounds(273, 17, 270, 19);
        txtWelcome.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtWelcome.setText("Welcome to HA-IT Calendar - Admin view");
        panelContent.add(txtWelcome);

        image = new ImageIcon(getClass().getResource("Images/calendar.jpg"));
        lbIcon = new JLabel(image);
        lbIcon.setBounds(84, 91, 666, 433);
        panelContent.add(lbIcon);

    }

}