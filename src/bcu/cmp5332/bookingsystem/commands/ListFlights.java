package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * Represents the listFlights command
 * 
 * Implements the interface Command
 */
public class ListFlights implements Command {

    /**
     * Executes the listFlights command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> allFlights = flightBookingSystem.getFlights();
        
        // getting only valid flights (are not deleted and have not yet departed)
        int notHiddenFlights = 0;
        
        for (Flight flight : allFlights) {
        	if (flight.getIsDeleted() == false
        			&& flight.getDepartureDate().isAfter(flightBookingSystem.getSystemDate())) {
        		System.out.println(flight.getDetailsShort());
        		++notHiddenFlights;
        	} 
        }
        
        System.out.println(notHiddenFlights + " flight(s)");
    }
}
