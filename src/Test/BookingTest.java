package Test; 

import org.junit.Test;

import Model.Booking;
import Model.Flight;
import Model.Passenger;

import static org.junit.Assert.*;

public class BookingTest {





    Flight F = new Flight("BA001", "Edinburgh", "BA", 100, 45.00, 25.00);
    Booking B = new Booking("A00001", "Wong","Sukrit",F, false);
    Passenger P = new Passenger("Sukrit", "Wong");

    @Test
    public void TestGetBookingRef() {
        String BookingRef = "A00001";
        String actualBookingRef = B.GetBookingRef();
        assertEquals("Correct", BookingRef, actualBookingRef );


    }

    @Test
    public void TestGetPassenger() {

        Passenger Pass = B.GetPassenger();
        assertNotNull(B.GetPassenger());//check if the object is != null
        //checks if the returned object is of class Expression
    }

    @Test
    public void TestGetFlight() {
        Flight Flight = B.GetFlight();
        assertNotNull(B.GetFlight());

    }

    @Test
    public void TestGetWeight() {
        double actualWeight = B.GetWeight();
        assertEquals(0.0, actualWeight, 0.01);

    }

    @Test
    public void TestGetVolume() {
        double actualVolume = B.GetVolume();
        assertEquals(0.0, actualVolume, 0.01);
    }

    @Test
    public void TestGetCheckInStatus(){
        Boolean status = B.GetcheckInStatus();
        assertFalse(status);
    }

    @Test
    public void TestCheckIn() {
        B.CheckIn(30.00, 90.00, 20.00, 20.00);
        double actualWeight = B.GetWeight();
        assertEquals(30.00, actualWeight, 0.01);
        assertEquals(30.00, actualWeight, 0.01);
        assertTrue(B.GetcheckInStatus());

        Booking B_check = new Booking("A00001", "Wong","Sukrit",F, false);
        B_check.CheckIn(30,5.00, 1.00, 5.00);
        int test = B_check.CheckIn(30,5.00, 1.00, 5.00);
        assertEquals(-1, test, 0.01);
    }

    @Test
    public void TestgetExcessFees() {
        Booking B_BothExceed = new Booking("A00001", "Wong","Sukrit",F, false);
        B_BothExceed.CheckIn(45.00, 5.00, 3.00, 2.00);
        double actual_0 = B_BothExceed.getExcessFees();
        double expected_0 = 2*(B_BothExceed.GetWeight() - F.GetMaxWeight()) + 0.05*(B_BothExceed.GetVolume()-F.GetMaxVolume());
        assertEquals(expected_0, actual_0, 0.01);

        Booking B_VolumeExceed = new Booking("A00001", "Wong","Sukrit",F, false);
        B_VolumeExceed.CheckIn(45.00, 5.00, 3.00, 2.00);
        double actual_1 = B_VolumeExceed.getExcessFees();
        double expected_1 = 2*(B_VolumeExceed.GetWeight() - F.GetMaxWeight()) + 0.05*(B_VolumeExceed.GetVolume()-F.GetMaxVolume());
        assertEquals(expected_1, actual_1, 0.01);

        Booking B_WeightExceed = new Booking("A00001", "Wong","Sukrit",F, false);
        B_WeightExceed.CheckIn(50, 5.00, 1.00, 5.00);
        double actual_2 = B_WeightExceed.getExcessFees();
        double expected_2 = 2*(B_WeightExceed.GetWeight() - F.GetMaxWeight()) + 0.05*(B_WeightExceed.GetVolume()-F.GetMaxVolume());
        assertEquals(expected_2, actual_2, 0.01);



        Booking B_NoFee = new Booking("A00001", "Wong","Sukrit",F, false);
        B_NoFee.CheckIn(45, 5.00, 1.00, 5.00);
        double F = B_NoFee.getExcessFees();
        assertEquals(0.00, F, 0.01);

    }


    @Test
    public void checkBookingRef() {

        int t1 = B.CheckBookingRef("A0000111");
        assertEquals( -1, t1, 0.01);

        int t2 = B.CheckBookingRef("000001");
        assertEquals(-1 , t2, 0.01);

        int t3 = B.CheckBookingRef("A0000A");
        assertEquals(-1 , t3, 0.01);

        int t4 = B.CheckBookingRef(B.GetBookingRef());
        assertEquals(0 , t4, 0.01);


    }
    @Test
    public void TestgetHeight(){
        Booking TestHeight = new Booking("A00001", "Wong","Sukrit",F, false);
        TestHeight.CheckIn(45.00, 5.00, 1.00, 5.00);
        double actualHeight = TestHeight.getHeight();
        assertEquals(5.00, actualHeight, 0.01);
    }
    @Test
    public void TestgetDepth(){
        Booking TestDepth = new Booking("A00001", "Wong","Sukrit",F, false);
        TestDepth.CheckIn(45.00, 5.00, 1.00, 5.00);
        double actualDepth = TestDepth.getDepth();
        assertEquals(5.00, actualDepth, 0.01);
    }
    @Test
    public void TestgetWidth(){
        Booking TestWidth = new Booking("A00001", "Wong","Sukrit",F, false);
        TestWidth.CheckIn(45.00, 5.00, 1.00, 5.00);
        double actualWidth = TestWidth.getWidth();
        assertEquals(1.00, actualWidth, 0.01);
    }
}