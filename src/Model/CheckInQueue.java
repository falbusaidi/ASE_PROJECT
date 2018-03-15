package Model;



import java.util.ArrayList;

import java.util.LinkedList;

import java.util.Queue;



import Interface.Observer;

import Interface.Subject;



public class CheckInQueue implements Subject{

	private String processMessage;

	private ArrayList<Observer> observers; 

	private Queue<Booking> CheckInQueue ; 
	
	private boolean checkinClosed; 
	
	private boolean  done; // to indicate that no more passengers are going to be added to the queue



	public CheckInQueue() {
		
		observers = new ArrayList<Observer>();
		 CheckInQueue = new LinkedList<Booking>();
		 done= false;
		 checkinClosed=false; 
	}

	// Call to add Passenger into the line

	public synchronized void EnQueue(Booking booking) {	

		CheckInQueue.add(booking);
		notifyObservers();
		notifyAll();
		Log.getInstance().addEvent("Passenger added to Queue: "+String.format("%-7s,%s,%s,%3.2fkg, %3.0fcm x %3.0fcm x %3.0fcm",booking.GetBookingRef(),booking.GetPassenger().GetLastName(),booking.GetPassenger().GetFirstName(), booking.GetWeight(),booking.getHeight(),booking.getWidth(), booking.getDepth())+"\n");
		

	}

	

	// Call to remove the head of the Queue after finishing Checking In

	public synchronized Booking DeQueue() {

		// TODO : also add a check to see if all passengers have already being processed

		while(CheckInQueue.isEmpty()) { 

			try {

				wait();

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}
		Booking booking = CheckInQueue.remove();
		this.notifyObservers();
		Log.getInstance().addEvent("Passenger removed from Queue: "+String.format("%-7s,%s,%s,%3.2fkg, %3.0fcm x %3.0fcm x %3.0fcm",booking.GetBookingRef(),booking.GetPassenger().GetLastName(),booking.GetPassenger().GetFirstName(), booking.GetWeight(),booking.getHeight(),booking.getWidth(), booking.getDepth())+"\n");
		return booking; 

	}

	

	/**

	 * Return true if the producer "passengerThread" finish processing all the bookings from the file and no more passengers to be added

	 * into the queue, otherwise false

	 * @return true is no more passengers to add to the queue, otherwise false

	 */

	public boolean isDone() {

		

		return done;

	}



	/**

	 * Set a boolean done which indicates that no more passengers are going to be added to the queue

	 * @param done a boolean 

	 */

	public void setDone(boolean done) {

	

		this.done = done;

	}

	

	/**

	 * check is queue is empty

	 * @return true if empty otherwise false

	 */

	public boolean isEmpty() {

		

		return CheckInQueue.size() == 0; 

	}

	

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

	

	public String getQueueDetail() {
		processMessage="Number of Passengers in the Queue: "+CheckInQueue.size()+"\n";

		for(Booking object : CheckInQueue ) {
			
			if (this.checkinClosed) {
				//processMessage += object.GetBookingRef()+", "+object.GetPassenger().GetLastName()+", Not allowed to Board \n";
				processMessage += String.format("|%-7s|%-15s|%-15s|%3.2fkg|%3.0fcm x %3.0fcm x %3.0fcm|Not allowed to Board|",object.GetBookingRef(),object.GetPassenger().GetLastName(),object.GetPassenger().GetFirstName(), object.GetWeight(),object.getHeight(),object.getWidth(), object.getDepth())+"\n"; 
				
			}else {
				processMessage += String.format("|%-7s|%-15s|%-15s|%3.2fkg|%3.0fcm x %3.0fcm x %3.0fcm|",object.GetBookingRef(),object.GetPassenger().GetLastName(),object.GetPassenger().GetFirstName(), object.GetWeight(),object.getHeight(),object.getWidth(), object.getDepth())+"\n"; 
				
			}
		   
		    

		}
		return processMessage;
	}

	public Queue<Booking> getQueue(){

		return CheckInQueue;

	}

	/**
	 * method to set the checkinClose variable to indicate that the check-in process is closed 
	 * @param checkinClosed Boolean
	 */
	public void setCheckinClosed(boolean checkinClosed) {
		this.checkinClosed = checkinClosed;
		this.notifyObservers();
	}

}