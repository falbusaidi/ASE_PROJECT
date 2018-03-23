package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Interface.Observer;
import Model.CheckInQueue;
import Model.Desk;
import Model.DeskManager;
import Model.Flight;
import Model.FlightList;

/**
 * The purpose of this class is to display the Main Passenger Check-in simulation process
 * The GUI display the :
 * 1. Passenger Queue and details of passenger in the Queue 
 * 2. The Check-in desks and the passenger details which is process 
 * 3. The List of FLights and details such as number of checked-in passenger, Hold percentage, and total wieght and volum 
 * @author Fahad
 *
 */
public class KioskGUI extends JFrame {
	
private DeskManager deskModel;// represent the Desk model 
private CheckInQueue queueModel;// represent the queue model
private FlightList flightModel; // represent the flight model


private JPanel desksPanel;  // JPanel to display desk
private JPanel flightPanel; // JPanel to display flight information
private JPanel queuePanel; // JPanel to display queue information

/**
 * The Constructor 
 * @param queue represent the model to access passengers in the queue
 * @param flightlist represent the model to get the flight details 
 * @param deskmanager represent the model to get the checkin desk
 */
public KioskGUI(CheckInQueue queueModel, FlightList flightModel, DeskManager desksModel)
{
	// setting the model
	this.flightModel= flightModel;
	this.deskModel=desksModel; 
	this.queueModel= queueModel; 
	
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	// the main Panel layout as GridLayout
	setLayout(new GridLayout(3,1));
	
	// Setup the GUI Components
	Setup();
	//this.setSize(1400, 800);
	this.pack();
	this.setTitle("Check-in Simulation");
	setVisible(true);
}

/**
 * Setup the GUI components to display the Queue, Desks, and Flights
 */
private void Setup() {
	// Add Panel to display the Passenger Queue
		queuePanel = new JPanel();
		queuePanel.setLayout(new FlowLayout());
		// The queue model is passed to CheckInQueueDisplay which handels the display of the Queue
		JScrollPane Jscrolpanel = new JScrollPane(new CheckInQueueDisplay(queueModel));
		Jscrolpanel.setPreferredSize(new Dimension( 900,200));
		Jscrolpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		queuePanel.add(Jscrolpanel);
		this.add(queuePanel);
		
		// Add Panel to display the Check-in Desk Panel
		desksPanel = new JPanel();
		desksPanel.setLayout(new FlowLayout());
		
		// loop through the list of desks
		for(Desk desk:deskModel.getDesks())
		{
			// each desk are passed to separate GUI class CheckInDeskDisplay
			desksPanel.add(new CheckInDeskDisplay(desk)); 
		}
		this.add(desksPanel);
		
		
		// Add Panel to display the Flight details information
		
		flightPanel = new JPanel();
		flightPanel.setLayout(new FlowLayout());
		
		for(Map.Entry<String, Flight> entry:flightModel.getFlightmap().entrySet())
		{
			// each flight are passed to separate GUI class FlightDisplay which handel displaying each flight
			Flight flight = entry.getValue(); 
			flightPanel.add(new FlightDisplay(flight)); 
		}
		// Add Panel to display the Flight details information
		this.add(flightPanel);
}


}
