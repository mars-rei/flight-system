package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the showCustomer command
 * 
 * Implements the interface Command
 */
public class ShowCustomer implements Command {
	
	private int id;

    /**
     * Initialises the ShowCustomer object
     * 
     * @param id the customer's id (int)
     */
    public ShowCustomer(int id) {
    	this.id = id;
    }

    /**
     * Executes the showCustomer command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	Customer customer = flightBookingSystem.getCustomerByID(id);
    	System.out.println(customer.getDetailsLong()); 
    	if (customer.getIsDeleted() == true) {
    		System.out.println("This customer has been deleted.");
    	}
    }
}
