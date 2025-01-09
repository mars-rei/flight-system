// given - to complete

package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

/**
 * A class that models the entire booking system
 */
public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.parse("2024-11-11");
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();
    private final Map<Integer, Booking> bookings = new TreeMap<>(); // added even if not specified

    /**
     * Returns the system date
     */
    public LocalDate getSystemDate() {
        return systemDate;
    }

    /**
     * Returns an unmodifiable list of all flights in system
     * 
     * Given implemented
     */
    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }
    
    /**
     * Returns an unmodifiable list of all customers in system
     * 
     * To implement
     */
    public List<Customer> getCustomers() {
    	List<Customer> out = new ArrayList<>(customers.values());
    	return Collections.unmodifiableList(out);
    }
    
    /**
     * Returns an unmodifiable list of all bookings in system
     * 
     * To implement
     */
    public List<Booking> getBookings() { // added even though not specified 
    	List<Booking> out = new ArrayList<>(bookings.values());
    	return Collections.unmodifiableList(out);
    }

    /**
     * Finds and returns a flight using the flight id
     * 
     * Given implemented
     * 
     * @throws FlightBookingSystemException thrown when there is no flight with the given id in the system
     */
    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    /**
     * Finds and returns a customer using the customer id
     * 
     * To implement
     * 
     * @throws FlightBookingSystemException thrown when there is no customer with the given id in the system
     */
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        // TODO: implementation here
    	if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }
    
    /**
     * Finds and returns a booking using the booking id
     * 
     * @throws FlightBookingSystemException thrown when there is no booking with the given id in the system
     */
    public Booking getBookingByID(int id) throws FlightBookingSystemException {
        // TODO: implementation here
    	if (!bookings.containsKey(id)) {
            throw new FlightBookingSystemException("There is no booking with that ID.");
        }
        return bookings.get(id);
    }

    /**
     * Adds flight to flight booking system
     * 
     * Given implemented
     * 
     * @throws FlightBookingSystemException thrown when there is already a flight 
     * in the system with the same number and departure date
     */
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }
    
    /**
     * Removes flight from flight booking system
     */
    public void removeFlight(Flight flight){
    	// original code to fully delete the flight from the fbs
    	// flights.remove(flight.getId(), flight);
    	
    	// new code to hide deleted flights
    	flight.setIsDeleted(true);
    }

    /**
     * Adds customer to flight booking system
     * @throws FlightBookingSystemException thrown when there is already a customer
     *  in the system with the same email
     */
    public void addCustomer(Customer customer) throws FlightBookingSystemException {
    	if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
    	for (Customer existing : customers.values()) {
            if (existing.getEmail().equals(customer.getEmail())) {
                throw new FlightBookingSystemException("There is a customer with the same email in the system");
            }
        }
    	customers.put(customer.getId(), customer);
    }
    
    /**
     * Removes customer from flight booking system
     */
    public void removeCustomer(Customer customer){
    	// original code to fully delete the customer from the fbs
    	// customers.remove(customer.getId(), customer);
    	
    	// new code to hide deleted customers
    	customer.setIsDeleted(true);
    }
    
    /**
     * Adds booking to flight booking system
     * 
     * @throws FlightBookingSystemException thrown when there is already a booking 
     * in the system with the same customer and flight 
     */
    public void addBooking(Booking booking) throws FlightBookingSystemException { // added even though not specified
    	if (bookings.containsKey(booking.getId())) {
            throw new IllegalArgumentException("Duplicate booking ID.");
        }
    	
    	for (Booking existing : bookings.values()) {
            if (existing.getCustomer().equals(booking.getCustomer())
            		&& existing.getFlight().equals(booking.getFlight())) {
                throw new FlightBookingSystemException("There is a booking with same customer and flight in the system");
            }
        }
    	
    	if (booking.getFlight().getPassengers().size() == booking.getFlight().getCapacity()) {
    		throw new FlightBookingSystemException("This flight has reached its capacity. Booking unsuccessful.");
    	}
    	
    	bookings.put(booking.getId(), booking);
    }
    
    // TODO
    public void editBooking(Flight newFlight) throws FlightBookingSystemException { // added even though not specified
    	// if user tries to update booking to a full flight
    	if (newFlight.getPassengers().size() == newFlight.getCapacity()) {
    		throw new FlightBookingSystemException("This flight has reached its capacity. Booking unsuccessful.");
    	}
    }
    
    /**
     * Deletes booking from flight booking system
     * 
     * To implement
     * 
     * @throws FlightBookingSystemException thrown when there isn't a booking 
     * in the system with the customer and flight specified
     */
    public void cancelBooking(Customer customer, Flight flight) throws FlightBookingSystemException { // added even though not specified
    	// finds booking that matches the customer and flight
    	Booking bookingFound = null;
        for (Booking existing : bookings.values()) {
            if (existing.getCustomer() == customer && existing.getFlight() == flight) {
            	bookingFound = existing;
            }
        }
        
        if (bookingFound != null) {
        	bookings.remove(bookingFound.getId(), bookingFound);
        } else {
        	throw new FlightBookingSystemException("There is no booking with this customer and flight in the system");
        }
    }
}
