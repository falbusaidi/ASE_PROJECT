package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Application.Booking;
import Application.BookingList;

public class CheckInQueue {
	
	public void InQueue() {		
		try 
		{
			FlightList flightlist = new FlightList();
			BookingList bookinglist = new BookingList(flightlist);
		
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");
			LinkedList<Booking> CheckInQueue = new LinkedList<Booking>();
			
			List keys = new ArrayList(bookinglist.getBookingslist().keySet());
			Collections.shuffle(keys);
			
			
			for (int i = 0; i < keys.size(); i++)
			{
				Booking booking = bookinglist.getBookingslist().get(keys.get(i));
				CheckInQueue.add(booking);

			}


		}

		catch ( FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}


		
		
	}
}
