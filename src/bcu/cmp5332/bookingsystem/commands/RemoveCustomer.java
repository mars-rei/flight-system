package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the removeCustomer command
 * 
 * Implements the interface Command
 */
public class RemoveCustomer implements Command {
	
	private int id;

    /**
     * Initialises the RemoveCustomer object
     * 
     * @param id the customer's id (int)
     */
    public RemoveCustomer(int id) {
    	this.id = id;
    }

    /**
     * Executes the removeCustomer command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	Customer customer = flightBookingSystem.getCustomerByID(id);
    	flightBookingSystem.removeCustomer(customer);    	
    	System.out.println("Customer #" + id + " removed.");
    }
}
