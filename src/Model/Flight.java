package Model;

public class Flight {
	
	private String Code;
	private String Destination;
	private String Carrier;
	private int MaxPassenger;
	private double MaxWeight;
	private double MaxVolume;
	
	public Flight(String GetFlightCode, String GetFlightDest, String GetFlightCarrier, int getMaxPass, double getMaxW, double getMaxV)
	{
		this.Code			=	GetFlightCode;			//Contains the flight code
		this.Destination	=	GetFlightDest;			//Contains the destination of the flight
		this.Carrier		=	GetFlightCarrier;		//Contains the company used for the flight
		this.MaxPassenger	=	getMaxPass;				//Contains the maximum number of passengers on the flight
		this.MaxWeight		=	getMaxW;				//Contains the maximum weight of the baggages for the flight
		this.MaxVolume		=	getMaxV;				//Contains the maximum volume of the baggages for the flight
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
		return this.MaxPassenger;
	}
	
	public  double GetMaxWeight() 	//When called upon return the maximum weight of the baggages for the flight
	{
		return this.MaxWeight;
	}

	public double GetMaxVolume() 	//When called upon return the maximum volume of the baggages for the flight
	{
		return this.MaxVolume;
	}

}