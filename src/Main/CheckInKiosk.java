
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
import Model.DeskTimer;

/**
 * 
 * main Class to launch the Check-in System Application
 *
 */
public class CheckInKiosk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			// setup all the objects
			FlightList flightlist = new FlightList();
			BookingList bookinglist = new BookingList(flightlist);
			CheckInQueue passengerQueue = new CheckInQueue(); 
			PassengerThread passengerThread = new PassengerThread(bookinglist,passengerQueue); 
			DeskManager deskmanager = new DeskManager(passengerQueue,flightlist, 4,3); 
			
			// load the flight and booking information
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");
			
			
			// define the timer to close the check-in desks
			long closeDeskTime = 120*1000;
			
			// create and launch the main Simulation GUI class
			KioskGUI gui = new KioskGUI(passengerQueue,flightlist,deskmanager); 
			
			// create the AdminGUI 
			AdminGUI adminGUI = new AdminGUI(deskmanager,passengerThread); 
			
			// Create the Controller class
			Controller controller = new Controller(adminGUI,deskmanager,passengerThread);
			
			//start the passenger queue 
			new Thread(passengerThread).start();
			// start the Timer class
			DeskTimer deskstart= new DeskTimer(deskmanager,passengerThread, passengerQueue, closeDeskTime);
	
	}

}