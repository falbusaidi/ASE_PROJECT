package Model;

import java.util.ArrayList;

import Interface.Observer;
import Interface.Subject;

public class DeskManager {
	
	private ArrayList<Desk> desks; 
	private int NumberofDesks;
	private int DesksToOpen; 
	private CheckInQueue queue ; 
	private int delay = 5000;
	
	
	
	

	public DeskManager(CheckInQueue queue, int NumberofDesks, int DesksToOpen )
	{
		this.queue = queue; 
		desks = new ArrayList<Desk>(); 
		this.NumberofDesks = NumberofDesks;
		this.DesksToOpen = DesksToOpen;
		
		initializeDesks(); 
	}
	
	private void initializeDesks()
	{
		for ( int i=0; i<DesksToOpen; i++)
		{
			desks.add(new Desk(i+1, queue,delay));
		}
	}
	
	public void OpenDesks()
	{
		for (Desk desk:desks)
		{
			Thread t = new Thread(desk);
			t.start();
		}
	}
	
	public void closeAllDesks()
	{
		for (Desk desk:desks)
		{
			desk.Stop();
		}
	}
	
	public ArrayList<Desk> getDesks() {
		return desks;
	}
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
		for (Desk desk:desks)
		{
			desk.setDelay(delay);
		}
		
	}
}
