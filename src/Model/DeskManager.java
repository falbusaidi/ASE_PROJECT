package Model;

import java.util.ArrayList;

import Interface.Observer;
import Interface.Subject;

/**
 * @author fahad
 * This class is used to manage the check-in desk 
 * it creates and initialize the desks object
 * Open and close all desks
 * Open and close desks by ID from the GUI 
 */
public class DeskManager implements Subject{
	
	private ArrayList<Desk> desks; 
	private int totalDesks;
	private int openDesks; 
	private CheckInQueue queue ; 
	private ArrayList<Observer> observers; 
	private int delay = 5000;
	private boolean closeDesks; 
	private FlightList flightlist; 
	

	/**
	 * Constructor
	 * @param queue : represent the passenger queue
	 * @param NumberofDesks : number of available desks
	 * @param DesksToOpen: number of open desks
	 */
	public DeskManager(CheckInQueue queue, FlightList flightlist,int NumberofDesks, int DesksToOpen )
	{
		this.queue = queue; 
		desks = new ArrayList<Desk>(); 
		this.totalDesks = NumberofDesks;
		this.openDesks = DesksToOpen;
		observers = new ArrayList<Observer>();
		closeDesks= false; 
		this.flightlist= flightlist; 
		
		initializeDesks(); 
	}
	
	public int getNumberofDesks() {
		return totalDesks;
	}

	public int getDesksToOpen() {
		return openDesks;
	}

	/**
	 * initialize the  desks objects into an arraylist of Desks
	 */
	private void initializeDesks()
	{
		for ( int i=0; i<totalDesks; i++)
		{
			desks.add(new Desk(i+1,queue,flightlist,delay));
		}
	}
	
	/**
	 * Used to create a desk object based on the allow number of open desks and schedule them as thread
	 */
	public void OpenDesks()
	{
		for ( int i=0; i<openDesks; i++)
		{
			new Thread(desks.get(i)).start();
		}
	}
	
	/**
	 * Used to close all the threads
	 */
	public void closeAllDesks()
	{
		closeDesks= true; 
		for (Desk desk:desks)
		{
			if(desk.isRunning()) {
				desk.Stop();
				openDesks--; 
			}
			notifyObservers();
		}
		
	}
	
	/**
	 * Get the list of open desk
	 * @return an list of open desks as an Arraylist of Deks
	 */
	public ArrayList<Desk> getDesks() {
		return desks;
	}
	
	/**
	 * Used to return the delay value for the desk threads in 
	 * @return  delays in milliseconds 
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * delay value for the desk thread
	 * @param delay in milliseconds 
	 */
	public void setDelay(int delay) {
		this.delay = delay;
		for (Desk desk:desks)
		{
			desk.setDelay(delay);
		}
		
	}
	
	/**
	 * Register an observer with this subject . 
	 * This has been taken from ASE course material 
	 */
	public void registerObserver(Observer obs){
		observers.add(obs); 
	}

	/**
	 * De-register an observer with this subject
	 * This has been taken from ASE course material 
	 */
	public void removeObserver(Observer obs) {
		observers.remove(obs); 
	}

	/**
	 * Inform all registered observers that there's been an update
	 * This has been taken from ASE course material 
	 */
	public void notifyObservers() {
		
		for(Observer obs: observers) {
			obs.update();
		}
	}
	
	/**
	 * Open a desk by ID
	 * @param ID integer representing the desk ID
	 *  @return 0 is successful, or return 1 if check-in time is over , or -1 if desk is not found
	 */
	public int openDeskByID(int ID)
	{
		Desk desk = findDeskByID(ID);
		// if no desk was found with giving ID return -1
		if (desk == null) {
			return -1;
		// else if all the desks are closed then you can't open a desk
		} else if (closeDesks) {
			return 1; 
		} else {
			
			if(!desk.isRunning()) {
				new Thread(desk).start();
				openDesks++;
			}
			notifyObservers();
			return 0; 	
		}
		
	}
	
	/**
	 * close a desk given an ID
	 *@param ID integer representing the desk ID
	 * @return 0 is successful, or return 1 if check-in time is over , or -1 if desk is not found
	 */
	public int closeDeskByID(int ID)
	{
		Desk desk = findDeskByID(ID);
		if (desk == null) {
			return -1;
		}else if (closeDesks) {
			return 1; 
		} else {
			if (desk.isRunning()) {
				desk.Stop();
				openDesks--;
				notifyObservers();
			}
			notifyObservers();
			return 0; 
		}
	}
	/**
	 * Find a Desk by ID
	 * @param ID int representing the desk ID
	 * @returnn Desk object bsaed on supplied ID
	 */
	public Desk findDeskByID(int ID) {
		
		for( Desk desk: desks) {
			if (desk.getDeskID() == ID)
				return desk; 
		}
		return null; 
	}
	
}
