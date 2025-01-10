package bcu.cmp5332.bookingsystem.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.data.BookingDataManager;
import bcu.cmp5332.bookingsystem.data.CustomerDataManager;
import bcu.cmp5332.bookingsystem.data.FlightDataManager;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class BookingTest {
	
	private FlightBookingSystem fbs;

	@Test
	void issueBooking() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(8, "Safica Khan", "07952858582","sk@bcu.ac.uk",false);
		Flight newFlight=new Flight(5,"BA99","London", "Paris", LocalDate.parse("2024-12-25"), 60, 150.0, false);
		fbs.addCustomer(newCustomer);
		fbs.addFlight(newFlight);
		
		//creates and add a booking
		AddBooking newBooking = new AddBooking(newCustomer.getId(), newFlight.getId(), LocalDate.now());
		newBooking.execute(fbs);
		
		// the new booking would be the first value in the treemap
		Booking booking = fbs.getBookings().get(0);
		
		
		//ensures the booking has been added to the fbs
		assertTrue(fbs.getBookings().contains(booking));
		
		//ensures customer has been added to the flight's passengers list
		assertTrue(newFlight.getPassengers().contains(newCustomer));
		
		//ensures booking has been added to the customer's list
		assertTrue(newCustomer.getBookings().contains(booking));
	}
	
	@Test 
	void cancelBooking() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(8, "Safica Khan", "07952858582","sk@bcu.ac.uk",false);
		Flight newFlight = new Flight(5,"BA99","London", "Paris", LocalDate.parse("2024-12-25"), 60, 150.0, false);
		
		fbs.addCustomer(newCustomer);
		fbs.addFlight(newFlight);
		
		Booking newBooking = new Booking(1, newCustomer, newFlight, LocalDate.now());
		fbs.addBooking(newBooking);
		fbs.cancelBooking(newCustomer, newFlight);

		// ensures booking has been cancelled
		assertFalse(newFlight.getPassengers().contains(newCustomer));
		assertFalse(newCustomer.getBookings().contains(newBooking));
		
		assertFalse(fbs.getBookings().contains(newBooking));		
	}
	
	@Test 
	void bookingNotFound() throws FlightBookingSystemException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(8, "Safica Khan", "07952858582","sk@bcu.ac.uk",false);
		Flight newFlight = new Flight(5,"BA99","London", "Paris", LocalDate.parse("2024-12-25"), 60, 150.0, false);
		fbs.addCustomer(newCustomer);
		fbs.addFlight(newFlight);

		FlightBookingSystemException exception = assertThrows(FlightBookingSystemException.class, () -> {
			newCustomer.cancelBookingForFlight(newFlight);
		});
		assertEquals("There is no booking in the list that contains this flight.", exception.getMessage());
	}
}
