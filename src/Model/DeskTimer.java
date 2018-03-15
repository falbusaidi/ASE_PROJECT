package Model;

import java.util.Timer;
import java.util.TimerTask;

public class DeskTimer
{	
	private DeskManager SetupDesks;
	private PassengerThread passengerthread; 
	private CheckInQueue queue; 
	
	Timer timer= new Timer();
	public DeskTimer(DeskManager getDeskManager, PassengerThread passengerthread, CheckInQueue queue,  long closeDeskTime)
	{
		//Get settings for desk manager
		this.SetupDesks=getDeskManager;
		this.passengerthread= passengerthread;
		this.queue = queue; 
		
		//System.out.println("Debug 1");
		//Initialize schedule to setup desks
		timer.schedule(new TimerTask() {
			@Override
			public void run()
			{
				SetupDesks.OpenDesks();
				//System.out.println("Debug a");				
			}
		}, 1*1000);
		
		//Initialize schedule to close the desks
		timer.schedule(new TimerTask() {
			@Override
			public void run()
			{
				SetupDesks.closeAllDesks();
				queue.setCheckinClosed(true);
				passengerthread.stop();
				Log.getInstance().writeLog();
				//System.out.println("Debug b");
			}
		}, closeDeskTime);
		
			
	}
		
}
