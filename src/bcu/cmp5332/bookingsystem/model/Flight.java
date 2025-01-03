// given - to complete

package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class modelling a flight in the flight booking system
 */
public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private double price;

    private final Set<Customer> passengers;

    /**
     * Represents the flight class constructor
     * 
     * @param id the flight's id (int)
     * 
     * @param flightNumber the flight's number (string)
     * 
     * @param origin the airport the flight takes off at (string)
     * 
     * @param destination the airport the flight lands at (string)
     * 
     * @param departureDate the date the flight departs (local date)
     * 
     * @param capacity the number of seats in the flight (int)
     * 
     * @param price the price of the flight (double)
     */
    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate,
    		int capacity, double price) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        
        passengers = new HashSet<>();
    }

    /**
     * Returns the flight id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the flight id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    /**
     * Returns the flight origin
     */
    public String getOrigin() {
        return origin;
    }
    
    /**
     * Sets the flight origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Returns the flight destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the flight destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Returns the flight departure date
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the flight departure date
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Returns the flight capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the flight capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    /**
     * Returns the price of the flight
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the flight
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Returns the flight passengers
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    /**
     * Returns the flight details in a short format
     */
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    /**
     * Returns the flight details in a long format
     */
    public String getDetailsLong() {
        // TODO: implementation here
    	String longDetails = "Flight #" + id + "\nFlight No: " + flightNumber + "\nOrigin: " + origin
    			+ "\nDestination: " + destination + "\nDeparture Date: " + departureDate + 
    			"\nNumber of seats: " + capacity + "\nPrice: £" + price + 
    			"\n---------------" + "\nPassengers:";
    	for (Customer passenger: getPassengers()) {
    		longDetails += "\n * Id: ";
    		longDetails += passenger.getId() + " - ";
    		longDetails += passenger.getName() + " - ";
    		longDetails += passenger.getPhone();
    	}
    	longDetails += "\n" + passengers.size() + " passenger(s)";
    	return longDetails;
    }
    
    /**
     * Adds a passenger to the flight
     * 
     * @throws FlightBookingSystemException thrown when there is an error
     */
    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
    	// TODO: implementation here   
    	if (passengers.contains(passenger)) {
    		throw new FlightBookingSystemException("This passenger is already on this flight.");
    	} else {
    		passengers.add(passenger);
    	}
    }
    
    /**
     * Removes a passenger from a flight
     * 
     * @throws FlightBookingSystemException thrown when there is an error
     */
    public void removePassenger(Customer passenger) throws FlightBookingSystemException {
    	// TODO: implementation here       	
    	if (passengers.contains(passenger)) {
    		passengers.remove(passenger);
    	} else {
    		throw new FlightBookingSystemException("This passenger is not on this flight.");
    	}
    }
}
