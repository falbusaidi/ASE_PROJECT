
package Main;

import java.io.FileNotFoundException;

import Controller.Controller;
import Model.BookingList;
import Model.CheckInQueue;
import Model.DeskManager;
import Model.FlightList;
import Model.PassengerThread;
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
			DeskManager deskmanager = new DeskManager(passengerQueue, 3,3); 
			
			// load the flight and booking information
			flightlist.populateFlight("FlightList.csv");
			bookinglist.populateBookingDetails("PassengerList.csv");
			
			//star the passnger queue 
			Thread t = new Thread(passengerThread);
			t.start();
			//deskmanager.OpenDesks();
			long closeDeskTime = 30*1000;
			DeskTimer deskstart= new DeskTimer(deskmanager, closeDeskTime);
			KioskGUI gui = new KioskGUI(passengerQueue,flightlist,deskmanager); 
			SetSimulationTime setGui = new SetSimulationTime(deskmanager,passengerThread); 
			Controller controller = new Controller(setGui,deskmanager,passengerThread);
			
			
			//GUIMainWindow gui = new GUIMainWindow(bookinglist);
			
			// gui.run(); 
			
		}
		catch ( FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}