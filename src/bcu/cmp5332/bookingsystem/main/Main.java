// UNFINISHED

package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;

/**
 * Main class of the flight booking system application
 */
public class Main {

	/**
	 * Represents the main class constructor
	 * 
	 * @param args an array of strings
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

			// tryna figure this out
			boolean rollback = false;
			Command command = null;
			String[] parts = line.split(" ", 3);
            String cmd = parts[0];

			try {
				command = CommandParser.parse(line);
				command.execute(fbs);
				
				FlightBookingSystemData.store(fbs);

			} catch (FlightBookingSystemException ex) {
				System.out.println(ex.getMessage());
				
			} catch (IOException ex) {
				// rollback code
				rollback = true;

			} 
			
			if (rollback == true) {
				if (cmd == "cancelbooking" || cmd == "editbooking") {
					command.rollback(fbs, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
				} else {
					command.rollback(fbs);
				}
			}
			
		}
		FlightBookingSystemData.store(fbs);
		System.exit(0); 
	}
}
