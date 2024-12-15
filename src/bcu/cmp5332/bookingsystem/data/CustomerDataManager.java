// to complete - reference data manager

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.IOException;

/**
 * Represents a customer data manager 
 * 
 * implements the interface DataManager
 */
public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
    /**
	 * Loads customer data from file storage to flight booking system
	 */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    }

    /**
	 * Stores customer data from flight booking system to file storage
	 */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
    }
    
}
