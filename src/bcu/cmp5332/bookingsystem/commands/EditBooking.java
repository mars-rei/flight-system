// UNFINISHED

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the editBooking command
 * 
 * implements the interface Command
 */
public class EditBooking implements Command {

    private final int bookingId;
    private final int flightId;

    /**
     * Represents the editBooking command constructor
     * 
     * @param bookingId the booking's id (int)
     * 
     * @param flightId the booking's new flight id (int)
     */
    public EditBooking(int bookingId, int flightId) {
    	this.bookingId = bookingId;
        this.flightId = flightId;
    }

    /**
     * Executes the editBooking command
     */
    @Override // TODO
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	Booking booking = flightBookingSystem.getBookingByID(bookingId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);
    	Flight oldFlight = booking.getFlight();
    	
    	Customer customer = booking.getCustomer();
    	
    	booking.setFlight(flight);
    	flight.addPassenger(customer);
    	oldFlight.removePassenger(customer);
    	
    	System.out.println("Booking was edited and updated succesfully.");
    }
    
    /*@Override // TODO
    public void rollback(FlightBookingSystem flightBookingSystem) {  
    	System.out.println("Error storing updated booking data.");
    	
    	// idk how to retrieve this info unless we use a temporary file to store new changes instead
        
		System.out.println("Booking update withdrawn.");
		
    }*/
}
