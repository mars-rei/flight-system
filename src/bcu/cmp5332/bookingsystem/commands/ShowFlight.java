// added - to complete

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the showFlight command
 * 
 * implements the interface Command
 */
public class ShowFlight implements Command {
	
	private int id;

    /**
     * Represents the showFlight command constructor
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
        // TODO: implementation here
    	Flight flight = flightBookingSystem.getFlightByID(id);
    	System.out.println(flight.getDetailsLong()); // need to implement this method in the model customer class still
    }
}
