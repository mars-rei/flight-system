package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * Represents the listBookings command
 * 
 * Implements the interface Command
 */
public class ListBookings implements Command {

    /**
     * Executes the listBookings command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Booking> bookings = flightBookingSystem.getBookings();
        
        // for if the customer or flight has been removed or the booking has been completed
        int invalidBookings = 0;
        
        for (Booking booking : bookings) {
        	if ((booking.getCustomer().getIsDeleted() == false 
        			&& booking.getFlight().getIsDeleted() == false)
        			&& booking.getFlight().getDepartureDate().isAfter(flightBookingSystem.getSystemDate())) {
        		System.out.println(booking.getDetailsShort());
        	} else {
        		++invalidBookings;
        	}
        }
        
        System.out.println((bookings.size() - invalidBookings) + " booking(s)");
    }
}
