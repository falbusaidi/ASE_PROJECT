package Model;

/**
 * Booking class is used to capture the details of a booking. 
 * @author fahad
 *
 */
public class Booking {
	
	
    private Passenger passenger;
    private Flight flight;
    private String BookingRef;// need to be 6 charater long and starts with a letter
    private Boolean checkInStatus;
    private double weight;
    private double volume;
    private double height;
    
	private double depth;
   
	private double width; 
    public static int counter = 0; 
   
    /**
     * Constructor 
     * @param BookingRef
     * @param passenger
     * @param flight
     * @param Weight
     * @param Volume
     */
    public Booking(String BookingRef , String Lastname, String firstname, Flight flight, Boolean checkInStatus)
    {
    	// check if supplied booking reference has correct format before creating the booking object
  
        if (BookingRef == null) 
        {
        	throw new InvalidBookingRefException(BookingRef); 
        }
        else if (CheckBookingRef(BookingRef) == -1)
        {
        	throw new InvalidBookingRefException(BookingRef); 
        }
        else
        {
        	this.BookingRef = BookingRef;
            this.passenger = new Passenger(firstname,Lastname);
            this.flight = flight;
            this.weight = 0.0;
            this.volume = 0.0;
            if (checkInStatus)
            	counter++; 
            this.checkInStatus = checkInStatus;
        }
        		 
    }

   
    /**
     * Get BookingRef
     * @return
     */
    public String GetBookingRef()
    {
        return BookingRef;
    }

    
    /**
     * Get Passenger
     * @return
     */
    public Passenger GetPassenger()
    {
        return passenger;
    }

    
    /**
     * Get Flight
     * @return
     */
    public Flight GetFlight()
    {
        return flight;
    }

    
    /**
     * Get Weight
     * @return
     */
    public  double GetWeight()
    {
        return weight;
    }

    
    /**
     * Get Volume
     * @return
     */
    public  double GetVolume()
    {
        return volume;
    }


    /**
     * Set the checking status for the booking and capture the weight and volume
     * @param Weight
     * @param Volume
     * @return
     */
    public int CheckIn(double weight, double height, double width, double depth) {
		// TODO Auto-generated method stub
    	if (checkInStatus)
    		return -1; 
       checkInStatus = true;
       this.weight = weight ;
       this.height = height;
       this.width = width ;
       this.depth = depth; 
       volume = this.depth*this.height*this.width; 
       counter++;
       return 0; 
		
	}
    public double CheckIn()
    {
    	// check if the passenger of this booking is already checked in 
    	if (checkInStatus)
    		return getExcessFees(); 
       checkInStatus = true;
       counter++;
       return this.getExcessFees(); 
        
    }

  
    /**
     * Calculate Baggage Excess fees based on the volume and weight
     * The check-in weight and volume is compared to the maximum values specified for the flight
     * Excess weight fees is charged as 2� per every 1 KG exceeding maximum weight allowance 
     * Extra volume fees is charged as 0.05� for every 1 Cubic centimeter exceeding maximum volume allowance
     *  
     * @return a double representing the excess fees 
     */
    public double getExcessFees()
    {
                
        double MaxWeight = flight.GetMaxWeight();
        double MaxVolume = flight.GetMaxVolume();
        double ExcessFees = 0.0; 

        if (weight > MaxWeight)
        {
        	ExcessFees += 2*(weight - MaxWeight);
        }
      
        if (volume > MaxVolume)
        {
        	ExcessFees += (0.05*(volume - MaxVolume));
        }
        
        return ExcessFees ;
    }

   
    /**
     * Check the format of Booking reference
     * Format should be as:
     * Length: 6 character long
     * Starts with a Character followed by numbers
     * 
     * @param BookingRef : String representing the booking reference number
     * @return 0 if string adheres to the format otherwise -1
     */
    public int CheckBookingRef(String BookingRef) 
    {
      
        char c = Character.toUpperCase(BookingRef.charAt(0));
        if (BookingRef.length() !=6)
        {
        	return -1; 
        }
        else if (!(c >= 'A' && c <= 'Z'))
        {
        	return -1; 
        }
        else if (!BookingRef.matches("^.*[1234567890]"))
        {
        	return -1; 
        }
        else
        {
        	return 0; 
        }      
    }
    
    /**
     * @return check-in status of the booking
     */
    public boolean GetcheckInStatus()
    {
    	return this.checkInStatus; 
    }
    
    /**
     * @return baggage height as integer 
     */
    public double getHeight() {
		return height;
	}
    
    /**
     * @return baggage depth as integer
     */
    public double getDepth() {
		return depth;
	}
    /**
     * @return baggage width as integer
     */
    public double getWidth() {
		return width;
	}
    
    
    public void setWeight(double weight) {
		this.weight = weight;
	}


	public void setDepth(double depth) {
		this.depth = depth;
	}


	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
}

