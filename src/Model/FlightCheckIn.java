package Model;
public class FlightCheckIn {
	
	private String FlightCode;
	private int Capacity;
	private int CurrentPassenger;
	private double CurrentWeight;
	private double CurrentVolume;
	
	public FlightCheckIn(String setFlightcode, int setCapacity)
	{
		this.FlightCode = setFlightcode;
		this.Capacity = setCapacity;
		this.CurrentPassenger = 0;
		this.CurrentWeight = 0;
		this.CurrentVolume = 0;
	}
	
	private void addweight(double weight)
	{
		this.CurrentWeight = this.CurrentWeight + weight;
	}
	
	private void addvolume(double volume)
	{
		this.CurrentVolume = this.CurrentVolume + volume;
	}
	
	private void addpassenger()
	{
		this.CurrentPassenger++;
	}
	
	public void addPassengerToFlight(String getFlightcode, int newPassenger, double newweight, double newvolume)
	{
		addpassenger();
		addvolume(newvolume);
		addweight(newweight);
	}
	
	public boolean exceedflight()
	{
		int exceedPassenger = this.Capacity - this.CurrentPassenger;
		if(exceedPassenger < 0)
		{
			return true;
		}
		else return false;
	}
	
	public String findflightcode()
	{
		return this.FlightCode;
	}
}