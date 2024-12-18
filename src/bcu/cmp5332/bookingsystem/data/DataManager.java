// completed

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;

/**
 * Represents a data manager that will be used to load data to the booking system 
 * from file storage and store data from the booking system to file storage
 */
public interface DataManager {
    
    public static final String SEPARATOR = "::";
    
    /**
	 * Loads data from file storage to flight booking system
	 * 
	 * @param fbs a FlightBookingSystem object
	 * 
	 * @throws IOException thrown when there is an input / output error
	 * 
	 * @throws FlightBookingSystemException thrown when there is an error
	 */
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    
    /**
	 * Stores data from flight booking system to file storage
	 * 	
	 * @param fbs a FlightBookingSystem object
	 * 
	 * @throws IOException thrown when there is an input / output error
	 */
    public void storeData(FlightBookingSystem fbs) throws IOException;
    
}
