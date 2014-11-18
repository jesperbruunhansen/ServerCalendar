package View;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;

@SuppressWarnings("serial")
public class UserList extends JPanel {

    // PANELS //
    public JPanel panel;
    public JButton btnDelete;
    public JLabel lblHead;
    public JLabel lblDelete;
    public JScrollPane scrollPane;
    public JTable table;
    public ListSelectionModel listSelectionModel;

    public UserList() {

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

        lblHead = new JLabel("User List");
        lblHead.setForeground(Color.GRAY);
        lblHead.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblHead.setBounds(23, 15, 132, 15);
        panel.add(lblHead);

        lblDelete = new JLabel("Confirm");
        lblDelete.setForeground(Color.GRAY);
        lblDelete.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblDelete.setBounds(513, 639, 138, 15);
        lblDelete.setVisible(false);
        panel.add(lblDelete);

    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public String getLblDelete() {
        return lblDelete.getText();
    }

    public JTable getTable() {
        return table;
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
    class MyListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            //System.out.println("valueChanged: " + e.toString());
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            int selectedItem = (int)table.getValueAt(row, col);
            System.out.println(row + " : " + col + " = " + selectedItem);
            lblDelete.setText("Delete user with ID " + selectedItem);
            lblDelete.setVisible(true);
        }

    };

}