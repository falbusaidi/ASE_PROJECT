package Model;

import java.util.ArrayList;

import Interface.Observer;
import Interface.Subject;

public class Desk implements Runnable , Subject{

	private int deskID;
	private boolean running; 
	private CheckInQueue queue; 
	private int delay ;
	private ArrayList<Observer> observers; 
	private Booking booking; 
	private String processMessage; 
	
	
	

	public Desk(int deskID, CheckInQueue queue, int delay)
	{
		this.deskID = deskID;
		this.queue = queue; 
		this.delay=delay;
		observers = new ArrayList<Observer>();
		
	}
	
	public void run()
	{
		running = true; 
		while (running)
		{
			
			processMessage="";
			processMessage+="Desk: "+this.deskID+" is OPEN"+"\n";
			this.notifyObservers();
			booking = queue.DeQueue();
			
			if ( booking !=null)
			{
				// check in the Passenger and get any excess fees
				double excess = booking.CheckIn();
				// add the Passenger to flight
							
				processMessage+=("Processing Passenger: "+booking.GetBookingRef()+" , "+booking.GetPassenger().GetLastName()+"\n");
				this.notifyObservers();
				
				processMessage+=("Baggage weight(Kg) : "+String.format("%.2f", booking.GetWeight())+"\n");
				processMessage+=("Baggage Dimensions(H*W*D): "+booking.getHeight()+"x"+booking.getWidth()+"x"+booking.getDepth()+"\n");
				this.notifyObservers();
				processMessage+="Excess Fees: £"+String.format("%.2f", excess)+"\n"; 
				this.notifyObservers();
				// ToDO: use Flight List to add to flight
				booking.GetFlight().addPassengerToFlight(booking);
				try {
					
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//check if no more Passenger to add to the queue and the queue is empty then stop and close the counter
			if (queue.isDone() && queue.isEmpty()) {
				running = false; 
				processMessage="";
				processMessage+="Desk: "+this.deskID+" is closed"+"\n";
				processMessage+="All Passengers boarded \n";
				this.notifyObservers();
			}		
			
		}
		
	}
	
	/**
	 * Method to close the desk and set the display message
	 */
	public void Stop()
	{
		processMessage="";
		processMessage+="Desk: "+this.deskID+" is closed"+"\n";
		this.notifyObservers();
		running = false; 
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
}
