// added - completed

package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;
import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the addBooking command
 * 
 * implements the interface Command
 */
public class AddBooking implements Command {

    private final int customerId;
    private final int flightId;
    private LocalDate bookingDate;

    /**
     * Represents the addBooking command constructor
     * 
     * @param customerId the booking's customer id (int)
     * 
     * @param flightId the booking's flight id (int)
     * 
     * @param bookingDate the date of booking (LocalDate)
     */
    public AddBooking(int customerId, int flightId, LocalDate bookingDate) {
    	this.customerId = customerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
    }

    /**
     * Executes the addBooking command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	int maxId = 0;
        if (flightBookingSystem.getBookings().size() > 0) {
            int lastIndex = flightBookingSystem.getBookings().size() - 1;
            maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
        }
        
    	Customer customer = flightBookingSystem.getCustomerByID(customerId);
    	Flight flight = flightBookingSystem.getFlightByID(flightId);
    	
    	LocalDate bookedDate;
    	if (bookingDate == null) { // if booking is added by user
    		bookedDate = flightBookingSystem.getSystemDate();
    	} else { // if booking is added by booking data manager (solves date booked overriding)
    		bookedDate = bookingDate; 
    	}

        Booking booking = new Booking(++maxId, customer, flight, bookedDate);
        flightBookingSystem.addBooking(booking);
        customer.addBooking(booking); 
        flight.addPassenger(customer);
        
        if (bookingDate == null) { // only prints message if booking made by user
        	System.out.println("Booking #" + booking.getId() + " added.");
    	} 
    }
    
    @Override
    public void rollback(FlightBookingSystem flightBookingSystem) {  
    	System.out.println("Error storing new booking data.");
    	
    	Booking mostRecent = (flightBookingSystem.getBookings()).get(flightBookingSystem.getBookings().size()-1);
    	Customer customer = mostRecent.getCustomer();
    	Flight flight = mostRecent.getFlight();
    	
    	try {
			customer.cancelBookingForFlight(flight);
			flight.removePassenger(customer); 
	        flightBookingSystem.cancelBooking(customer, flight);
		} catch (FlightBookingSystemException e) {
			System.out.println(e.getMessage());
		}
        
		System.out.println("Booking addition withdrawn.");
       
    }
}
