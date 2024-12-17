// completed

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
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
                System.out.println("booking stored in file: " + line); //testing
                String[] properties = line.split(SEPARATOR, -1);
                try {
                	int customerId = Integer.parseInt(properties[0]);
                	int flightId = Integer.parseInt(properties[1]);
                    LocalDate bookingDate = LocalDate.parse(properties[2]);
                    Booking booking = new Booking(fbs.getCustomerByID(customerId), fbs.getFlightByID(flightId), bookingDate);
                    AddBooking initialiseBooking = new AddBooking(customerId, flightId);
                    initialiseBooking.execute(fbs); // make sure booking is added to customer and passenger
                    
                    // to correct the date might need to use edit booking
                    booking.setBookingDate(bookingDate); // makes sure to keep the booking date correct
                    System.out.println(booking.getBookingDate()); // seems to work when printing but when showing customer it's incorrect
                    
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse id on line " + line_idx
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
                out.print(booking.getCustomer().getId() + SEPARATOR);
                out.print(booking.getFlight().getId() + SEPARATOR);
                out.print(booking.getBookingDate() + SEPARATOR);
                out.println();
            }
        }
    }
    
}
