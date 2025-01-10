package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the cancelBooking command
 * 
 * Implements the interface Command
 */
public class CancelBooking implements Command {

    private final int customerId;
    private final int flightId;

    /**
     * Initialises the cancelBooking object
     * 
     * @param customerId the booking's customer id (int)
     * 
     * @param flightId the booking's flight id (int)
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
    	Customer customer = flightBookingSystem.getCustomerByID(customerId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);
        
        customer.cancelBookingForFlight(flight);
        flight.removePassenger(customer); 
        flightBookingSystem.cancelBooking(customer, flight);
        System.out.println("Booking was cancelled succesfully.");
    }
    
    /*
     * Restores previous state when there is an error storing data
     */
    @Override 
    public void rollback(FlightBookingSystem flightBookingSystem, Booking booking) {  
    	System.out.println("Error updating booking data.");
		try {
			Customer bookingCustomer = booking.getCustomer();
			Flight bookingFlight = booking.getFlight();
	    	
	        Booking resetBooking = new Booking(booking.getId(), bookingCustomer, bookingFlight, booking.getBookingDate());
	        bookingCustomer.addBooking(resetBooking); 
	        bookingFlight.addPassenger(bookingCustomer);
	        flightBookingSystem.addBooking(resetBooking);
	        
		} catch (FlightBookingSystemException e) {
			System.out.println(e.getMessage());
		}
    	
		System.out.println("Booking cancellation withdrawn.");
		
    }
}
