package bcu.cmp5332.bookingsystem.gui;

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
 * Allows the user to view a flight's passengers via input in the GUi
 */
public class ShowFlightPassengersWindow extends JFrame implements ActionListener {
	
    private MainWindow mw;
    private JTextField flightText = new JTextField();

    private JButton showBtn = new JButton("Show");
    private JButton cancelBtn = new JButton("Cancel");

    public ShowFlightPassengersWindow(MainWindow mw) {
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

        setTitle("View Flight Passengers");

        setSize(250, 100); 
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2)); // changed from 5, 2 for it to be more legible
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(showBtn);
        bottomPanel.add(cancelBtn);

        showBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == showBtn) {
            try {
				showFlightPassengers();
			} catch (FlightBookingSystemException e) {
				JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
			} 
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    
    
    /**
     * Shows a flight's passengers in the GUI
     */
    private void showFlightPassengers() throws FlightBookingSystemException { 
        int flight = Integer.parseInt(flightText.getText());

		// refresh the view with the list of flight passengers
		mw.displayPassengers(flight);
		this.setVisible(false);

    }
   
}
