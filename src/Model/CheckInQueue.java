package Model;

import java.util.LinkedList;
import java.util.Queue;


public class CheckInQueue{
	
	
	Queue<Booking> CheckInQueue = new LinkedList<Booking>();
	
	
	// Call to add Passenger into the line
	public synchronized void EnQueue(Booking booking) {	
		
		CheckInQueue.add(booking);
		notifyAll();
		System.out.println("added booking"+booking.GetBookingRef()+","+booking.GetPassenger().GetLastName());
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
		System.out.println("removed booking");
		
		return CheckInQueue.remove();
		
		
	}
}
