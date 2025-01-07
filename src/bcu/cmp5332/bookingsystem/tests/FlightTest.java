package bcu.cmp5332.bookingsystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class FlightTest {

	private Flight flight;
	private Customer customer;
	private FlightBookingSystem fbs;

	@BeforeEach
	void setUp() {
		flight = new Flight(1, "LH2560", "Birmingham", "Munich", LocalDate.parse("2024-11-11"), 100, 50.0);
		customer = new Customer(1, "John Doe","07555555555", "johndoe@example.com");
		fbs = new FlightBookingSystem();
	}
	
	@Test
	void flightCreation() {
		Flight flight= new Flight(1, "LH2560", "Birmingham", "Munich", LocalDate.parse("2022-11-25"), 25, 40.99);
		assertEquals(25, flight.getCapacity());
		assertEquals(40.99, flight.getPrice());
	}

	@Test
	void addPassenger() throws FlightBookingSystemException{
		flight.addPassenger(customer); 
		assertTrue(flight.getPassengers().contains(customer)); 
	}

	@Test
	void removePassenger()throws FlightBookingSystemException {
		flight.addPassenger(customer);
		flight.removePassenger(customer);
		assertFalse(flight.getPassengers().contains(customer));
	}

	/*@Test
	void testRemovePassengerNotAddedYet() {
		Exception exception = assertThrows(FlightBookingSystemException.class, () -> {
			flight.removePassenger(customer);
		});
		assertEquals("This passenger is not part of this flight", exception.getMessage());
	}*/

	@Test
	void getDetailsShort() {
		String expected = "Flight #1 - LH2560 - Birmingham to Munich on 11/11/2024";
		assertEquals(expected, flight.getDetailsShort());
	}

	@Test
	void getDetailsLong() throws FlightBookingSystemException {
		flight.addPassenger(customer);
		String expected = "Flight #1\nFlight No: LH2560\nOrigin: Birmingham\nDestination: Munich\nDeparture Date: 2024-11-11\nNumber of seats: 100\nPrice: £50.0\n---------------\nPassengers:\n * Id: 1 - John Doe - 07555555555\n1 passenger(s)";
		assertEquals(expected, flight.getDetailsLong());


	}

}
