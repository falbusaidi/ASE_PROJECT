
package Application;

import java.io.FileNotFoundException;

/**
 * 
 * main Class to launch the Check-in System Application
 *
 */
public class CheckInKiosk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FlightList flightlist = new FlightList();
			BookingList bookinglist = new BookingList(flightlist);
			GUIMainWindow gui = new GUIMainWindow(bookinglist);
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");
			gui.run(); 
			
		}
		catch ( FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
