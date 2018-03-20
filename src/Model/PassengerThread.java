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
				
				//We use British Airways Baggage Allowance
				//http://www.edreams.com/blog/luggage-restrictions-by-airline/
				booking.setWeight(10.00 + (13.00*Math.random())); // Max weight of 23kg (generated value range from 10kg to 23kg)
				booking.setHeight(50.00 + Math.floor(30.00*Math.random())); // Max Height of 80 cm (generated value range from 50cm to 80cm)
				booking.setDepth(20.00 + Math.floor(23*Math.random())); // Max Depth of 43 cm (generated value range from 20cm to 43cm)
				booking.setWidth(40.00 + Math.floor(35*Math.random())); // Max Width of 75 cm (generated value range from 40cm to 73cm)
				
				// add the Passenger booking info to the check-in queue
				queue.EnQueue(booking);
				
				// wait for 5 second before adding the next passenger
				Thread.sleep(delay);
				
				if (bookinglist.GetNumberOfBookings()==0) {
					
					queue.setDone(true);
				}
			} catch (InterruptedException e) {

				System.out.println("Thread interrupted.");
			}
		}
		

	}
	
	/**
	 * used to stop the processing of the thread by setting a boolean variable 
	 */
	public void stop()
	{
		running = false; 
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
