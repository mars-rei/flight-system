package bcu.cmp5332.bookingsystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class FlightTest {
	
	private FlightBookingSystem fbs;
	
	@Test
	void addFlight() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Flight newFlight = new Flight(7, "UX100","Madrid", "Rome", LocalDate.parse("2025-01-15"),150, 120.0, false);
		fbs.addFlight(newFlight);
				
		// ensures flight capacity is correct
		assertEquals(150, newFlight.getCapacity());
		
		// ensures flight price is correct
		assertEquals(120.0, newFlight.getPrice());

		// ensuring the getDetailsShort() returns the correct details
		assertEquals("Flight #7 - UX100 - Madrid to Rome on 15/01/2025", newFlight.getDetailsShort());

		// ensures the getDetailsLong() returns the correct details
		String expected = "Flight #7\nFlight No: UX100\nOrigin: Madrid\nDestination: Rome\nDeparture Date: 2025-01-15\nNumber of seats: 150\nPrice: £120.0"
				+ "\n---------------\nPassengers:\n0 passenger(s)";

		assertEquals(expected, newFlight.getDetailsLong());
	}
	
	@Test
	void addFlightDuplicateId() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Flight newFlight = new Flight(7, "UX100","Madrid", "Rome", LocalDate.parse("2025-01-15"),150, 120.0, false);
		fbs.addFlight(newFlight);
		
		Flight duplicateId = new Flight(7, "JAL42","London", "Tokyo", LocalDate.parse("2024-12-17"), 60, 200.99, false);
		
		Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
			fbs.addFlight(duplicateId);
		});
		assertEquals("Duplicate flight ID.", exception.getMessage());
	}
	
	@Test
	void addDuplicateFlight() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Flight newFlight = new Flight(7, "UX100","Madrid", "Rome", LocalDate.parse("2025-01-15"), 150, 120.0, false);
		fbs.addFlight(newFlight);
		
		Flight duplicateFlight = new Flight(6, "UX100","Madrid", "Rome", LocalDate.parse("2025-01-15"), 150, 120.0, false);
		
		Exception exception = assertThrows(FlightBookingSystemException.class, ()-> {
			fbs.addFlight(duplicateFlight);
		});
		assertEquals("There is a flight with same number and departure date in the system", exception.getMessage());
	}
	
	
	@Test
	void listAllFlights() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Flight newFlight = new Flight(7, "UX100","Madrid", "Rome", LocalDate.parse("2025-01-15"), 150, 120.0, false);
		fbs.addFlight(newFlight);
		
		Flight newerFlight = new Flight(2, "JAL42","London", "Tokyo", LocalDate.parse("2024-12-17"), 60, 200.99, false);
		fbs.addFlight(newerFlight);
		
		// unsure on how to test text output so taking this route of testing
		// using the list flights code
		List<Flight> flights = fbs.getFlights();
		String listFlights = "";

		for (Flight flight: flights) {
			if (flight.getIsDeleted() == false) {
				listFlights += flight.getDetailsShort() + "/n"; 
			}
		}
		        
		String expected = "Flight #2 - JAL42 - London to Tokyo on 17/12/2024/nFlight #7 - UX100 - Madrid to Rome on 15/01/2025/n";
		
		assertEquals(listFlights, expected);
		
		//make sure flights are listed
		assertTrue(flights.size() > 0);

		//make sure the new flights are in the list
		assertTrue(flights.contains(newFlight));
		assertTrue(flights.contains(newerFlight));
	}

}
