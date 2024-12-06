package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings = new ArrayList<>();
    // not sure if we need to edit the above line of code to this: private final List<Booking> bookings;
    
    // TODO: implement constructor here
    
    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        
        // not sure if we need to add this: bookings = new ArrayList<>();
    }
    
    // TODO: implementation of Getter and Setter methods
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
    
    public String getDetailsShort() {
        return "Customer #" + id + " - " + name + " - " + phone;
    }
    
    public String getDetailsLong() {
        return "Customer #" + id + "/n Name: " + name + "/n Phone: " + phone + "/n ---------------" + "/n Bookings: "; // to continue
    }
    
    public void addBooking(Booking booking) throws FlightBookingSystemException {
        // TODO: implementation here
    	
    }
    
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
        // TODO: implementation here
    }
}
