package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
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
 * Allows the user to update a booking in the flight booking system via the GUI
 */
public class EditBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookingIdText = new JTextField();
    private JTextField flightIdText = new JTextField();

    private JButton updateBtn = new JButton("Update");
    private JButton cancelBtn = new JButton("Cancel");

    public EditBookingWindow(MainWindow mw) {
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

        setTitle("UpdateBooking");

        setSize(400, 220); 
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1)); 
        topPanel.add(new JLabel("Booking Id : "));
        topPanel.add(bookingIdText);
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(updateBtn);
        bottomPanel.add(cancelBtn);

        updateBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            editBooking(); 
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    /**
     * Adds the booking to the flight booking system via the GUI
     */
    private void editBooking() { 
        try {
        	int bookingId = Integer.parseInt(bookingIdText.getText());
        	int flightId = Integer.parseInt(flightIdText.getText());
            
            Command editBooking = new EditBooking(bookingId, flightId);
            editBooking.execute(mw.getFlightBookingSystem());
            mw.displayBookings();
            this.setVisible(false);
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
