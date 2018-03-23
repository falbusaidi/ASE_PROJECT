package View;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Interface.Observer;
import Model.Desk;
import Model.Flight;


/**
 * @author Fahad
 * The purpose of this class is to display a flight infromation into the GUI 
 * The GUI will display following infromation:
 * 1. number of checked-in passenger out of the total booked for the flight
 * 2. Hold percentage
 * 3. Total Weight
 * 4. Total Volume 
 *
 */
public class FlightDisplay extends JPanel implements Observer{
	
private Flight model;  // model representing a flight
private JTextArea flightDetails; // a text area to display the flight information

/**
 * Constructor : assign the model, register as observer for the model, and setup the display GUI Text area
 * @param model represent the flight
 */
public FlightDisplay(Flight model)
{
	this.setLayout(new FlowLayout());
	this.model=model;
	// register the display class as an observer of the model
	model.registerObserver(this);
	
	// setup the GUI components
	Setup(); 
	
	// Initialize the display text area with values from the model
	update(); 
}
/**
 * Setup the GUI text area to display the flight information
 */
public void Setup()
{
	flightDetails = new JTextArea();
	flightDetails.setLineWrap(true);
	flightDetails.setWrapStyleWord(true);
	flightDetails.setEditable(false);
	flightDetails.setFont(new Font("Courier New", Font.PLAIN, 14));
	flightDetails.setSize(300, 200);
	this.add(flightDetails);
	
	
}
/* (non-Javadoc)
 * @see Interface.Observer#update()
 * used to update the GUI with flight information from the model
 */
public void update()
{	
	flightDetails.setText(model.getFlightDetails());
}

}
