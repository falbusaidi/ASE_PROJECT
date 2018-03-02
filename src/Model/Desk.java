package Model;

public class Desk implements Runnable{

	private int deskID;
	private boolean running; 
	private CheckInQueue queue; 
	private int delay = 5000;
	
	
	

	public Desk(int deskID, CheckInQueue queue)
	{
		this.deskID = deskID;
		this.queue = queue; 
		
	}
	
	public void run()
	{
		running = true; 
		while (running)
		{
			Booking booking = queue.DeQueue();
			
			if ( booking !=null)
			{
				// check in the Passenger and get any excess fees
				double excess = booking.CheckIn();
				// add the Passenger to flight
				
				System.out.print("Desk number: "+this.deskID+"\n");
				System.out.println("Processing Booking"+booking.GetBookingRef()+","+booking.GetPassenger().GetLastName()+"\n");
				System.out.println("Passenger had excess:"+excess+"\n");
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

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
}
