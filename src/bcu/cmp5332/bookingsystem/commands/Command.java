// given - completed

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents a command that will be used to access a feature in the system
 */
public interface Command {
    public static final String HELP_MESSAGE = "Commands:\n"
        + "\tlistflights                               print all flights\n"
        + "\tlistcustomers                             print all customers\n"
        + "\taddflight                                 add a new flight\n"
        + "\taddcustomer                               add a new customer\n"
        + "\tshowflight [flight id]                    show flight details\n"
        + "\tshowcustomer [customer id]                show customer details\n"
        + "\taddbooking [customer id] [flight id]      add a new booking\n"
        + "\tcancelbooking [customer id] [flight id]   cancel a booking\n"
        + "\teditbooking [booking id] [flight id]     update a booking\n" // is it not meant to be customer id?
        + "\tloadgui                                   loads the GUI version of the app\n"
        + "\thelp                                      prints this help message\n"
        + "\texit                                      exits the program";

    /**
	 * Executes command specified by user on the FlightBookingSystem object flightBooking System
	 * 
	 * @param flightBookingSystem a FlightBookingSystem object
	 * 
	 * @throws FlightBookingSystemException thrown when there is an invalid command
	 */
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;
    
    default void rollback(FlightBookingSystem flightBookingSystem) {
    	// default is empty as this only affects commands that update the state of the system
    }
    
}
