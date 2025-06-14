package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Represents a customer data manager 
 * 
 * Implements the interface DataManager
 */
public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
    /**
	 * Loads customer data from file storage to flight booking system
	 */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String name = properties[1];
                    String phone = properties[2];
                    String email = properties[3];
                    boolean isDeleted = Boolean.parseBoolean(properties[4]);
                    Customer customer = new Customer(id, name, phone, email, isDeleted);
                    fbs.addCustomer(customer);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse customer id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    /**
	 * Stores customer data from flight booking system to file storage
	 */
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
    	
    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
    		for (Customer customer : fbs.getCustomers()) {
    			out.print(customer.getId() + SEPARATOR);
    			out.print(customer.getName() + SEPARATOR);
    			out.print(customer.getPhone() + SEPARATOR);
    			out.print(customer.getEmail() + SEPARATOR);
    			out.print(customer.getIsDeleted() + SEPARATOR);
    			out.println();
    		}
    	} 
    }
}
