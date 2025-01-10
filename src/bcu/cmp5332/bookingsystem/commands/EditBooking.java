package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the editBooking command
 * 
 * Implements the interface Command
 */
public class EditBooking implements Command {

	private final int bookingId;
	private final int flightId;

	/**
	 * Initialises the EditBooking object
	 * 
	 * @param bookingId the booking's id (int)
	 * 
	 * @param flightId  the booking's new flight id (int)
	 */
	public EditBooking(int bookingId, int flightId) {
		this.bookingId = bookingId;
		this.flightId = flightId;
	}

	/**
	 * Executes the editBooking command
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Booking booking = flightBookingSystem.getBookingByID(bookingId);
		Flight flight = flightBookingSystem.getFlightByID(flightId);
		Flight oldFlight = booking.getFlight();

		Customer customer = booking.getCustomer();

		flightBookingSystem.editBooking(flight, booking);

		booking.setFlight(flight);
		flight.addPassenger(customer);
		oldFlight.removePassenger(customer);

		System.out.println("Booking was edited and updated succesfully.");
	}

	/**
     * Restores previous state when there is an error storing data
     * @throws FlightBookingSystemException 
     */
    @Override 
    public void rollback(FlightBookingSystem flightBookingSystem, int bookingId, int newFlight, Flight originalFlight) {  
    	System.out.println("Error storing updated booking data.");
    	
    	try {
			Booking booking = flightBookingSystem.getBookingByID(bookingId);
			Flight flight = flightBookingSystem.getFlightByID(newFlight);
			
			Customer customer = booking.getCustomer();
			
			flightBookingSystem.editBooking(originalFlight, booking);
			
			booking.setFlight(originalFlight);
			originalFlight.addPassenger(customer);
			flight.removePassenger(customer);
			
		} catch (FlightBookingSystemException e) {
			System.out.println(e.getMessage());
		}
    	
        
		System.out.println("Booking update withdrawn.");
		
    }
}
