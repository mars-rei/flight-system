// still needs to be fully completed

package bcu.cmp5332.bookingsystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class CustomerTest {

	private Customer customer;
	private Booking booking;
	private Flight flight;
	private FlightBookingSystem fbs;
	
	@BeforeEach 
	void setUp() {
		customer = new Customer(1, "John Doe", "07555555555", "johndoe@example.com");
		flight = new Flight(1, "LH2560", "Birmingham", "Munich", LocalDate.parse("2024-11-11"), 100, 50.0);
		booking = new Booking(customer, flight, LocalDate.now());
		fbs = new FlightBookingSystem();
	}

	@Test
	void testAddBooking() throws FlightBookingSystemException {
		customer.addBooking(booking);
		assertTrue(customer.getBookings().contains(booking));
	}

	@Test
	void testCancelBooking() throws FlightBookingSystemException{
		customer.addBooking(booking);
		customer.cancelBookingForFlight(flight);
		assertFalse(customer.getBookings().contains(booking)); 
	}

	@Test // 
	void testAddDuplicateBooking() throws FlightBookingSystemException {
		Booking booking = new Booking(customer, flight, LocalDate.now());
		customer.addBooking(booking);
		
		Exception exception = assertThrows(FlightBookingSystemException.class, () -> {
			customer.addBooking(booking);
		});
		assertEquals("There is a booking with same customer and flight in the system", exception.getMessage());
	}
	
	@Test // 
	void testCancelBookingNotFound() {
		FlightBookingSystemException exception = assertThrows(FlightBookingSystemException.class, () -> {
			customer.cancelBookingForFlight(flight);
		});

		assertEquals("There is no booking in the list that contains this flight.", exception.getMessage());
	}

	@Test // 
	void testGetDetailsShort() {
		String expected = "Customer #1 - John Doe - 07555555555 - johndoe@example.com";
		assertEquals(expected, customer.getDetailsShort());
	}

	@Test // 
	void testGetDetailsLong() throws FlightBookingSystemException {
		customer.addBooking(booking);
		String expected = "Customer #1\nName: John Doe\nPhone: 07555555555\nEmail: johndoe@example.com\n---------------\nBookings:\n Booking date: " 
				+ LocalDate.now() + " for Flight #1 - LH2560 - Birmingham to Munich on 2024-11-11\n1 booking(s)";
		assertEquals(expected, customer.getDetailsLong());
	}
}

