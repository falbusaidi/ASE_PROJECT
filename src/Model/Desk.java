package Model;

import java.util.ArrayList;
import java.util.logging.Logger;

import Interface.Observer;
import Interface.Subject;

/**
 * @author Fahad
 * The purpose of the class is to simulate the Check-in Desk which pick passenger from the Queue and process 
 * Their check-in and assign them to a flight
 * 
 *
 */
public class Desk implements Runnable , Subject{

	private int deskID; // represent the Dek ID
	private boolean running;  // boolean to control running and stopping of the desk thread
	private CheckInQueue queue; // represent the Queue to read from 
	private int delay ; // integer representing the thread delay between each processing of passenger
	private ArrayList<Observer> observers; // list of observers
	private Booking booking; // booking object used to represent the passenger and booking information
	private String processMessage; // message to capture the desk events e.g open/close desk, process passenger
	private FlightList flightlist; // provide access to list of flight
	
	
	
	/**
	 * Constructor 
	 * @param deskID:  integer to represent the desk ID
	 * @param queue : the CheckinQueue object 
	 * @param flightlist: flightlist object to access list of flights
	 * @param delay : integer representing delay in millisecond 
	 */
	public Desk(int deskID, CheckInQueue queue, FlightList flightlist, int delay)
	{
		this.deskID = deskID;
		this.queue = queue; 
		this.delay=delay;
		observers = new ArrayList<Observer>();
		this.flightlist= flightlist;
		
		// when first time the desk is create the GUI message desk closed is displayed
		processMessage="";
		processMessage+="Desk: "+this.deskID+" is closed"+"\n";
		notifyObservers();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * The Thread run method, the purpose of this method:
	 * 1. Remove a passenger from the queue
	 * 2. Check-in a passenger
	 * 3. assign the passenger to a flight
	 */
	public void run()
	{
		running = true;
		
		// keep looping as long the boolean running is true
		while (running)
		{
			// message to capture the desk events and used to display in the GUI
			processMessage="";
			processMessage+="Desk: "+this.deskID+" is OPEN"+"\n";
			// notify observer GUI to display the message 
			notifyObservers();
			
			
			//check if no more Passenger to add to the queue and the queue is empty then stop and close the counter
			if (queue.isDone() && queue.isEmpty()) {
				running = false; 
				processMessage="";
				processMessage+="Desk: "+this.deskID+" is closed"+"\n";
				processMessage+="All Passengers boarded \n";
				notifyObservers();
				// log events
				Log.getInstance().writeLog();
			}	
			
			// Get the passenger from the Queue
			booking = queue.DeQueue();
			
			if ( booking !=null)
			{
				// check in the Passenger and get any excess fees
				double excess = booking.CheckIn();
			
				// write event massges and notify observer
				processMessage+=("Processing Passenger: "+booking.GetBookingRef()+" , "+booking.GetPassenger().GetLastName()+","+booking.GetPassenger().GetFirstName()+"\n");
				notifyObservers();
				processMessage+=("Baggage weight(Kg) : "+String.format("%.2f", booking.GetWeight())+"\n");
				processMessage+=("Baggage Dimensions(H*W*D): "+booking.getHeight()+"x"+booking.getWidth()+"x"+booking.getDepth()+"\n");
				notifyObservers();
				processMessage+="Excess Fees: £"+String.format("%.2f", excess)+"\n"; 
				notifyObservers();
				Log.getInstance().addEvent(processMessage); // log events
				
				// add the Passenger to flight
				flightlist.addpassenger(booking);
				
				
				// Thread sleep based on delay
				try {
					
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}
				
			
		}
		// Display close message once the loop is existed , meaning the desk thread was stopped
		processMessage="";
		processMessage+="Desk: "+this.deskID+" is closed"+"\n";
		notifyObservers();
		
		
	}
	
	/**
	 * Method to close the desk and set the display message
	 */
	public void Stop()
	{
		running = false; 
	}
	
	/**
	 * method to check is desk is open and running or closed
	 * @return boolean to represent the status of thread if true means desk is open is false means desk is closed
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Method to get the delay for a desk between each time it processes a passenger from the queue
	 * @return int representing the delay for the desk in milliseconds
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Method to get the delay for a desk between each time it processes a passenger from the queue
	 * @param delay in milliseconds
	 */
	public void setDelay(int delay) {
		
		this.delay = delay;
	}
	
	/**
	 * Register an observer with this subject
	 */
	public void registerObserver(Observer obs){
		observers.add(obs); 
	}

	/**
	 * De-register an observer with this subject
	 */
	public void removeObserver(Observer obs) {
		observers.remove(obs); 
	}

	/**
	 * Inform all registered observers that there's been an update
	 */
	public void notifyObservers() {
		
		for(Observer obs: observers) {
			obs.update();
		}
	}
	
	/**
	 * Returns the details of Passenger that the desk is processing 
	 * @return String which capture the details of passenger the desk is processing such as 
	 * Booking Reference, Passenger Last name, baggage weight and dimensions and any excess fees
	 */
	public String getPassengerDetail() {
		
		
		return processMessage; 
	
	}
	
	/**
	 * Get Desk ID
	 * @return int representing the desk ID
	 */
	public int getDeskID() {
		return deskID;
	}
	
	
}
