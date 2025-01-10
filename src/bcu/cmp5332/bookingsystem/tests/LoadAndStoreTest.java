package bcu.cmp5332.bookingsystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.data.BookingDataManager;
import bcu.cmp5332.bookingsystem.data.CustomerDataManager;
import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.data.FlightDataManager;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

class LoadAndStoreTest {
	
	private FlightBookingSystem fbs;

	@Test 
	void checkEverythingSaved() throws FlightBookingSystemException, IOException { 
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(8, "Safica Khan", "07952858582","sk@bcu.ac.uk",false);
		Flight newFlight=new Flight(5,"BA99","London", "Paris", LocalDate.parse("2024-12-25"), 60, 150.0, false);
		fbs.addCustomer(newCustomer);
		fbs.addFlight(newFlight);
		AddBooking newBooking = new AddBooking(newCustomer.getId(), newFlight.getId(), LocalDate.now());
		newBooking.execute(fbs);
		
		BookingDataManager bdm = new BookingDataManager();
		bdm.storeData(fbs);
        Scanner bdmsc = new Scanner(new File("./resources/data/bookings.txt"));
        int bdmline_idx = 1;
        String bookingText = "";
        while (bdmsc.hasNextLine()) {
        	String line = bdmsc.nextLine();
        	bookingText += line;
        	bdmline_idx++;
        }
		assertEquals(("1::8::5::" + LocalDate.now() + "::"), bookingText);
        
        
		FlightDataManager fdm = new FlightDataManager();
		fdm.storeData(fbs);
        Scanner fdmsc = new Scanner(new File("./resources/data/flights.txt"));
        int fdmline_idx = 1;
        String flightText = "";
        while (fdmsc.hasNextLine()) {
        	String line = fdmsc.nextLine();
        	flightText += line;
        	fdmline_idx++;
        }
		assertEquals(("5::BA99::London::Paris::2024-12-25::60::150.0::false::"), flightText);
		
		CustomerDataManager cdm = new CustomerDataManager();
		cdm.storeData(fbs);
        Scanner cdmsc = new Scanner(new File("./resources/data/customers.txt"));
        int cdmline_idx = 1;
        String customerText = "";
        while (cdmsc.hasNextLine()) {
        	String line = cdmsc.nextLine();
        	customerText += line;
        	cdmline_idx++;
        }
		assertEquals(("8::Safica Khan::07952858582::sk@bcu.ac.uk::false::"), customerText);
	}
	
	@Test 
	void checkEverythingLoaded() throws FlightBookingSystemException, IOException {
		fbs = new FlightBookingSystem();
		Customer newCustomer = new Customer(8, "Safica Khan", "07952858582","sk@bcu.ac.uk",false);
		Flight newFlight=new Flight(5,"BA99","London", "Paris", LocalDate.parse("2024-12-25"), 60, 150.0, false);
		fbs.addCustomer(newCustomer);
		fbs.addFlight(newFlight);
		AddBooking newBooking = new AddBooking(newCustomer.getId(), newFlight.getId(), LocalDate.now());
		newBooking.execute(fbs);
		
		FlightBookingSystemData.store(fbs);
		FlightBookingSystemData.load();
		
		assertEquals(1, fbs.getBookings().size());
		assertEquals(newCustomer, fbs.getBookings().get(0).getCustomer());
		assertEquals(newFlight, fbs.getBookings().get(0).getFlight());
		
		assertEquals(1, fbs.getCustomers().size());
		assertTrue(fbs.getCustomers().contains(newCustomer));
		assertTrue(newCustomer.getBookings().contains(fbs.getBookings().get(0)));
		
		assertEquals(1, fbs.getFlights().size());
		assertTrue(fbs.getFlights().contains(newFlight));
		assertTrue(newFlight.getPassengers().contains(newCustomer));
	}
}
