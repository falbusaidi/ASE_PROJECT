package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Interface.Observer;


public class CheckInQueue{
	private String processMessage;
	private ArrayList<Observer> observers; 
	

	Queue<Booking> CheckInQueue = new LinkedList<Booking>();

	
	
	// Call to add Passenger into the line
	public synchronized void EnQueue(Booking booking) {	
		observers = new ArrayList<Observer>();
		processMessage = "";
		CheckInQueue.add(booking);
		notifyAll();
		processMessage += booking.GetBookingRef()+","+booking.GetPassenger().GetLastName();
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
		return processMessage;
	}
	
	public Queue<Booking> getQueue(){
		return CheckInQueue;
	}
}
