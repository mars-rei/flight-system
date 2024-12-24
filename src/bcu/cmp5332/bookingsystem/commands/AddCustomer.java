// completed

package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * Represents the addCustomer command
 * 
 * implements the interface Command
 */
public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;

    /**
     * Represents the addCustomer command constructor
     * 
     * @param name customer name (string)
     * 
     * @param phone customer phone number (string)
     * 
     * @param email customer email address (string)
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
        // TODO: implementation here
    	int maxId = 0;
        if (flightBookingSystem.getCustomers().size() > 0) {
            int lastIndex = flightBookingSystem.getCustomers().size() - 1;
            maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        
        Customer customer = new Customer(++maxId, name, phone, email);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer #" + customer.getId() + " added.");
    }
}
