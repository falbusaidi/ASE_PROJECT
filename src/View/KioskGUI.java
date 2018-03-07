package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Booking;
import Model.CheckInQueue;
import Model.Desk;
import Model.DeskManager;
import Model.Flight;
import Model.FlightList;

public class KioskGUI extends JFrame {
	
private DeskManager deskModel;
private CheckInQueue queueModel;
private FlightList flightModel; 


private JPanel desksPanel; 
private JPanel flightPanel; 
private JScrollPane QueuePanel;

/**
 * The 
 * @param queue represent the model to access passengers in the queue
 * @param flightlist represent the model to get the flight details 
 * @param deskmanager represent the model to get the checkin desk
 */
public KioskGUI(CheckInQueue queueModel, FlightList flightModel, DeskManager desksModel)
{
	this.flightModel= flightModel;
	this.deskModel=desksModel; 
	this.queueModel= queueModel; 
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	
//	 Add Panel to display the Passenger Queue
//	QueuePanel = new JScrollPane();
//	QueuePanel.setLayout(new FlowLayout());
//	
//	for(CheckInQueue queue:queueModel.getQueue())
//	{
//	QueuePanel.add(new CheckInQueueDisplay(queue)); 
//	}
//	this.add(desksPanel, BorderLayout.NORTH);
	 
	
	// Add Panel to display the Check-in Desk Panel
	desksPanel = new JPanel();
	desksPanel.setLayout(new FlowLayout());
	for(Desk desk:desksModel.getDesks())
	{
		desksPanel.add(new CheckInDeskDisplay(desk)); 
	}
	this.add(desksPanel, BorderLayout.CENTER);
	
	
	// Add Panel to display the Flight details information
	
	flightPanel = new JPanel();
	flightPanel.setLayout(new FlowLayout());
	
	for(Map.Entry<String, Flight> entry:flightModel.getFlightmap().entrySet())
	{
		Flight flight = entry.getValue(); 
		flightPanel.add(new FlightDisplay(flight)); 
	}
	// Add Panel to display the Flight details information
	this.add(flightPanel, BorderLayout.SOUTH);
	
	this.setSize(1000, 280);
	setVisible(true);
}



}
