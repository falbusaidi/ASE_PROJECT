package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import Model.BookingList;

public class PassengerThread extends Thread{
	private Thread t;
	public List keys;
	FlightList flightlist = new FlightList();
	BookingList bookinglist = new BookingList(flightlist);
	int FIVE_SECOND = 5000; 
	public void run() {
		try {
			Thread.sleep(FIVE_SECOND);
		} catch (InterruptedException e) {

			System.out.println("Thread interrupted.");
		}

	}
	
	public Booking getBooking(Object key) {
		Booking booking = bookinglist.Getbookingslist().get(key);
		return booking;
	}
	
}
