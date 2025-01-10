package bcu.cmp5332.bookingsystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class CustomerTest {

	private FlightBookingSystem fbs;
	
	@Test
	void addCustomer() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(6, "Seehem Ahmed", "07539829549","sa@bcu.ac.uk", false);
		fbs.addCustomer(newCustomer);
		
		// ensuring the ID is Correct
		assertEquals(6, newCustomer.getId());
		
		// ensuring name is correct
		assertEquals("Seehem Ahmed", newCustomer.getName());
		
		// ensuring phone number is correct
		assertEquals("07539829549", newCustomer.getPhone());
		
		// ensuring email is correct
		assertEquals("sa@bcu.ac.uk", newCustomer.getEmail());
		
		// ensuring isDeleted is false
		assertFalse(newCustomer.getIsDeleted());
		
		// ensuring the booking list is empty
		assertEquals(0, newCustomer.getBookings().size());
		
		// ensuring the customer has been added to the customers map in the fbs
		assertTrue(fbs.getCustomers().contains(newCustomer));
		
		// ensuring the getDetailsShort() returns the correct details
		assertEquals("Customer #6 - Seehem Ahmed - 07539829549 - sa@bcu.ac.uk", newCustomer.getDetailsShort());
		
		// ensures the getDetailsLong() returns the correct details
		String expected = "Customer #6\nName: Seehem Ahmed\nPhone: 07539829549\nEmail: sa@bcu.ac.uk\n---------------\nBookings:\n0 booking(s)";
		
		assertEquals(expected, newCustomer.getDetailsLong());
	}
	
	@Test
	void addCustomerDuplicateId() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(6, "Seehem Ahmed", "07539829549","sa@bcu.ac.uk", false);
		fbs.addCustomer(newCustomer);
		
		Customer duplicateId = new Customer(6, "Haddie Mae", "07850129565","hm@bcu.ac.uk", false);
		
		Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
			fbs.addCustomer(duplicateId);
		});
		assertEquals("Duplicate customer ID.", exception.getMessage());
	}
	
	@Test
	void addCustomerDuplicateEmail() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(6, "Seehem Ahmed", "07539829549", "sa@bcu.ac.uk", false);
		fbs.addCustomer(newCustomer);
		
		Customer duplicateEmail = new Customer(7, "Haddie Mae", "07850129565", "sa@bcu.ac.uk", false);
		
		Exception exception = assertThrows(FlightBookingSystemException.class, ()-> {
			fbs.addCustomer(duplicateEmail);
		});
		assertEquals("There is a customer with the same email in the system", exception.getMessage());
	}
	
	@Test
	void listAllCustomers() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(6, "Seehem Ahmed", "07539829549","sa@bcu.ac.uk", false);
		fbs.addCustomer(newCustomer);
		
		Customer newerCustomer = new Customer(7, "Haddie Mae", "07850129565","hm@bcu.ac.uk", false);
		fbs.addCustomer(newerCustomer);
		
		
		// unsure on how to test text output so taking this route of testing
		// using the list customers code
		List<Customer> customers = fbs.getCustomers();
		String listCustomers = "";

		for (Customer customer : customers) {
			if (customer.getIsDeleted() == false) {
				listCustomers += customer.getDetailsShort() + "/n"; 
			}
		}
		
		String expected = "Customer #" + newCustomer.getId() + " - " + newCustomer.getName() + " - " + newCustomer.getPhone() + " - " + newCustomer.getEmail() + 
				"/nCustomer #" + newerCustomer.getId() + " - " + newerCustomer.getName() + " - " + newerCustomer.getPhone() + " - " + newerCustomer.getEmail()
				+ "/n";
		
		assertEquals(listCustomers, expected);
		
		//make sure customers are listed
		assertTrue(customers.size() > 0);

		//make sure the new customers are in the list
		assertTrue(customers.contains(newCustomer));
		assertTrue(customers.contains(newerCustomer));
	}
}
	



