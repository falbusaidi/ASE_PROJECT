package View;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Interface.Observer;
import Model.Desk;
import Model.Flight;


public class FlightDisplay extends JPanel implements Observer{
	
private Flight model; 
private JTextArea flightDetails; 
public FlightDisplay(Flight model)
{
	this.setLayout(new FlowLayout());
	this.model=model;
	// register the display class as an observer of the model
	model.registerObserver(this);
	Setup(); 
	update(); 
}
public void Setup()
{
	flightDetails = new JTextArea();
	flightDetails.setLineWrap(true);
	flightDetails.setWrapStyleWord(true);
	flightDetails.setEditable(false);
	flightDetails.setSize(300, 200);
	this.add(flightDetails);
	
	
}
public void update()
{
	// TODO: add the method to set the flight infromation in the text field
	
	flightDetails.setText(model.getFlightDetails());
	
}

}
