package Model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * This class acts as a wrapper class to the HashMap which contains the list of Bookings 
 * @author fahad
 *
 */
public class BookingList {
	
	

	private HashMap <String,Booking> bookingslist; // hold list of bookings 
	private FlightList flightlist; // Reference to list of flights
	
	
	/**
	 * Constructor 
	 * @param flightlist
	 */
	public BookingList(FlightList flightlist)
	{
		
		bookingslist= new HashMap<String,Booking>();
		this.flightlist = flightlist; 
	}
	
	/**
	 * Method to add a booking to the BookingList HashMap
	 * @param b
	 * @return
	 */
	public boolean addBooking(Booking booking)
	{
		
		if (booking!= null)
		{
				String Key = booking.GetBookingRef()+booking.GetPassenger().GetLastName();
				this.bookingslist.put(Key, booking); 
				return true; 
		}
		return false; 
	}
	
	/**
	 * Remove a Booking from the bookinglist 
	 * @param Booking_Ref : booking reference as a string
	 * @param LastName: passenger lastname as a string
	 * @return true is object successfully removed else false
	 */
	public boolean removeBooking(String Booking_Ref, String LastName)
	{
		Booking booking = this.findBooking(Booking_Ref, LastName);
		if(booking == null) 
			return false;
		String key = Booking_Ref+LastName; 
		return bookingslist.remove(key,booking);
		
		
	}
	
	/**
	 * Find and Retrun an booking object using the booking Reference and Passenger lastname  
	 * @param Booking_Ref : booking reference as a string
	 * @param LastName: passenger lastname as a string
	 * @return booking object if object is found else null
	 */
	public Booking findBooking(String Booking_Ref, String Lastname)
	{
		Booking booking = bookingslist.get(Booking_Ref+Lastname);
		if (booking ==null)
			throw new BookingNotFoundException(Booking_Ref,Lastname); 
		return  booking; 
	
	}
		
	/**
	 * Method to load bookinglist information
	 * @param Filename  bane of the file 
	 */
	public void populateBookingDetails(String Filename) throws FileNotFoundException
	{
		File f = new File(Filename);
		String Line;
		Scanner scanner = new Scanner(f);

		while (scanner.hasNextLine()) {
			Line = scanner.nextLine();
			if(!Line.isEmpty())
				readLine(Line);
		}
		scanner.close();

	}

	/**
	 * Method to process the line read from file
	 * @param line  string representing the file line
	 */
	private void readLine(String line) {
		// TODO Auto-generated method stub
		String[] bookingDetails = line.split(",");
		String book_ref=null; 
		String lastname;
		String firstname; 
		Flight flight ; 
		Boolean checInstatus; 
		String key;
		Booking booking;
		try
		{
			// check if line is empty
			if (bookingDetails.length == 5)
			{
				book_ref= bookingDetails[0]; 
				lastname = bookingDetails[1].toUpperCase();
				firstname = bookingDetails[2].toUpperCase(); 
				flight = flightlist.FindFlight((bookingDetails[3])); 
				checInstatus = Boolean.valueOf(bookingDetails[4]); 
				key = book_ref+lastname;
				booking = new Booking(book_ref,lastname,firstname,flight,checInstatus);
				bookingslist.put(key,booking ); 
				
				// if the passenger already checked in than add to checkedinList
				if(checInstatus)
				{
					CheckedInList.getInstance().add(booking);
				}
			}	
		}catch(InvalidBookingRefException e) {
			System.out.print("Can't create Booking, Invalid reference number :"+book_ref);
		}
		
	}
	
	/**
	 * Return number of items in the HashMap 
	 * @return integer as number of bookings 
	 */
	public int GetNumberOfBookings()
	{
		
		return bookingslist.keySet().size(); 
	}
	
	public HashMap<String, Booking> Getbookingslist() {
		
		return bookingslist;
	}
	
	/**
	 * Return List of Keys in the HashMap 
	 * @return List of Keys in the HashMap in random manner 
	 */
	public List ShuffleKeys() {
		
		List HashKeys = new ArrayList(this.bookingslist.keySet());
		Collections.shuffle(HashKeys);
		return HashKeys;
	}
	/**
	 * Return Object of Keys in the HashMap 
	 * @return Object of Keys in the HashMap after shuffle
	 */
	public Object ReturnBooking(int n) {
		List Keys = this.ShuffleKeys();
		return Keys.get(n);
	}

}
