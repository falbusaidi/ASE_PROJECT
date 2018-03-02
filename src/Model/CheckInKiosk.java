
package Model;

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
			// setup all the objects
			FlightList flightlist = new FlightList();
			BookingList bookinglist = new BookingList(flightlist);
			CheckInQueue passengerQueue = new CheckInQueue(); 
			PassengerThread passengerThread = new PassengerThread(bookinglist,passengerQueue); 
			DeskManager deskmanager = new DeskManager(passengerQueue, 3,3); 
			
			// load the flight and booking information
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");
			
			//star the passnger queue 
			Thread t = new Thread(passengerThread);
			t.start();
			deskmanager.OpenDesks();
			
			
			
			//GUIMainWindow gui = new GUIMainWindow(bookinglist);
			
			// gui.run(); 
			
		}
		catch ( FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
