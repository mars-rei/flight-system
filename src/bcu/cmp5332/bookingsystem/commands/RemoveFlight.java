package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the removeFlight command
 * 
 * Implements the interface Command
 */
public class RemoveFlight implements Command {
	
	private int id;

    /**
     * Initialises the RemoveFlight object
     * 
     * @param id the flight's id (int)
     */
    public RemoveFlight(int id) {
    	this.id = id;
    }

    /**
     * Executes the removeFlight command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	Flight flight = flightBookingSystem.getFlightByID(id);
    	flightBookingSystem.removeFlight(flight);
    	System.out.println("Flight #" + id + " removed.");
    }
}
