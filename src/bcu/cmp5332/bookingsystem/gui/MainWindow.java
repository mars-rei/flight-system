package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 * Represents the main class for the GUI
 */
public class MainWindow extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu adminMenu;
	private JMenu flightsMenu;
	private JMenu bookingsMenu;
	private JMenu customersMenu;

	private JMenuItem adminExit;

	private JMenuItem flightsView;
	private JMenuItem flightsPassengers; // added
	private JMenuItem flightsAdd;
	private JMenuItem flightsDel;

	private JMenuItem bookingsView; // added
	private JMenuItem bookingsIssue;
	private JMenuItem bookingsUpdate;
	private JMenuItem bookingsCancel;

	private JMenuItem custView;
	private JMenuItem custBookings; // added
	private JMenuItem custAdd;
	private JMenuItem custDel;

	private FlightBookingSystem fbs;

	/**
	 * Initialises the AddBooking object
	 */
	public MainWindow(FlightBookingSystem fbs) {
		initialize();
		this.fbs = fbs;
	}

	/**
	 * Gets the flight booking system object
	 * 
	 * @return the flight booking system is returned
	 */
	public FlightBookingSystem getFlightBookingSystem() {
		return fbs;
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		setTitle("Flight Booking Management System");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//adding admin menu and menu items
		adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);

		adminExit = new JMenuItem("Exit");
		adminMenu.add(adminExit);
		adminExit.addActionListener(this);

		// adding Flights menu and menu items
		flightsMenu = new JMenu("Flights");
		menuBar.add(flightsMenu);

		flightsView = new JMenuItem("View");
		flightsPassengers = new JMenuItem("Passengers");
		flightsAdd = new JMenuItem("Add");
		flightsDel = new JMenuItem("Delete");
		flightsMenu.add(flightsView);
		flightsMenu.add(flightsPassengers);
		flightsMenu.add(flightsAdd);
		flightsMenu.add(flightsDel);
		// adding action listener for Flights menu items
		for (int i = 0; i < flightsMenu.getItemCount(); i++) {
			flightsMenu.getItem(i).addActionListener(this);
		}

		// adding Bookings menu and menu items
		bookingsMenu = new JMenu("Bookings");
		menuBar.add(bookingsMenu);

		bookingsView = new JMenuItem("View");
		bookingsIssue = new JMenuItem("Issue");
		bookingsUpdate = new JMenuItem("Update");
		bookingsCancel = new JMenuItem("Cancel");
		bookingsMenu.add(bookingsView);
		bookingsMenu.add(bookingsIssue);
		bookingsMenu.add(bookingsUpdate);
		bookingsMenu.add(bookingsCancel);
		// adding action listener for Bookings menu items
		for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
			bookingsMenu.getItem(i).addActionListener(this);
		}

		// adding Customers menu and menu items
		customersMenu = new JMenu("Customers");
		menuBar.add(customersMenu);

		custView = new JMenuItem("View");
		custBookings = new JMenuItem("Bookings");
		custAdd = new JMenuItem("Add");
		custDel = new JMenuItem("Delete");

		customersMenu.add(custView);
		customersMenu.add(custBookings);
		customersMenu.add(custAdd);
		customersMenu.add(custDel);
		// adding action listener for Customers menu items
		custView.addActionListener(this);
		custBookings.addActionListener(this);
		custAdd.addActionListener(this);
		custDel.addActionListener(this);

		setSize(800, 500);

		setVisible(true);
		setAutoRequestFocus(true);
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

	}	

	/**
	 * Loads the flight booking system data from file storage and creates a new main window object
	 */
	public static void main(String[] args) throws IOException, FlightBookingSystemException {
		FlightBookingSystem fbs = FlightBookingSystemData.load();
		new MainWindow(fbs);			
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == adminExit) {
			try {
				FlightBookingSystemData.store(fbs);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
			}
			System.exit(0);
		} else if (ae.getSource() == flightsView) {
			displayFlights();

		} else if (ae.getSource() == flightsPassengers) { // added to view passengers of a flight
			new ShowFlightPassengersWindow(this);

		} else if (ae.getSource() == flightsAdd) {
			new AddFlightWindow(this);

		} else if (ae.getSource() == flightsDel) {
			new DeleteFlightWindow(this);

		} else if (ae.getSource() == bookingsView) {
			displayBookings();
		
		} else if (ae.getSource() == bookingsIssue) {
			new AddBookingWindow(this);

		} else if (ae.getSource() == bookingsUpdate) {
			new EditBookingWindow(this);

		} else if (ae.getSource() == bookingsCancel) {
			new DeleteBookingWindow(this);

		} else if (ae.getSource() == custView) {
			displayCustomers();

		} else if (ae.getSource() == custBookings) { // added to view bookings of a customer
			new ShowCustomerBookingsWindow(this);

		}else if (ae.getSource() == custAdd) {
			new AddCustomerWindow(this);

		} else if (ae.getSource() == custDel) {
			new DeleteCustomerWindow(this);

		}
	}

	/**
	 * Displays flights in the GUI
	 */
	public void displayFlights() {
		List<Flight> flightsList = fbs.getFlights();
		// headers for the table
		String[] columns = new String[]{"ID", "Flight No", "Origin", "Destination", "Departure Date", "Capacity", "Price"};
		
		Object[][] data = new Object[flightsList.size()][7];
		
		for (int i = 0; i < flightsList.size(); i++) {
			Flight flight = flightsList.get(i);
			if (flight.getIsDeleted() == false && flight.getDepartureDate().isAfter(fbs.getSystemDate())) {
				data[i][0] = flight.getId(); 
				data[i][1] = flight.getFlightNumber();
				data[i][2] = flight.getOrigin();
				data[i][3] = flight.getDestination();
				data[i][4] = flight.getDepartureDate();
				data[i][5] = flight.getCapacity();
				data[i][6] = flight.getPrice();
			}
		}

		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}

	/**
	 * Displays passengers of flights in the GUI
	 */
	public void displayPassengers(int flightId) {
		try {
			Flight flightSelected = fbs.getFlightByID(flightId);

			List<Customer> passengers = flightSelected.getPassengers();

			// headers for the table
			String[] columns = new String[]{"ID", "Name", "Phone", "Email"};

			Object[][] data = new Object[passengers.size()][4];
			for (int i = 0; i < passengers.size(); i++) {
				Customer passenger = passengers.get(i);
				data[i][0] = passenger.getId(); 
				data[i][1] = passenger.getName();
				data[i][2] = passenger.getPhone();
				data[i][3] = passenger.getEmail();
			}

			JTable table = new JTable(data, columns);
			this.getContentPane().removeAll();
			this.getContentPane().add(new JScrollPane(table));
			this.revalidate();
		} catch (FlightBookingSystemException e) {
			JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Displays customers in the GUI
	 */
	public void displayCustomers() {
		List<Customer> customersList = fbs.getCustomers();
		// headers for the table
		String[] columns = new String[]{"ID", "Name", "Phone", "Email", "No. of Bookings"};

		Object[][] data = new Object[customersList.size()][5];
		for (int i = 0; i < customersList.size(); i++) {
			Customer customer = customersList.get(i);
			if (customer.getIsDeleted() == false) {
				data[i][0] = customer.getId();
				data[i][1] = customer.getName();
				data[i][2] = customer.getPhone();
				data[i][3] = customer.getEmail();
				data[i][4] = (customer.getBookings()).size();
			}
		}

		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}

	/**
	 * Displays bookings of customers in the GUI
	 */
	public void displayCustomerBookings(int customerId) {
		try {
			Customer customerSelected = fbs.getCustomerByID(customerId);

			List<Booking> bookings = customerSelected.getBookings();

			// headers for the table
			String[] columns = new String[]{"Booking Date", "Flight Id", "Flight No.", "Origin", "Destination", "Departure"};

			Object[][] data = new Object[bookings.size()][6];
			for (int i = 0; i < bookings.size(); i++) {
				Booking booking = bookings.get(i);
				data[i][0] = booking.getBookingDate();
				data[i][1] = booking.getFlight().getId(); 
				data[i][2] = booking.getFlight().getFlightNumber();
				data[i][3] = booking.getFlight().getOrigin();
				data[i][4] = booking.getFlight().getDestination();
				data[i][5] = booking.getFlight().getDepartureDate();
			}

			JTable table = new JTable(data, columns);
			this.getContentPane().removeAll();
			this.getContentPane().add(new JScrollPane(table));
			this.revalidate();
		} catch (FlightBookingSystemException e) {
			JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Displays bookings in the GUI
	 */
	public void displayBookings() {
		List<Booking> bookingsList = fbs.getBookings();
		// headers for the table
		String[] columns = new String[]{"ID", "Customer", "Flight", "Booking Date"};

		Object[][] data = new Object[bookingsList.size()][4];
		for (int i = 0; i < bookingsList.size(); i++) {
			Booking booking = bookingsList.get(i);
        	if ((booking.getCustomer().getIsDeleted() == false 
        			&& booking.getFlight().getIsDeleted() == false)
        			&& booking.getFlight().getDepartureDate().isAfter(fbs.getSystemDate())) {
				data[i][0] = booking.getId();
				data[i][1] = booking.getCustomer().getName();
				data[i][2] = booking.getFlight().getFlightNumber();
				data[i][3] = booking.getBookingDate();
        	}
		}

		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}
}
