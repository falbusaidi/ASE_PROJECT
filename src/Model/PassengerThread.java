package Model;



import Model.BookingList;

public class PassengerThread implements Runnable{
	
	private BookingList bookinglist; 
	private CheckInQueue queue; 
	private boolean running; 
	private int FIVE_SECOND = 5000; 
	
	public PassengerThread(BookingList bookinglist,CheckInQueue queue ) {
		this.bookinglist = bookinglist; 
		this.queue = queue; 
	}
	

	public void run() {
		running = true; 
		
		while ((bookinglist.GetNumberOfBookings() > 0)) {
			
			try {
				// retrieve the random bookings object 
				Booking booking = bookinglist.getBooking(); 
				
				// assign the weight, volume randomly
				booking.setWeight(15.00 + (25.00*Math.random()));
				booking.setHeight(40.00 + Math.floor(40.00*Math.random()));
				booking.setDepth(40.00 + Math.floor(70*Math.random()));
				booking.setWidth(40.00 + Math.floor(70*Math.random()));
				
				// add the Passenger booking info to the check-in queue
				queue.EnQueue(booking);
				
				// wait for 5 second before adding the next passenger
				Thread.sleep(FIVE_SECOND);
			} catch (InterruptedException e) {

				System.out.println("Thread interrupted.");
			}
		}
		

	}
	
	public void stop()
	{
		running = false; 
	}
		
}
