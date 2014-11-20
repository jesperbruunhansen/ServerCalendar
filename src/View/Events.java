package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Casper on 14/11/14.
 */
public class Events extends JPanel {

    public JPanel panel;
    public JButton btnDelete;
    public JButton btnChoose;
    public JLabel lblHead;
    public JLabel lblConfirm;
    public JScrollPane scrollPane;
    public JTable table;
    public ListSelectionModel listSelectionModel;

    private String userID;

    public Events() {

        //OVERALL PANEL SETTINGS
        setLayout(null);
        setBounds(0, 0, 850, 711);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 850, 711);
        add(panel);
        panel.setLayout(null);

        //CONTENT ON PANEL
        scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 36, 784, 570);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        panel.add(scrollPane);

        btnChoose = new JButton("Choose Calendar");
        btnChoose.setOpaque(true);
        btnChoose.setForeground(Color.WHITE);
        btnChoose.setFont(new Font("Dialog", Font.BOLD, 12));
        btnChoose.setFocusPainted(false);
        btnChoose.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnChoose.setBackground(new Color(102, 204, 153));
        btnChoose.setBounds(661, 630, 146, 32);
        panel.add(btnChoose);

        btnDelete = new JButton("Delete");
        btnDelete.setOpaque(true);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnDelete.setBackground(new Color(204, 51, 51));
        btnDelete.setBounds(661, 630, 146, 32);
        btnDelete.setVisible(false);
        panel.add(btnDelete);

        lblHead = new JLabel("Events: Please choose a calendar from the list");
        lblHead.setForeground(Color.GRAY);
        lblHead.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblHead.setBounds(23, 15, 400, 15);
        panel.add(lblHead);

        lblConfirm = new JLabel("Choose Calendar");
        lblConfirm.setForeground(Color.GRAY);
        lblConfirm.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblConfirm.setBounds(466, 639, 170, 15);
        lblConfirm.setVisible(false);
        panel.add(lblConfirm);

    }

    //GET CHOOSE BUTTON
    public JButton getBtnChoose() {
        return btnChoose;
    }

    //RETURN DELETE BUTTON
    public JButton getBtnDelete() {
        return btnDelete;
    }

    //RETURN THE CHOSEN ID
    public String getSelectedID(){
        return userID;
    }

    //SET THE CHOSEN ID
    public void setUserID(){
        userID = "0";
    }

    //ADD ACTIONLISTENER
    public void addListeners(ActionListener l){
        btnChoose.addActionListener(l);
        btnDelete.addActionListener(l);
    }

    //SET TABLE WITH DATA AND ADD TO SCROLLPANE
    public void setTable(Vector data, Vector columns){

        //Add date to a table
        table = new JTable(data, columns);

        //Add selection listeners
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new MyListSelectionListener());

        //Put table with data into a scroll panel to show the user
        scrollPane.setViewportView(table);

    }

    //IMPLEMENT SELECTIONLISTENER ON TABLE
    class MyListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int row = table.getSelectedRow();
            userID = (table.getValueAt(row, 0)).toString();

            lblConfirm.setText("Choose calendar with ID " + userID);
            lblConfirm.setVisible(true);
        }

    };
}
