package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Model.CheckInQueue;
import Model.DeskManager;
import Model.FlightList;

public class KioskGUI extends JFrame {
	


/**
 * The 
 * @param queue represent the model to access passengers in the queue
 * @param flightlist represent the model to get the flight details 
 * @param deskmanager represent the model to get the checkin desk
 */
public KioskGUI(CheckInQueue queueModel, FlightList flightModel, DeskManager desksModel)
{
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	
	// Add Panel to display the Passenger Queue
	// Add Panel to display the Check-in Desk Panel
	// Add Panel to display the Flight details information
	
	
	pack(); 
	setVisible(true);
}



}
