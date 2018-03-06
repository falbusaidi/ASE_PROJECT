package Model;

import java.util.ArrayList;

import Interface.Observer;
import Interface.Subject;

public class Desk implements Runnable , Subject{

	private int deskID;
	private boolean running; 
	private CheckInQueue queue; 
	private int delay = 5000;
	private ArrayList<Observer> observers; 
	private Booking booking; 
	private String processMessage; 
	
	
	

	public Desk(int deskID, CheckInQueue queue)
	{
		this.deskID = deskID;
		this.queue = queue; 
		observers = new ArrayList<Observer>();
		
	}
	
	public void run()
	{
		running = true; 
		while (running)
		{
			processMessage="";
			processMessage+="Desk: "+this.deskID+"\n";
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
						
			
		}
	}
	
	public void Stop()
	{
		running = false; 
	}
	
	public int getDelay() {
		return delay;
	}

	/**
	 * @param delay
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
	
	public String getPassengerDetail() {
		
		return processMessage; 
	
	}
}
