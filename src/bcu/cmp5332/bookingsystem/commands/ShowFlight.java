package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the showFlight command
 * 
 * Implements the interface Command
 */
public class ShowFlight implements Command {
	
	private int id;

    /**
     * Initialises the ShowFlight object
     * 
     * @param id the flight's id (int)
     */
    public ShowFlight(int id) {
    	this.id = id;
    }

    /**
     * Executes the showFlight command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	Flight flight = flightBookingSystem.getFlightByID(id);
    	System.out.println(flight.getDetailsLong()); 
    	if (flight.getIsDeleted() == true) {
    		System.out.println("This flight has been deleted.");
    	}
    }
}
