
package Main;

import java.io.FileNotFoundException;

import Controller.Controller;
import Model.BookingList;
import Model.CheckInQueue;
import Model.DeskManager;
import Model.FlightList;
import Model.PassengerThread;
import View.AdminGUI;
import View.KioskGUI;
import View.SetSimulationTime;
import Model.DeskTimer;

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
			DeskManager deskmanager = new DeskManager(passengerQueue,flightlist, 4,3); 
			
			// load the flight and booking information
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");
			
			//start the passenger queue 
			
			long closeDeskTime = 120*1000;
			
			KioskGUI gui = new KioskGUI(passengerQueue,flightlist,deskmanager); 
			AdminGUI adminGUI = new AdminGUI(deskmanager,passengerThread); 
			Controller controller = new Controller(adminGUI,deskmanager,passengerThread);
			new Thread(passengerThread).start();
			DeskTimer deskstart= new DeskTimer(deskmanager, closeDeskTime);
	
			
		}
		catch ( FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}