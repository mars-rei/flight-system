// added

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * Represents the listBookings command
 * 
 * implements the interface Command
 */
public class ListBookings implements Command {

    /**
     * Executes the listBookings command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Booking> bookings = flightBookingSystem.getBookings();
        for (Booking booking : bookings) {
            System.out.println(booking.getDetailsShort());
        }
        System.out.println(bookings.size() + " booking(s)");
    }
}
