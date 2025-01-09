package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

/**
 * Represents the listFlights command
 * 
 * implements the interface Command
 */
public class ListFlights implements Command {

    /**
     * Executes the listFlights command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> allFlights = flightBookingSystem.getFlights();
        int notHiddenFlights = 0;
        for (Flight flight : allFlights) {
        	if (flight.getIsDeleted() == false) {
        		System.out.println(flight.getDetailsShort());
        		++notHiddenFlights;
        	} 
        }
        System.out.println(notHiddenFlights + " flight(s)");
    }
}
