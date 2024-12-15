// given - completed

package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads data to and stores data from the flight booking system using text file storage
 */
public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
        // dataManagers.add(new CustomerDataManager());
        // dataManagers.add(new BookingDataManager());
    }
    
    /**
   	 * Loads customer data from text file storage to flight booking system
   	 * 
   	 * @param fbs a FlightBookingSystem object
	 * 
	 * @throws IOException thrown when there is an input / output error
	 * 
	 * @throws FlightBookingSystemException thrown when there is an error
   	 */
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }

    /**
	 * Stores customer data from flight booking system to text file storage
	 *
	 * @param fbs a FlightBookingSystem object
	 * 
	 * @throws IOException thrown when there is an input / output error
	 */
    public static void store(FlightBookingSystem fbs) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
}
