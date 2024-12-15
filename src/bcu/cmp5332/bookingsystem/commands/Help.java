// given - complete

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the help command
 * 
 * implements the interface Command
 */
public class Help implements Command {

    /**
     * Executes the help command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
