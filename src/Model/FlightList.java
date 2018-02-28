package Model;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class FlightList {
	
	HashMap<String,Flight> flightmap ;			//Create new HashMap for the flights
	
	public FlightList()
	{
		flightmap = new HashMap<String,Flight>();
	}
	
	public void AddFlight(String FlightCode, Flight extraFlight)
	{
		flightmap.put(FlightCode, extraFlight);									//Add new flight to the HashMap
	}
	
	public void RemoveFlight(String FlightCode)
	{
		Flight existFlight = null;
		existFlight = FindFlight(FlightCode);								//Use FindFlight method to search for the flight
		if(existFlight != null)														//If the flight exists then
		{
			flightmap.remove(FlightCode);										//Remove the flight from the HashMap
		}
	}
	
	public Flight FindFlight(String FlightCode)
	{
		Flight searchflight = null;
		Set set = flightmap.entrySet();
		Iterator iterator = set.iterator();
		//boolean found = false;
		while(iterator.hasNext() && searchflight == null)
		{
			Map.Entry mentry = (Map.Entry)iterator.next();
			if(mentry.getKey().equals(FlightCode))
			{
				searchflight= (Flight) mentry.getValue();
			}
		}
		return searchflight;
	}
	
	public void populateFlight(String FlightDetails)
	{
		try
		{
			File f = new File(FlightDetails);
			Scanner input=new Scanner(f);
			while(input.hasNextLine())
			{
				String line=input.nextLine();
				if(line.length() != 0)
				{
					readLine(line);
				}
			}
			input.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.printf("FILE NAME %s  NOT FOUND %n", FlightDetails);
			System.err.println("ERROR " + e.getMessage());
			System.exit(1);
		}
		
	}
	
	private void readLine(String line)
	{
		try
		{
			String parts[] = line.split(",");
			int MaxPassenger = Integer.parseInt(parts[3]);
			double MaxWeight = Double.parseDouble(parts[4]);
			double MaxVolume = Double.parseDouble(parts[5]);
			Flight flights = new Flight(parts[0],parts[1],parts[2],MaxPassenger,MaxWeight,MaxVolume);
			AddFlight(parts[0],flights);
		}
		catch (NumberFormatException e)
		{
			System.err.println("ERROR " + e.getMessage());
			System.exit(1);
		}
		
	}
	
}