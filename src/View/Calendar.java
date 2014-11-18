package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Calendar extends JPanel {

    public JPanel panel;
    public JButton btnDeleteUser;
    public JLabel lblHead;
    public JLabel lblConfirm;
    public JScrollPane scrollPane;
    public JTable table;

    public Calendar() {

        setLayout(null);
        setBounds(0, 0, 850, 711);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 850, 711);
        add(panel);
        panel.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 36, 784, 570);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        panel.add(scrollPane);

        btnDeleteUser = new JButton("Delete");
        btnDeleteUser.setOpaque(true);
        btnDeleteUser.setForeground(Color.WHITE);
        btnDeleteUser.setFont(new Font("Dialog", Font.BOLD, 12));
        btnDeleteUser.setFocusPainted(false);
        btnDeleteUser.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnDeleteUser.setBackground(new Color(204, 51, 51));
        btnDeleteUser.setBounds(661, 630, 146, 32);
        panel.add(btnDeleteUser);

        lblHead = new JLabel("Calendars");
        lblHead.setForeground(Color.GRAY);
        lblHead.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblHead.setBounds(23, 15, 132, 15);
        panel.add(lblHead);

        lblConfirm = new JLabel("Confirm");
        lblConfirm.setForeground(Color.GRAY);
        lblConfirm.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblConfirm.setBounds(513, 639, 138, 15);
        lblConfirm.setVisible(false);
        panel.add(lblConfirm);

    }

    public void addListeners(ActionListener l){
        btnDeleteUser.addActionListener(l);
    }
}
