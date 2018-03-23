package Model;



import java.util.ArrayList;

import java.util.LinkedList;

import java.util.Queue;



import Interface.Observer;

import Interface.Subject;



/**
 * @author fahad
 * Purpose of this class is to work as a wrapper to the queue of passengers waiting to be checked-in
 * the class implement the subject interface since it is used by GUI to display the Passenger list
 *
 */
public class CheckInQueue implements Subject{

	private String processMessage; // string to hold events

	private ArrayList<Observer> observers; // contain list of observers

	private Queue<Booking> CheckInQueue ; // the queue to hold passenger based on Fist in first out(FIFO) 
	
	private boolean checkinClosed; // boolean to check if timer for check-in is over
	
	private boolean  done; // to indicate that no more passengers are going to be added to the queue



	/**
	 * Constructor method to initialized the observer, checkinQUeue 
	 */
	public CheckInQueue() {
		
		observers = new ArrayList<Observer>();
		 CheckInQueue = new LinkedList<Booking>();
		 done= false;
		 checkinClosed=false; 
	}


	/**
	 * Method to add Passenger to end of the queue
	 * @param booking  object which contains infromation of passenger
	 */
	public synchronized void EnQueue(Booking booking) {	

		CheckInQueue.add(booking);
		// notify the observer that passenger is removed
		notifyObservers();
		// when a new passenger is added to the queue , notify waiting Desks
		notifyAll();
		// log event
		Log.getInstance().addEvent("Passenger added to Queue: "+String.format("%-7s,%s,%s,%3.2fkg, %3.0fcm x %3.0fcm x %3.0fcm",booking.GetBookingRef(),booking.GetPassenger().GetLastName(),booking.GetPassenger().GetFirstName(), booking.GetWeight(),booking.getHeight(),booking.getWidth(), booking.getDepth())+"\n");
		

	}

	
	/**
	 * Call to remove the first passenger of the Queue 
	 * @return return booking object which is used to retrieve passenger and booking information
	 */
	public synchronized Booking DeQueue() {

		// check if queue is empty if true threads wait else process the queue
		while(CheckInQueue.isEmpty()) { 

			try {
				
				wait(100);
				// check if there are not more passenger to add and the queue is empty then exit
				if(isDone()) {
					return null; 
				}
				
			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}
		// remove a passenger from the queue
		
		Booking booking = CheckInQueue.remove();	
		
		// update the queue display by notifying the observers
		this.notifyObservers();
		// log event 
		Log.getInstance().addEvent("Passenger removed from Queue: "+String.format("%-7s,%s,%s,%3.2fkg, %3.0fcm x %3.0fcm x %3.0fcm",booking.GetBookingRef(),booking.GetPassenger().GetLastName(),booking.GetPassenger().GetFirstName(), booking.GetWeight(),booking.getHeight(),booking.getWidth(), booking.getDepth())+"\n");
		return booking; 

	}

	

	/**

	 * Return true if the producer "passengerThread" finish processing all the bookings from the file and no more passengers to be added

	 * into the queue, otherwise false

	 * @return true is no more passengers to add to the queue, otherwise false

	 */

	public boolean isDone() {

		return done && CheckInQueue.isEmpty() ;

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

	

	/* (non-Javadoc)
	 * @see Interface.Subject#registerObserver(Interface.Observer)
	 * method to register an observer
	 * Have been copied from the Lecture Notes
	 */
	public void registerObserver(Observer obs){
		
		observers.add(obs); 

	}



	/**

	 * De-register an observer with this subject
	 * Have been copied from the Lecture Notes

	 */

	public void removeObserver(Observer obs) {

		observers.remove(obs); 

	}



	/**

	 * Inform all registered observers that there's been an update
	 * Have been copied from the Lecture Notes

	 */

	public void notifyObservers() {

		for(Observer obs: observers) {

			obs.update();

		}

	}

	

	/**
	 * Used to get the Desk events to be displayed by the GUI
	 * @return
	 */
	public String getQueueDetail() {
		processMessage="Number of Passengers in the Queue: "+CheckInQueue.size()+"\n";

		for(Booking object : CheckInQueue ) {

			// check if the desk are closed because the check-in time is over, if true indicat that passenger are not allowed to Board
			if (checkinClosed) {
				processMessage += String.format("|%-7s|%-15s|%-15s|%3.2fkg|%3.0fcm x %3.0fcm x %3.0fcm|Not allowed to Board|",object.GetBookingRef(),object.GetPassenger().GetLastName(),object.GetPassenger().GetFirstName(), object.GetWeight(),object.getHeight(),object.getWidth(), object.getDepth())+"\n"; 
				
			}else {
				
				// if check-in time is not over display normal passenger infromation
				processMessage += String.format("|%-7s|%-15s|%-15s|%3.2fkg|%3.0fcm x %3.0fcm x %3.0fcm|",object.GetBookingRef(),object.GetPassenger().GetLastName(),object.GetPassenger().GetFirstName(), object.GetWeight(),object.getHeight(),object.getWidth(), object.getDepth())+"\n"; 
				
			}    

		}
		return processMessage;
	}

	/**
	 * used to return the Queue data structure
	 * @return return a Queue Object of type bookings which contains all the bookings object which are used to get passenger information
	 */
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