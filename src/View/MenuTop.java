package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class MenuTop extends JPanel {

    public JPanel panel;
    public JPanel border;
    public JButton btnBack;
    public JButton btnFor;
    public JTextPane txtLabel;

    public MenuTop () {

        setLayout(null);
        setBounds(0, 0, 1024, 57);

        panel = new JPanel();
        panel.setBackground(new Color(230, 230, 230, 250));
        panel.setBounds(0, 0, 1024, 57);
        add(panel);
        panel.setLayout(null);

        border = new JPanel();
        border.setBackground(new Color(183, 183, 183));
        border.setBounds(0, 55, 1024, 2);
        panel.add(border);

        btnBack = new JButton("<");
        btnBack.setBounds(10, 23, 30, 27);
        btnBack.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
        btnBack.setForeground(Color.GRAY);
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnBack.setFocusPainted(false);
        btnBack.setOpaque(true);
        panel.add(btnBack);

        btnFor = new JButton(">");
        btnFor.setBounds(42, 23, 30, 27);
        btnFor.setFont(new Font("HelveticaNeueLT Pro 55 Roman", Font.PLAIN, 15));
        btnFor.setForeground(Color.GRAY);
        btnFor.setBackground(new Color(255, 255, 255));
        btnFor.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnFor.setFocusPainted(false);
        btnFor.setOpaque(true);
        panel.add(btnFor);

        txtLabel = new JTextPane();
        txtLabel.setForeground(Color.GRAY);
        txtLabel.setBackground(new Color(230, 230, 230));
        txtLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        txtLabel.setText("HA-IT Calendar Admin");
        txtLabel.setBounds(855, 30, 152, 20);
        panel.add(txtLabel);
    }

}
