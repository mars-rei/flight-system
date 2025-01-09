package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.time.LocalDate;

/**
 * Represents the addFlight command
 * 
 * Implements the interface Command
 */
public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int capacity;
    private final double price;
    
    /**
     * Initialises the AddFlight object
     * 
     * @param flightNumber the flight's number (string)
     * 
     * @param origin the airport the flight is coming from (string)
     * 
     * @param destination the airport the flight will land at (string)
     * 
     * @param departureDate the date of flight departure (local date)
     * 
     * @param capacity the number of seats on the flight (int)
     * 
     * @param price the price of the flight (double)
     */
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate,
    		int capacity, double price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
    }
    
    /**
     * Executes the addFlight command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, capacity, price, false);
        flightBookingSystem.addFlight(flight);
    	System.out.println("Flight #" + flight.getId() + " added.");
   
    }
    
    /*
    @Override
    public void rollback(FlightBookingSystem flightBookingSystem) {  
    	System.out.println("Error storing new flight data.");
    	
    	int lastIndex = flightBookingSystem.getFlights().size() - 1;
        int newestId = flightBookingSystem.getFlights().get(lastIndex).getId();
        
        Flight flight;
		try {
			flight = flightBookingSystem.getFlightByID(newestId);
			flightBookingSystem.removeFlight(flight);
			System.out.println("Flight #" + newestId + " addition withdrawn.");
		} catch (FlightBookingSystemException e) {
			System.out.println(e.getMessage());
		}
    }
    */
}
