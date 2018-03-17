package Model;



import Model.BookingList;

public class PassengerThread implements Runnable{
	
	private BookingList bookinglist; 
	private CheckInQueue queue; 
	private boolean running; // used to control the thread execution to start or stop
	private int delay = 500; 
	
	
	/**constructor
	 * @param bookinglist : reference to bookinglist which holds the collection of bookings from the file
	 * @param queue : represent the passenger queue where bookings are going to be inserted to be processed by the desks
	 */
	public PassengerThread(BookingList bookinglist,CheckInQueue queue ) {
		this.bookinglist = bookinglist; 
		this.queue = queue; 
		Log.getInstance().addEvent("Passenger Thread: Thread started");
		Log.getInstance().writeLog();
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * runs the thread to read bookings from bookinglist and insert it into the passenger queue
	 */
	public void run() {
		running = true; 
	
		
		while (running &&(bookinglist.GetNumberOfBookings() > 0)) {
			
			
			try {
				// retrieve the random bookings object 
				Booking booking = bookinglist.getBooking(); 
				
				// assign the weight, width, height, and depth randomly to each passenger in the queue
				booking.setWeight(15.00 + (25.00*Math.random()));
				booking.setHeight(40.00 + Math.floor(40.00*Math.random()));
				booking.setDepth(40.00 + Math.floor(70*Math.random()));
				booking.setWidth(40.00 + Math.floor(70*Math.random()));
				Log.getInstance().addEvent("Passenger Thread: Random weight, height, width, and depth are generated for "+booking.GetBookingRef()+", "+booking.GetPassenger().GetLastName());
				Log.getInstance().writeLog();
				// add the Passenger booking info to the check-in queue
				queue.EnQueue(booking);
				
				// wait for 5 second before adding the next passenger
				Thread.sleep(delay);
				
				if (bookinglist.GetNumberOfBookings()==0) {
					
					queue.setDone(true);
				}
			} catch (InterruptedException e) {

				System.out.println("Thread interrupted.");
				Log.getInstance().addEvent("Passenger Thread: Thread interrupted");
				Log.getInstance().writeLog();
			}
		}
		

	}
	
	/**
	 * used to stop the processing of the thread by setting a boolean variable 
	 */
	public void stop()
	{
		running = false; 
		Log.getInstance().addEvent("Passenger Thread: Thread stopped");
		Log.getInstance().writeLog();
	}
	
	/**
	 * get the delay between each read and insert of passenger from the bookinglist into the queue
	 * @return delay represented in milliseconds
	 */
	public int getDelay() {
		return delay;
	}


	/**
	 * Sets the thread delay between each read and insert of passenger from the bookinglist into the queue 
	 * @param delay represented in milliseconds
	 */
	public void setDelay(int delay) {
		this.delay = delay;
		
	}
		
}
