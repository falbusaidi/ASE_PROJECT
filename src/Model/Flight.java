package Model;

public class Flight {
	
	private String Code;
	private String Destination;
	private String Carrier;
	private int MaxCapacity;
	private double MaxWeight;
	private double MaxVolume;
	private String FlightStatus; // Boarding or departed
	
	
	private int CheckInPassengers;
	private double TotalWeight;
	private double TotalVolume;
	
	
	public Flight(String GetFlightCode, String GetFlightDest, String GetFlightCarrier, int getMaxPass, double getMaxW, double getMaxV)
	{
		this.Code			=	GetFlightCode;			//Contains the flight code
		this.Destination	=	GetFlightDest;			//Contains the destination of the flight
		this.Carrier		=	GetFlightCarrier;		//Contains the company used for the flight
		this.MaxCapacity	=	getMaxPass;				//Contains the maximum number of passengers on the flight
		this.MaxWeight		=	getMaxW;				//Contains the maximum weight of the baggages for the flight
		this.MaxVolume		=	getMaxV;				//Contains the maximum volume of the baggages for the flight
		CheckInPassengers =0; 
		TotalWeight=0.0;
		TotalVolume=0.0;
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
		this.CheckInPassengers++;
		this.TotalVolume+= booking.GetVolume();
		this.TotalWeight+=booking.GetWeight(); 
		System.out.println("Flight"+this.Code+","+this.CheckInPassengers+","+this.TotalWeight+"\n");
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

}