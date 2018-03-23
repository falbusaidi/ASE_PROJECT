package View;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Interface.Observer;
import Model.Desk;


/**
 * @author fahad
 * This class is used to display a single desk infromation in the GUI 
 * using a textArea
 *
 */
public class CheckInDeskDisplay extends JPanel implements Observer{
	
private Desk model;  // the model as the desk
private JTextArea passengerDetails;  // the Text Area

/**
 * Constructor : assign the model and register it self as an observer to the desk class
 * @param model representing the desk
 */
public CheckInDeskDisplay(Desk model)
{
	this.setLayout(new FlowLayout());
	this.model=model;
	// register the display class as an observer of the model
	model.registerObserver(this);
	// setup the text area in the GUI
	Setup(); 
	// call update to populate the GUI
	update(); 
}

/**
 * Setup the GUI component as a text area to display the desk information
 */
public void Setup()
{
	passengerDetails = new JTextArea();
	passengerDetails.setLineWrap(true);
	passengerDetails.setWrapStyleWord(true);
	passengerDetails.setEditable(false);
	passengerDetails.setFont(new Font("Courier New", Font.PLAIN, 14));
	passengerDetails.setSize(300, 100);
	this.add(passengerDetails);
	
	
}
/* (non-Javadoc)
 * @see Interface.Observer#update()
 * used to update the passenger being processed by the desk
 */
public void update()
{
	passengerDetails.setText(model.getPassengerDetail());
}

}
