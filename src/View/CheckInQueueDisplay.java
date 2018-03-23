package View;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Queue;



import javax.swing.JPanel;

import javax.swing.JTextArea;



import Interface.Observer;

import Model.Booking;

import Model.CheckInQueue;



/**
 * @author fahad
 * Purpose of the class is display the Queue information in the GUI as a text area with scroll
 * The class implements the observer interface to register itself with Flight object to get instance update
 *
 */
public class CheckInQueueDisplay extends JPanel implements Observer{
	
	private CheckInQueue model; // represent the model
	private JTextArea QueueDetail; // Text Area to display the queue details
	
	

	/**
	 * Constructor : setup up the model and assign itself as observer to the model
	 * @param queue represent the queue model
	 */
	public CheckInQueueDisplay(CheckInQueue queue) 
	{
		setLayout(new FlowLayout());
		this.model=queue;
		// register the display class as an observer of the model
		model.registerObserver(this);
		
		// setup the GUI Component
		Setup(); 
		// Call update first time to update the text area 
		update(); 
	}



	/**
	 * Setup the text area component to display a flight information
	 */
	public void Setup()
	{

		QueueDetail = new JTextArea();

		QueueDetail.setLineWrap(true);

		QueueDetail.setWrapStyleWord(true);
		QueueDetail.setFont(new Font("Courier New", Font.PLAIN, 14));

		QueueDetail.setEditable(false);

		QueueDetail.setSize(900, 300);
		this.add(QueueDetail);
	}



	/* (non-Javadoc)
	 * @see Interface.Observer#update()
	 * Updates the Text area with flight infromation 
	 */
	public void update()
	{
		
		QueueDetail.setText(model.getQueueDetail());
	}



	
}
