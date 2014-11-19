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
public class Notes extends JPanel {

    public JPanel panel;
    public JButton btnDelete;
    public JButton btnChooseCal;
    public JButton btnChooseEvent;
    public JLabel lblHead;
    public JLabel lblConfirm;
    public JScrollPane scrollPane;
    public JTable table;
    public ListSelectionModel listSelectionModel;

    private int row;

    public Notes() {

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

        btnChooseCal = new JButton("Choose Calendar");
        btnChooseCal.setOpaque(true);
        btnChooseCal.setForeground(Color.WHITE);
        btnChooseCal.setFont(new Font("Dialog", Font.BOLD, 12));
        btnChooseCal.setFocusPainted(false);
        btnChooseCal.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnChooseCal.setBackground(new Color(102, 204, 153));
        btnChooseCal.setBounds(661, 630, 146, 32);
        panel.add(btnChooseCal);

        btnChooseEvent = new JButton("Choose Event");
        btnChooseEvent.setOpaque(true);
        btnChooseEvent.setForeground(Color.WHITE);
        btnChooseEvent.setFont(new Font("Dialog", Font.BOLD, 12));
        btnChooseEvent.setFocusPainted(false);
        btnChooseEvent.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnChooseEvent.setBackground(new Color(102, 204, 153));
        btnChooseEvent.setBounds(661, 630, 146, 32);
        panel.add(btnChooseEvent);

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

        lblHead = new JLabel("Notes: Please choose a calendar from the list");
        lblHead.setForeground(Color.GRAY);
        lblHead.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblHead.setBounds(23, 15, 400, 15);
        panel.add(lblHead);

        lblConfirm = new JLabel("Choose Calendar");
        lblConfirm.setForeground(Color.GRAY);
        lblConfirm.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblConfirm.setBounds(513, 639, 138, 15);
        lblConfirm.setVisible(false);
        panel.add(lblConfirm);

    }

    //GET CHOOSE CALENDAR BUTTON
    public JButton getBtnChooseCal() {
        return btnChooseCal;
    }

    //GET CHOOSE EVENT BUTTON
    public JButton getBtnChooseEvent() {
        return btnChooseEvent;
    }

    //RETURN DELETE BUTTON
    public JButton getBtnDelete() {
        return btnDelete;
    }

    //RETURN THE CHOSEN ID
    public int getSelectedID(){
        int selectedID = row;
        return selectedID;
    }

    //ADD ACTIONLISTENER
    public void addListeners(ActionListener l){
        btnChooseCal.addActionListener(l);
        btnChooseEvent.addActionListener(l);
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
    class MyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            row = table.getSelectedRow();
            ++row;

            //Set label with information
            lblConfirm.setText("Choose event with ID " + row);
            lblConfirm.setVisible(true);
        }

    };
}
