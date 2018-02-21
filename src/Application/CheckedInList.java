
package Application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * This method acts a a wrapper for a HashMap, it is used to contain all list of bookings 
 * which Passenger have checked-In . The Hasmap stored infromation as a key which is the flight code
 * and the value is an Arraylist of Bookings
 *
 */
public class CheckedInList 
{
	private HashMap<String,ArrayList<Booking>> checkInList ;
	
	private static CheckedInList instance; 
	
	/**
	 * Constructor used to initialize the Hashmap of checked in bookings
	 */
	private CheckedInList()
	{
		checkInList = new HashMap<String,ArrayList<Booking>>(); 
		
	}
	
	/**
	 * Method to return only one instance of Class
	 * @return return a singleton object of the class 
	 */
	public static CheckedInList getInstance()
	{
		if ( instance == null) 
			instance = new CheckedInList();
		return instance; 
	}
	
	public boolean add(Booking booking)
	{
		// get the flight code from the booking
		String key= booking.GetFlight().getCode(); 
		
		// Retrieve the array of booking from the Hashmap
		ArrayList <Booking> CheckedBookings = checkInList.get(key);
		// if array list does not exist then created else just add the object to the list since arraylist already exist 
		if (CheckedBookings == null) {
			CheckedBookings = new ArrayList<Booking>(); 
			checkInList.put(key,CheckedBookings);
		}
		
		// check if the booking already exist in the arraylist if does not add the object else do noting
		
		if(!CheckedBookings.contains(booking))
		{
			CheckedBookings.add(booking); 
			return true; 
		}
		 
		return false; 
	}
	
	/**
	 * Method to get all checked in bookings for a flight
	 * @param flightCode : String representing the flight code
	 * @return : returns an array of bookings for the flight
	 */
	public ArrayList <Booking> findBooking(String flightCode)
	{
		return checkInList.get(flightCode)	; 
	}
	
	/**
	 * remove a booking
	 * @param booking object
	 * @return true if object is removed else false
	 */
	public boolean removeBooking(Booking booking)
	{
		return checkInList.get(booking.GetFlight().getCode()).remove(booking); 
	}
	
	/**
	 * Method to print report 
	 * @param filename: string representing the name of output file
	 * @throws IOException : when any IO exception occuer i.e unable to open or write to a file
	 */
	public void printReport(String filename) throws IOException
	{
		FileWriter  fw  = new FileWriter(filename); 
		PrintWriter pw  = new PrintWriter(fw);
		
		String Result = "|Flight Code|Passenger Number|Total Weight|Total Volume|Total Excess Fees| Capacity Exceeded|\n";
		
		ArrayList <Booking> checkInBooking; 
		double weightSum ;
		double volumeSum ;
		double excessSum ; 
		int passenserSum ;
		int overCapacity ;
		boolean Exceed_capacity;
		
		// loop over checkedList Hashmap and gor each enrty get the arraylist of bookings  
		
		for(Map.Entry< String, ArrayList<Booking>> entry : checkInList.entrySet()) {
			
			// initialize the variables 
			checkInBooking = entry.getValue();
			weightSum = 0.0;
			volumeSum = 0.0;
			excessSum = 0.0; 
			
			// get total number of check-in passengers 
			passenserSum = checkInBooking.size();
			overCapacity = (checkInBooking.get(0).GetFlight().GetMaxPassenger())-passenserSum;
			Exceed_capacity = false; 
			
			// check is passenger number exceed 
			if (overCapacity<0)
			{
				Exceed_capacity= true; 
			}
			
			// loop over the list of bookings and calculate the total numbers for weight, volume, excess fees 
			for(Booking b : checkInBooking ) {
				// check if booking is checked-in
				if(b.GetcheckInStatus())
				{
					weightSum+= b.GetWeight();
					volumeSum+=b.GetVolume() ;
					excessSum+=b.getExcessFees() ;
				}
			
			}
			// Concatenate the results into a formated string 
			
			Result+=String.format("|%-11s|%-16d|%-12.2f|%-12.2f|%-17.2f|%-18s|\n", entry.getKey(),passenserSum,weightSum,
					volumeSum,excessSum,Boolean.toString(Exceed_capacity));
		
		}
		// print result to file
		pw.println(Result);
		fw.close();
	}

}
