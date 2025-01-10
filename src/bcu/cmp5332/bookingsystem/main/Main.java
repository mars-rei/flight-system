package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;

/**
 * Main class of the flight booking system application
 */
public class Main {

	/**
	 * Represents the main class constructor
	 * 	 
	 * @throws IOException thrown when there is an input / output error
	 * 
	 * @throws FlightBookingSystemException thrown when there is an error
	 */
	public static void main(String[] args) throws IOException, FlightBookingSystemException {

		FlightBookingSystem fbs = FlightBookingSystemData.load();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Flight Booking System");
		System.out.println("Enter 'help' to see a list of available commands.");
		while (true) {
			System.out.print("> ");
			String line = br.readLine();
			if (line.equals("exit")) {
				break;
			}

			boolean rollback = false;
			Command command = null;
			String[] parts = line.split(" ", 3);
            String cmd = parts[0];
            String[] extraInfo;
            
            // variables to use to reset for rollback if needed
            Booking originalBooking = null;
            Flight originalFlight = null;

			try {
				command = CommandParser.parse(line);
				
				if (cmd.equals("cancelbooking")) { // for rollback
					Customer customer = fbs.getCustomerByID(Integer.parseInt(parts[1]));
					Flight flight = fbs.getFlightByID(Integer.parseInt(parts[2]));
					
					for (Booking booking: customer.getBookings()) {
						if (booking.getFlight() == flight) {
							originalBooking = booking;
						}
					}
					
				} else if (cmd.equals("editbooking")) { // for rollback
					originalFlight = fbs.getBookingByID(Integer.parseInt(parts[1])).getFlight();
				}
				
				command.execute(fbs);
				
				FlightBookingSystemData.store(fbs);

			} catch (FlightBookingSystemException ex) {
				System.out.println(ex.getMessage());
				
			} catch (IOException ex) {
				// rollback code
				rollback = true;

			} 
						
			if (rollback == true) {
				System.out.println();
				if (cmd.equals("removecustomer") || cmd.equals("removeflight")) {
					command.rollback(fbs, Integer.parseInt(parts[1]));
				
				} else if(cmd.equals("cancelbooking")) {
					command.rollback(fbs, originalBooking);
					
				} else if (cmd.equals("editbooking")){
					command.rollback(fbs, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), originalFlight);

				}
				
				else {
					command.rollback(fbs);
				}
			}
			
			System.out.println();
			
		}
		
		System.exit(0); 
	}
}
