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

public class KioskGUI extends JFrame {
	
private DeskManager deskModel;
private CheckInQueue queueModel;
private FlightList flightModel; 


private JPanel desksPanel; 
private JPanel flightPanel; 
private JPanel queuePanel;

/**
 * The 
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
	setLayout(new GridLayout(3,1));
	
	// Add Panel to display the Passenger Queue
	queuePanel = new JPanel();
	queuePanel.setLayout(new FlowLayout());
	JScrollPane Jscrolpanel = new JScrollPane(new CheckInQueueDisplay(queueModel));
	Jscrolpanel.setPreferredSize(new Dimension( 900,200));
	Jscrolpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	queuePanel.add(Jscrolpanel);
	this.add(queuePanel);
	// Add Panel to display the Check-in Desk Panel
	desksPanel = new JPanel();
	desksPanel.setLayout(new FlowLayout());
	
	for(Desk desk:deskModel.getDesks())
	{
		desksPanel.add(new CheckInDeskDisplay(desk)); 
	}
	this.add(desksPanel);
	
	
	// Add Panel to display the Flight details information
	
	flightPanel = new JPanel();
	flightPanel.setLayout(new FlowLayout());
	
	for(Map.Entry<String, Flight> entry:flightModel.getFlightmap().entrySet())
	{
		Flight flight = entry.getValue(); 
		flightPanel.add(new FlightDisplay(flight)); 
	}
	// Add Panel to display the Flight details information
	this.add(flightPanel);
	
	this.setSize(1400, 800);
	//this.pack();
	this.setTitle("Check-in Simulation");
	setVisible(true);
}


}
