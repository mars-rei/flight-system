// added - to complete

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command {

    private final int customerId;
    private final int flightId;

    public CancelBooking(int customerId, int flightId) {
    	this.customerId = customerId;
        this.flightId = flightId;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	Customer customer = flightBookingSystem.getCustomerByID(customerId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);
        
        customer.cancelBookingForFlight(flight); // still need to implement method in the model customer class
        flight.removePassenger(customer); // still need to implement method in the model flight class
        System.out.println("Booking was cancelled succesfully.");
    }
}
