package Application;


/**
 * Exception for invalid booking reference 
 *
 */

public class InvalidBookingRefException extends RuntimeException {

    /**
     * @param BookingRef
     * 
     */
    public InvalidBookingRefException(String BookingRef){
        super("Invalid Booking Reference: " + BookingRef);
    }
}
