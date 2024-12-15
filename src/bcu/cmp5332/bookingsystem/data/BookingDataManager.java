// to complete - reference data manager

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents a booking data manager 
 * 
 * implements the interface DataManager
 */

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    /**
	 * Loads booking data from file storage to flight booking system
	 */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                	int customerId = Integer.parseInt(properties[0]);
                	int flightId = Integer.parseInt(properties[1]);
                    LocalDate bookingDate = LocalDate.parse(properties[2]);
                    Booking booking = new Booking(fbs.getCustomerByID(customerId), fbs.getFlightByID(flightId), bookingDate);
                    fbs.addBooking(booking);
                	// addBookings() not specified in the specification 
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    /**
	 * Stores booking data from flight booking system to file storage
	 */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Booking booking : fbs.getBookings()) { 
            	// getBookings() not specified in the specification
                out.print(booking.getCustomer() + SEPARATOR);
                out.print(booking.getFlight() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR);
                out.println();
            }
        }
    }
    
}
