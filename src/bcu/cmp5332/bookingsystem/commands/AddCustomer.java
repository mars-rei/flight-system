package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the addCustomer command
 * 
 * Implements the interface Command
 */
public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;

    /**
     * Initialises the AddCustomer object
     * 
     * @param name the customer's name (string)
     * 
     * @param phone the customer's phone number (string)
     * 
     * @param email the customer's email address (string)
     */
    public AddCustomer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    /**
     * Executes the addCustomer command
     */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
    	int maxId = 0;
        if (flightBookingSystem.getCustomers().size() > 0) {
            int lastIndex = flightBookingSystem.getCustomers().size() - 1;
            maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        
        Customer customer = new Customer(++maxId, name, phone, email, false);
        flightBookingSystem.addCustomer(customer);
    	System.out.println("Customer #" + customer.getId() + " added.");
    }
    
    /**
     * Restores previous state when there is an error storing data
     */
    @Override
    public void rollback(FlightBookingSystem flightBookingSystem) {  
    	System.out.println("Error storing new customer data.");
    	
    	int lastIndex = flightBookingSystem.getCustomers().size() - 1;
        int newestId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        
        Customer customer;
		try {
			customer = flightBookingSystem.getCustomerByID(newestId);
			flightBookingSystem.removeCustomer(customer);
			System.out.println("Customer #" + newestId + " addition withdrawn.");
		} catch (FlightBookingSystemException e) {
			System.out.println(e.getMessage());
		}
    }

}
