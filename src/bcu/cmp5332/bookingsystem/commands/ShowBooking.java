package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the showBooking command
 * 
 * Implements the interface Command
 */
public class ShowBooking implements Command {
	
	private int id;

    /**
     * Initialises the ShowBooking object
     * 
     * @param id the booking's id (int)
     */
    public ShowBooking(int id) {
    	this.id = id;
    }

    /**
     * Executes the showBooking command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	Booking booking = flightBookingSystem.getBookingByID(id);
    	System.out.println(booking.getDetailsLong()); 
    }
}
