// added - completed

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the showCustomer command
 * 
 * implements the interface Command
 */
public class ShowCustomer implements Command {
	
	private int id;

    /**
     * Represents the showCustomer command constructor
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
        // TODO: implementation here
    	Customer customer = flightBookingSystem.getCustomerByID(id);
    	System.out.println(customer.getDetailsLong()); 
    }
}
