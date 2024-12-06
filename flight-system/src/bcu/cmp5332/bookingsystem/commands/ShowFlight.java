// added - to complete

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowFlight implements Command {
	
	private int id;

    public ShowFlight(int id) {
    	this.id = id;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        // TODO: implementation here
    	Flight flight = flightBookingSystem.getFlightByID(id);
    	flight.getDetailsLong(); // need to implement this method in the model customer class still
    }
}
