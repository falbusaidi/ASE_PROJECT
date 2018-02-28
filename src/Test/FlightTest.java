package Test; 

import org.junit.Test;

import Model.Flight;

import static org.junit.Assert.*;

public class FlightTest {
    Flight F = new Flight("A00001", "Edinburgh", "BA", 100, 30.00, 90);
    @Test
    public void TestgetCode() {
        String Code = "A00001";
        String actualCode = F.getCode();
        assertEquals("Correct", Code, actualCode);
    }

    @Test
    public void TestgetMaxPassenger() {
        int MaxPassenger = 100;
        int actualMaxPassenger = F.GetMaxPassenger();
        assertEquals("Correct", MaxPassenger, actualMaxPassenger);
    }

    @Test
    public void TestGetMaxWeight() {
        double MaxWeight = 30.00;
        double actualMaxWeight = F.GetMaxWeight();
        assertEquals(MaxWeight, actualMaxWeight, 0.01);
    }

    @Test
    public void TestGetMaxVolume() {
        double MaxVolume = 90.00;
        double actualMaxVolume = F.GetMaxVolume();
        assertEquals(MaxVolume, actualMaxVolume, 0.01);
    }
}