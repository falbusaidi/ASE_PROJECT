package Application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PassengerThread extends Thread{
	private Thread t;
	public List keys;
	
	@Override
	public void run() {

		try 
		{
			FlightList flightlist = new FlightList();
			BookingList bookinglist = new BookingList(flightlist);
		
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");

			List keys = new ArrayList(bookinglist.getBookingslist().keySet());
			Collections.shuffle(keys);

//			System.out.println(keys);
		}

		catch ( FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}

	}
	
	public void start () {
	      if (t == null) {
	         t = new Thread (this);
	         t.start ();
	         
	      } 
	}
	
	public List getKey() {

		return keys;
	}
}
