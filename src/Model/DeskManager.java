package Model;

import java.util.ArrayList;

public class DeskManager {
	
	private ArrayList<Desk> desks; 
	private int NumberofDesks;
	private int DesksToOpen; 
	private CheckInQueue queue ; 
	
	
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
			desks.add(new Desk(i+1, queue));
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
	
	
	

}
