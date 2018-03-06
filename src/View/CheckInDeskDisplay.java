package View;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Interface.Observer;
import Model.Desk;


public class CheckInDeskDisplay extends JPanel implements Observer{
	
private Desk model; 
private JTextArea passengerDetails; 
public CheckInDeskDisplay(Desk model)
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
	passengerDetails = new JTextArea();
	passengerDetails.setLineWrap(true);
	passengerDetails.setWrapStyleWord(true);
	passengerDetails.setEditable(false);
	passengerDetails.setSize(300, 100);
	this.add(passengerDetails);
	
	
}
public void update()
{
	passengerDetails.setText(model.getPassengerDetail());
}

}
