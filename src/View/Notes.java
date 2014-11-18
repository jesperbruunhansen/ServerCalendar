package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;


@SuppressWarnings("serial")
public class Notes extends JPanel {

    public JPanel panel;
    public JButton btnDelete;
    public JLabel lblHead;
    public JLabel lblConfirm;
    public JScrollPane scrollPane;
    public JTable table;
    public ListSelectionModel listSelectionModel;

    private int row;

    public Notes() {

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

        btnDelete = new JButton("Delete");
        btnDelete.setOpaque(true);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Dialog", Font.BOLD, 12));
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(BorderFactory.createLineBorder(new Color(183, 183, 183)));
        btnDelete.setBackground(new Color(204, 51, 51));
        btnDelete.setBounds(661, 630, 146, 32);
        panel.add(btnDelete);

        lblHead = new JLabel("Notes");
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

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public int getUserID(){
        int userID = row;
        return userID;
    }

    public void addListeners(ActionListener l){
        btnDelete.addActionListener(l);
    }

    public void setTable(Vector data, Vector columns){

        //Add date to a table
        table = new JTable(data, columns);

        //Add selection listeners
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new MyListSelectionListener());

        //Put table with data into a scroll panel to show the user
        scrollPane.setViewportView(table);

    }

    //Implement the selection listener
    class MyListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            row = table.getSelectedRow();
            ++row;
            lblConfirm.setText("Delete note with ID " + row);
            lblConfirm.setVisible(true);
        }

    };
}
