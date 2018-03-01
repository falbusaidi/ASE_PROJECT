package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Model.Booking;
import Model.BookingList;

public class CheckInQueue{
	Queue<Booking> CheckInQueue = new LinkedList<Booking>();
	FlightList flightlist = new FlightList();
	BookingList bookinglist = new BookingList(flightlist);
	PassengerThread thread = new PassengerThread();
	
	// Call to add Passenger into the line
	public void EnQueue(Object key) {		
		thread.start();
		Booking booking = thread.getBooking(key);
		CheckInQueue.add(booking);
		
	}
	
	// Call to remove the head of the Queue after finishing Checking In
	public void DeQueue(Object key) {
		thread.start();
		Booking booking = thread.getBooking(key);
		CheckInQueue.remove(booking);
		
	}
}
