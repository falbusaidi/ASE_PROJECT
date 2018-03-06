package Model;

import java.util.ArrayList;

import Interface.Observer;
import Interface.Subject;

public class Flight implements Subject {
	
	private String Code;
	private String Destination;
	private String Carrier;
	private int MaxCapacity;
	private double MaxWeight;
	private double MaxVolume;
	private String FlightStatus; // Boarding or departed
	private ArrayList<Observer> observers;
	
	
	private int CheckInPassengers;
	private double TotalWeight;
	private double TotalVolume;
	private double TotalExcessFees; 
	
	public Flight(String GetFlightCode, String GetFlightDest, String GetFlightCarrier, int getMaxPass, double getMaxW, double getMaxV)
	{
		this.Code			=	GetFlightCode;			//Contains the flight code
		this.Destination	=	GetFlightDest;			//Contains the destination of the flight
		this.Carrier		=	GetFlightCarrier;		//Contains the company used for the flight
		this.MaxCapacity	=	getMaxPass;				//Contains the maximum number of passengers on the flight
		this.MaxWeight		=	getMaxW;				//Contains the maximum weight of the baggage for the flight
		this.MaxVolume		=	getMaxV;				//Contains the maximum volume of the baggage for the flight
		CheckInPassengers =0; 
		TotalWeight=0.0;
		TotalVolume=0.0;
		TotalExcessFees=0.00; 
		observers = new ArrayList<Observer>();
		
	}
	
	/**
	 * Method to return the Flight code 
	 * @return String representing the flight code
	 */
	public String getCode() {
		return Code;
	}

	public int GetMaxPassenger()	//When called upon return the maximum number of passengers on the flight
	{
		return this.MaxCapacity;
	}
	
	public  double GetMaxWeight() 	//When called upon return the maximum weight of the baggage for the flight
	{
		return this.MaxWeight;
	}

	public double GetMaxVolume() 	//When called upon return the maximum volume of the baggage for the flight
	{
		return this.MaxVolume;
	}
	
	public synchronized void addPassengerToFlight(Booking booking)
	{
		CheckInPassengers++;
		TotalVolume+=booking.GetVolume();
		TotalWeight+=booking.GetWeight(); 
		TotalExcessFees+=booking.getExcessFees();
		this.notifyObservers();
		
	}
	public int getCheckInPassengers() {
		return CheckInPassengers;
	}

	public double getTotalWeight() {
		return TotalWeight;
	}

	public double getTotalVolume() {
		return TotalVolume;
	}
	public double getHold()
	{
		if (MaxCapacity != 0 ) {
			
			double maxpass = MaxCapacity;
			double passnum= CheckInPassengers; 
			return (passnum/maxpass)*100;
		}
			 
		return 0.00; 
				
	}
	
	public void registerObserver(Observer obs){
		observers.add(obs); 
	}

	/**
	 * De-register an observer with this subject
	 */
	public void removeObserver(Observer obs) {
		observers.remove(obs); 
	}

	/**
	 * Inform all registered observers that there's been an update
	 */
	public void notifyObservers() {
		
		for(Observer obs: observers) {
			obs.update();
		}
	}
	
	public String getFlightDetails()
	{
		String result="";
		result+="Flight :"+this.Code+"\n";
		result+=String.format("Hold: %.2f ", getHold())+"%\n";
		result+=String.format("Total Weight(Kg): %.2f", TotalWeight)+"\n";
		result+=String.format("Total Volume(C.CM): %.2f", TotalVolume)+"\n";
		result+=String.format("Excess Fees Collected: £ %.2f", TotalExcessFees)+"\n";
		return result; 
	}

}