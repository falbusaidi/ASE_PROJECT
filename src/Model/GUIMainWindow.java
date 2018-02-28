package Model;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to launch the main window for the check-in interface
 * @author fahad
 *
 */
public class GUIMainWindow  extends JFrame implements ActionListener{
	//holds list of bookings from the file
	
	
	private BookingList bookinglist; // Wrapper class containing a collection to all bookings
	private boolean ReportGenerated; // boolean used to control opening and closing the Kiosk
	private JButton searchButton; 
	private JTextField lastnamein;
	private JTextField BookingRefin;
	private JLabel LB_message; 
	


	
	
	/**
	 * Construct the JFrame 
	 * @param bookinglist : objects contains all the bookings
	 */
	public GUIMainWindow(BookingList bookinglist)
	{
		this.bookinglist = bookinglist;
		
		ReportGenerated = false; 
		this.setTitle("Check-in System");
		this.setLayout(new BorderLayout());
		SetupSearchPanel();	
		this.setSize(350, 150);
		//this.pack();
		
	}
	
	
	
	
	/**
	 * This method to setup the Search Panel which to allow passenger to enter last name and booking reference
	 */
	private void SetupSearchPanel() {
		// TODO Auto-generated method stub
		
		
		JPanel SearchPanel = new JPanel();
		SearchPanel.setLayout(new BorderLayout());


		JPanel OrderPanel = new JPanel();
		OrderPanel.setLayout(new GridLayout(2,2));

		

		searchButton  = new JButton("Search");
		searchButton.addActionListener(this);

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new FlowLayout());
		ButtonPanel.add(searchButton);

		

		JLabel lastname = new JLabel("Lastname:"); 
		lastnamein = new JTextField(10);
		OrderPanel.add(lastname);

		OrderPanel.add(lastnamein);

		

		JLabel BookingRef = new JLabel("Booking Ref:");

		BookingRefin = new JTextField(10);

		OrderPanel.add(BookingRef);

		OrderPanel.add(BookingRefin);

		LB_message= new JLabel("Welcome to Self Check-in Kiosk");
		LB_message.setFont(new Font("Serif", Font.BOLD, 20));
		
		SearchPanel.add(LB_message, BorderLayout.NORTH);

		SearchPanel.add(OrderPanel, BorderLayout.CENTER);

		SearchPanel.add(ButtonPanel,BorderLayout.SOUTH);

		this.add(SearchPanel,BorderLayout.CENTER);
	}

	

	/**
	 * Used to launch the GUI
	 */
	public void run()
	{
		if(this.CheckInClosed()) {
			LB_message.setText("Self Check-In Kiosk is Closed");
			if (!ReportGenerated) {
				try {
					CheckedInList.getInstance().printReport("Report.txt");
					ReportGenerated = true; 
					
				}catch(IOException exception)
				{
					System.out.println(exception.getMessage());
				}
				
			}
		}	
		
		setVisible(true);
	}
	
	
	/**
	 * Called to handle Events from the User Interface
	 * @param e event
	 */
	
	public void actionPerformed(ActionEvent e) 
	{
		// check the button events
		
		if(e.getSource()==searchButton)
		{
			// if check-in interface is closed 
			if(this.CheckInClosed())
			{
				
				JOptionPane.showMessageDialog(null,
	    				"Check-in Interface is CLOSED ",
	    				"Inane warning",
	    			    JOptionPane.WARNING_MESSAGE);
				System.exit(0);
					
			}
			else {
				
				// get the entered value for booking reference and Lastname from user input
				
				String lastnamestring = this.lastnamein.getText().trim().toUpperCase();

				String BookingRefstring = this.BookingRefin.getText().trim().toUpperCase();
				
				// search if booking exist

				
				
				try {
					
					Booking searchbooking = this.bookinglist.findBooking(BookingRefstring, lastnamestring);
					// if check in exist rest the fields and launch the GUICheckInWindow
					reset();
					this.setVisible(false);
					GUICheckInWindow checkinFrame = GUICheckInWindow.getInstance();
					checkinFrame.update(searchbooking, this);
				}
				catch(BookingNotFoundException exception)
				{
					JOptionPane.showMessageDialog(null,
		    				"No booking found with Ref number:"+BookingRefstring+" and Last Name :"+lastnamestring+"\n"
		    				+ "Please enter a valid Booking !!!\n",
		    				
		    					"Inane error",
		    						JOptionPane.ERROR_MESSAGE);
						reset(); 
				}
			}
		}
	}
	
	/**
	 * Method to rest the last name and booking Reference text field after each click to search
	 */
	public void reset()
	{
		this.BookingRefin.setText(null);
		this.lastnamein.setText(null);
	}
	
	/**
	 * This method check if all Passenger have checked-in by comparing the size of bookings in the booking list and 
	 * a class counter in the booking which holds number of checked-in passenger
	 * @return
	 */
	public boolean CheckInClosed()
	{
		return bookinglist.GetNumberOfBookings() == Booking.counter; 
	}
}
