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
    private final List<Booking> bookings = new ArrayList<>(); // added to make sense - remove if error

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
    public List<Booking> getBookings() { // added even though not specified - remove if error
    	return Collections.unmodifiableList(bookings);
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
     * Adds customer to flight booking system
     * 
     * To implement
     * 
     * @throws FlightBookingSystemException thrown when there is already a customer
     *  in the system with the same email
     */
    public void addCustomer(Customer customer) throws FlightBookingSystemException {
        // TODO: implementation here
    	if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
    	for (Customer existing : customers.values()) {
            if (existing.getName().equals(customer.getName()) 
                && existing.getPhone().equals(customer.getPhone())) {
                throw new FlightBookingSystemException("There is a customer with the same name and phone number in the system");
            }
        }
    	customers.put(customer.getId(), customer);
    }
    
    /**
     * Adds booking to flight booking system
     * 
     * To implement
     * 
     * @throws FlightBookingSystemException thrown when there is already a booking 
     * in the system with the same customer and flight 
     */
    public void addBooking(Booking booking) throws FlightBookingSystemException { // added even though not specified - remove if error
    	if (bookings.contains(booking)) {
    		throw new FlightBookingSystemException("There is a booking with same "
                    + "customer and flight in the system");
    	}
    	bookings.add(booking);
    }
}
