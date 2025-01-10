package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * A class modelling a booking in the flight booking system
 */
public class Booking {
    
	private int id;
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    
    /**
     * Initialises the Booking object
     * 
     * @param id the id of the booking (int)
     * 
     * @param customer the customer who did the booking (customer object)
     * 
     * @param flight the flight the customer booked (flight object)
     * 
     * @param bookingDate the date the flight was booked (local date)
     */
    public Booking(int id, Customer customer, Flight flight, LocalDate bookingDate) {
    	this.id = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }
    
    /**
     * Returns the booking id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the booking id
     */
    public void setId(int id) {
        this.id = id;
    }
    
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
    
    /**
     * Returns the booking details in short form
     */
    public String getDetailsShort() {
        return "Booking #" + getId() + " - " + getCustomer().getName() + " - " + getFlight().getFlightNumber() + " - " + getBookingDate();
    }
    
    /**
     * Returns the booking details in long form
     */
    public String getDetailsLong() {
    	String longDetails = "";
    	longDetails += "Booking #" + getId();
    	longDetails += " - Booked by customer #" + getCustomer().getId();
    	longDetails += " - " + getCustomer().getName();
    	longDetails += "\n" + getBookingDate() + " for Flight #";
    	longDetails += getFlight().getId() + " - ";
    	longDetails += getFlight().getFlightNumber() + " - ";
    	longDetails += getFlight().getOrigin() + " to ";
    	longDetails += getFlight().getDestination() + " on ";
    	longDetails += getFlight().getDepartureDate();
    	return longDetails;
    }
}
