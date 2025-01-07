// added - completed!

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the cancelBooking command
 * 
 * implements the interface Command
 */
public class CancelBooking implements Command {

    private final int customerId;
    private final int flightId;

    /**
     * Represents the cancelBooking command constructor
     * 
     * @param customerId customer id of booking (int)
     * 
     * @param flightId flight id of booking (int)
     */
    public CancelBooking(int customerId, int flightId) {
    	this.customerId = customerId;
        this.flightId = flightId;
    }

    /**
     * Executes the cancelBooking command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	Customer customer = flightBookingSystem.getCustomerByID(customerId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);
        
        customer.cancelBookingForFlight(flight);
        flight.removePassenger(customer); 
        flightBookingSystem.cancelBooking(customer, flight);
        System.out.println("Booking was cancelled succesfully.");
    }
    
    @Override
    public void rollback(FlightBookingSystem flightBookingSystem) {  
    	System.out.println("Error updating booking data.");
    	
    	// idk how to retrieve this info unless we use a temporary file to store new changes instead
        
		System.out.println("Booking cancellation withdrawn.");
		
    }
}
