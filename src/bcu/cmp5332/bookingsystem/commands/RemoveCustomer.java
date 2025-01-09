package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the removeCustomer command
 * 
 * implements the interface Command
 */
public class RemoveCustomer implements Command {
	
	private int id;

    /**
     * Represents the removeCustomer command constructor
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
