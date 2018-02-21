package Application;

public class Passenger {

    private String FirstName;
    private String LastName;

    public Passenger(String FirstName, String LastName)
    {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    // Return First Name
    public String GetFirstName()
    {
        return FirstName;
    }

    // Return Last Name
    public String GetLastName()
    {
        return LastName;
    }    
}

