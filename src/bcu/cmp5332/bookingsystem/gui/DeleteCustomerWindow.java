package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.RemoveCustomer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Allows the user to delete a customer from the flight booking system via the GUI
 */
public class DeleteCustomerWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField idText = new JTextField();

    private JButton deleteBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    public DeleteCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Delete a Customer");

        setSize(400, 100);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2)); 
        topPanel.add(new JLabel("ID : "));
        topPanel.add(idText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(deleteBtn);
        bottomPanel.add(cancelBtn);

        deleteBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteBtn) {
            deleteCustomer(); 
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    /**
     * Deletes a customer from the flight booking system via the GUI
     */
    private void deleteCustomer() { 
        try {
        	int id = Integer.parseInt(idText.getText());
            
            // create and execute the RemoveCustomer Command
            Command removeCustomer = new RemoveCustomer(id);
            removeCustomer.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of customers
            mw.displayCustomers();
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
