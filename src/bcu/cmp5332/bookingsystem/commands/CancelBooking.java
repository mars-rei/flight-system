// UNFINISHED - NEED TO EDIT AFTER FINISHING ADD BOOKING

package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

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
    
    @Override // TODO
    public void rollback(FlightBookingSystem flightBookingSystem, int customer, int flight) {  
    	System.out.println("Error updating booking data.");
    	
		try {
			Customer bookingCustomer = flightBookingSystem.getCustomerByID(customerId);
			Flight bookingFlight = flightBookingSystem.getFlightByID(flightId);
	    	
	    	// rollback will reset the booking date - so this method is still inaccurate
	    	LocalDate bookedDate = flightBookingSystem.getSystemDate();
	        
	    	int lastIndex = flightBookingSystem.getBookings().size() - 1;
            int maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
	    	
	        Booking booking = new Booking(++maxId, bookingCustomer, bookingFlight, bookedDate);
	        bookingCustomer.addBooking(booking); 
	        bookingFlight.addPassenger(bookingCustomer);
	        flightBookingSystem.addBooking(booking);
	        
		} catch (FlightBookingSystemException e) {
			System.out.println(e.getMessage());
		}
    	
		System.out.println("Booking cancellation withdrawn.");
		
    }
}
