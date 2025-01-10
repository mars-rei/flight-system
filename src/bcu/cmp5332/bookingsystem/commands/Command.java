package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents a command that will be used to access a feature in the system
 */
public interface Command {
    public static final String HELP_MESSAGE = "Commands:\n"
        + "\tlistflights                               print all flights\n"
        + "\tlistcustomers                             print all customers\n"
        + "\tlistbookings                              print all bookings\n" // added
        + "\taddflight                                 add a new flight\n"
        + "\taddcustomer                               add a new customer\n"
        + "\tshowflight [flight id]                    show flight details\n"
        + "\tshowcustomer [customer id]                show customer details\n"
        + "\tshowbooking [booking id]                  show booking details\n"
        + "\tremoveflight [flight id]                  remove flight\n" // added
        + "\tremovecustomer [customer id]              remove customer \n" // added
        + "\taddbooking [customer id] [flight id]      add a new booking\n"
        + "\tcancelbooking [customer id] [flight id]   cancel a booking\n"
        + "\teditbooking [booking id] [flight id]      update a booking\n" 
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
    
    default void rollback(FlightBookingSystem flightBookingSystem, int customerOrFlight) {
    	// default is empty as this only affects the removecustomer and removeflight commands
    }
    
    default void rollback(FlightBookingSystem flightBookingSystem, Booking booking) {
    	// default is empty as this only affects the cancelbooking command
    }
    
    default void rollback(FlightBookingSystem flightBookingSystem, int booking, int flight, Flight originalFlight) {
    	// default is empty as this only affects the editbooking commands
    }
    
}
