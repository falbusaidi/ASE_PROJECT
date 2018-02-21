
package Test; 

import Application.Passenger;

import org.junit.Test;

import static org.junit.Assert.*;

public class PassengerTest {
    Passenger P = new Passenger("Sukrit", "Wong");
    @Test
    public void TestGetFirstName() {

        String FN = "Sukrit";
        String actualFN = P.GetFirstName();
        assertEquals("Correct", FN, actualFN);
    }

    @Test
    public void TestGetLastName() {
        String LN = "Wong";
        String actualLN = P.GetLastName();
        assertEquals("Correct", LN, actualLN);
    }
}