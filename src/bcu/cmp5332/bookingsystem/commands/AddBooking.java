// added - to complete

package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the addBooking command
 */
public class AddBooking implements Command {

    private final int customerId;
    private final int flightId;
    // private final LocalDate bookingDate;

    /**
     * Represents the addBooking command constructor
     * 
     * @param customerId customer id (integer)
     * 
     * @param flightId flight id (integer)
     */
    public AddBooking(int customerId, int flightId) {
    	this.customerId = customerId;
        this.flightId = flightId;
        // this.bookingDate = bookingDate;
    }

    /**
     * Represents the addBooking command constructor
     * 
     * @param flightBookingSystem FlightBookingSystem object
     * 
     * {@inheritDoc}
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	Customer customer = flightBookingSystem.getCustomerByID(customerId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);
    	LocalDate bookingDate = flightBookingSystem.getSystemDate();
        
        Booking booking = new Booking(customer, flight, bookingDate);
        customer.addBooking(booking); // still need to implement method in the model customer class
        flight.addPassenger(customer); // still need to implement method in the model flight class
        System.out.println("Booking issued succesfully.");
    }
}
