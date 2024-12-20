// given - to complete

package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class modelling a customer in the flight booking system
 */
public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings;;
    
    /**
     * Represents the customer class constructor
     * 
     * @param id the customer's id (int)
     * 
     * @param name the customer's name (string)
     * 
     * @param phone the customer's phone number (string)
     */
    
    // TODO: implement constructor here
    
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        
        bookings = new ArrayList<>();
    }
    
    // TODO: implementation of Getter and Setter methods
    
    /**
     * Returns the customer id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the customer id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Returns the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Returns the customer bookings
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    
    /**
     * Returns the customer details in short form
     */
    public String getDetailsShort() {
        return "Customer #" + getId() + " - " + getName() + " - " + getPhone();
    }
    
    /**
     * Returns the customer details in long form
     */
    public String getDetailsLong() {
    	String longDetails = "Customer #" + getId() + "\nName: " + getName() + "\nPhone: " + getPhone() + "\n---------------" + "\nBookings:";
    	for (Booking booking: getBookings()) {
    		longDetails += "\n Booking date: ";
    		longDetails += booking.getBookingDate() + " for Flight #";
    		longDetails += booking.getFlight().getId() + " - ";
    		longDetails += booking.getFlight().getFlightNumber() + " - ";
    		longDetails += booking.getFlight().getOrigin() + " to ";
    		longDetails += booking.getFlight().getDestination() + " on ";
    		longDetails += booking.getFlight().getDepartureDate();
    	}
    	longDetails += "\n" + getBookings().size() + " booking(s)";
    	return longDetails;
    }
    
    /**
     * Adds a customer booking
     */
    public void addBooking(Booking booking) throws FlightBookingSystemException { 
        // TODO: implementation here
    	if (getBookings().contains(booking)) {
    		throw new FlightBookingSystemException("There is a booking with same customer and flight in the system");
    	} else {
    		bookings.add(booking);
    	}
    }
    
    /**
     * Cancels a customer booking
     */
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
        // TODO: implementation here
    	Boolean found = false;
    	Booking bookingFound = null;
    	
    	for (Booking booking: getBookings()) {
    		if (booking.getFlight() == flight) {
    			found = true;
    			bookingFound = booking;
    		}
    	}
    	
    	if (found == false) {
    		throw new FlightBookingSystemException("There is no booking in the list that contains this flight.");
    	} else {
    		bookings.remove(bookingFound);
    	}
    }
}