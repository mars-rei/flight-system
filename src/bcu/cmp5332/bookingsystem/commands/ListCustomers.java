package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * Represents the listCustomers command
 * 
 * Implements the interface Command
 */
public class ListCustomers implements Command {

	/**
	 * Executes the listCustomers command
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		List<Customer> allCustomers = flightBookingSystem.getCustomers();
		
		// getting only valid customers
		int notHiddenCustomers = 0;
		
		for (Customer customer : allCustomers) {
			if (customer.getIsDeleted() == false) {
				System.out.println(customer.getDetailsShort()); 
				++notHiddenCustomers;
			}
		}

		System.out.println(notHiddenCustomers + " customer(s)");
	}
}
