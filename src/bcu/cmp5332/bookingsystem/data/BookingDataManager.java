// to complete - reference data manager

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

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
    }

    /**
	 * Stores booking data from flight booking system to file storage
	 */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    }
    
}
