package Model;


public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String BookingRef, String lastname){
        super("No booking found with Ref number:"+BookingRef+" and Last Name :"+lastname+"\n"
				+ "Please enter a valid Booking !!!\n");
    }

}






