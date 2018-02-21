package Test; 
import Application.FlightList;

import Application.Flight;


import org.junit.Test;

import static org.junit.Assert.*;

public class FlightListTest {
	
	@Test
	public void addnewflight()			//This method is mainly used to test the AddFlight() in FlightList
	{
		FlightList Testing_Null = new FlightList();					//Create new FlightList
		Flight F = new Flight("A00001", "Edinburgh", "BA", 100, 30.00, 90);		//Create new Flight
		String actualresult =F.getCode();							//Get Flight code from Flight
		Testing_Null.AddFlight(actualresult,F);						//Add Flight to FlightList using AddFlight method
		assertNotNull(Testing_Null.FindFlight(F.getCode()));		//Check if FlightList is not empty
		String expectedresult = Testing_Null.FindFlight(F.getCode()).getCode() ; 		//Get Flight code from the Flight in the FlightList
		assertEquals(actualresult,expectedresult);					//Check if Flight code corresponds to Flight code entered
	}
	
	@Test
	public void removeaflight()			//This method is mainly used to test the RemoveFlight() in FlightList
	{
		FlightList Testing_Full= new FlightList();					//Create new FlightList
		Flight F = new Flight("A00001", "Edinburgh", "BA", 100, 30.00, 90);		//Create new Flight
		String actualresult =F.getCode();							//Get Flight code from Flight
		Testing_Full.AddFlight(actualresult,F);						//Add Flight to FlightList
		assertNotNull(Testing_Full.FindFlight(F.getCode()));		//Check if FlightList is not empty
		String Code="A00001";										//Setup Flight code to be removed
		Testing_Full.RemoveFlight(Code);							//Remove Flight from FlightList using RemoveFlight(), FindFlight() and Flight code
		assertNull(Testing_Full.FindFlight(Code));					//Check if FlightList is empty
	}
	
	@Test
	public void testpopulation()			//This method is mainly used to check populateFlight() and readLine() in FlightList
	{
		FlightList Testing_Null = new FlightList();			//Create new FlightList
		Testing_Null.populateFlight("FlightList.csv");		//Populate FlightList with Flights from csv file
		assertNotNull(Testing_Null.FindFlight("A1"));		//Check if FlightList is not empty by finding Flight A1
	}
	
}