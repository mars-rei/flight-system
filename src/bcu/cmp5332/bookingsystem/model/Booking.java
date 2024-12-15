// given - completed

package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * A class modelling a booking in the flight booking system
 */
public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    
    /**
     * Represents the booking class constructor
     * 
     * @param customer the customer who did the booking (customer object)
     * 
     * @param flight the flight the customer booked (flight object)
     * 
     * @param bookingDate the date the flight was booked (local date)
     */
    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }
    
    // TODO: implementation of Getter and Setter methods
    
    /**
     * Returns the customer object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer object
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Returns the flight object
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Sets the flight object
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    /**
     * Returns the booking date
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the booking date
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
