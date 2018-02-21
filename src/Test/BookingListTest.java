package Test; 
import Application.FlightList;
import Application.Passenger;
import Application.BookingList;
import Application.Booking;
import Application.Flight;
import Application.BookingNotFoundException;
import org.junit.Test;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class BookingListTest {
    FlightList flightList = new FlightList();
    Flight F = new Flight("BA001", "Edinburgh", "BA", 100, 45.00, 25.00);
    Booking B = new Booking("A00001", "Wong","Sukrit",F, false);
    Passenger P = new Passenger("Sukrit", "Wong");
    BookingList BL = new BookingList(flightList);

    boolean TestNull;

    @Test
    public void TestaddBooking_bIsNull() {
        Booking booking_null = null;
        boolean test1 = BL.addBooking(booking_null);
        assertFalse(test1);

    }

    @Test
    public void TestaddBooking_bIsNotNull() {
        boolean test2 = BL.addBooking(B);

        assertTrue(test2);

    }

   
    @Test(expected = BookingNotFoundException.class)
    public void TestremoveBooking_bookingIsNull() {
        Booking booking = new Booking("B00001", "AA","Sukrit",F, false);
        BL.addBooking(booking); 
        boolean test = BL.removeBooking("B00002", "AAXXX");
        assertTrue(test);


    }

    @Test
    public void TestremoveBooking_bookingIsNotNull() {
        Booking booking = new Booking("B00001", "AA","Sukrit",F, false);
        BL.addBooking(booking);
        boolean test = BL.removeBooking("B00001", "AA");
        assertTrue(test);
    }

    @Test(expected = BookingNotFoundException.class)
    public void TestfindBooking_bIsNull() {
        Booking booking = new Booking("B00001", "AA","Sukrit",F, false);
        BL.addBooking(booking);
        Booking b_notfound = BL.findBooking("000001", "LastName");
        assertEquals(booking, b_notfound);
    }

    @Test
    public void TestfindBooking_bIsNotNull() {
        Booking test_find = new Booking("A00011", "asd","asd",F, false);
        BL.addBooking(test_find);
        Booking b = BL.findBooking("A00011", "asd");
        assertEquals(test_find, b);


    }
}