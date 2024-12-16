// added - to complete

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

    private final int customerId;
    private final int flightId;

    /**
     * Represents the editBooking command constructor
     * 
     * @param customerId the booking's customer id (int)
     * 
     * @param flightId the booking's flight id (int)
     */
    public EditBooking(int customerId, int flightId) {
    	this.customerId = customerId;
        this.flightId = flightId;
    }

    /**
     * Executes the editBooking command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	Customer customer = flightBookingSystem.getCustomerByID(customerId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);

    	// plays with the setter method in Booking class
        System.out.println("Booking was edited and updated succesfully.");
    }
}
