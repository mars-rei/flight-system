// given -  completed

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;

/**
 * Represents the addFLight command
 * 
 * implements the interface Command
 */
public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;

    
    /**
     * Represents the addFlight command constructor
     * 
     * @param flightNumber flight number (string)
     * 
     * @param origin the airport the flight is coming from (string)
     * 
     * @param destination the airport the flight will land at (string)
     * 
     * @param departureDate the date of flight departure (local date)
     */
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
    }
    
    /**
     * Executes the addFlight command
     * 
     * @param flightBookingSystem FlightBookingSystem object
     * 
     * {@inheritDoc}
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}
