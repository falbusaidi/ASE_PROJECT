package Model;

import java.util.Timer;
import java.util.TimerTask;

public class DeskTimer
{	
	private DeskManager SetupDesks;
	Timer timer= new Timer();
	public DeskTimer(DeskManager getDeskManager, long closeDeskTime)
	{
		//Get settings for desk manager
		this.SetupDesks=getDeskManager;
		
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
				//System.out.println("Debug b");
			}
		}, closeDeskTime);
		
			
	}
		
}
