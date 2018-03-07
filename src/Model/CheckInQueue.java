package Model;

import java.util.LinkedList;
import java.util.Queue;


public class CheckInQueue{
	
	
	Queue<Booking> CheckInQueue = new LinkedList<Booking>();
	private boolean done= false; // to indicate that no more passengers are going to be added to the queue


	// Call to add Passenger into the line
	public synchronized void EnQueue(Booking booking) {	
		
		CheckInQueue.add(booking);
		notifyAll();
		//System.out.println("added booking"+booking.GetBookingRef()+","+booking.GetPassenger().GetLastName());
	}
	
	// Call to remove the head of the Queue after finishing Checking In
	public synchronized Booking DeQueue() {
		// TODO : also add a check to see if all passengers have already being processed
		while(CheckInQueue.isEmpty()) { 
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("removed booking");
		
		return CheckInQueue.remove();
		
		
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
}
