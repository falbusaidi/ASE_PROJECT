package Application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class to hold the Jframe used to display the window for Checking fields
 * @author fahad
 *
 */
public class GUICheckInWindow extends JFrame implements ActionListener 
{
	
	private static GUICheckInWindow instance; 
	private Booking booking; 
	private GUIMainWindow mainWindow; 
	
	// instance variables for the Checking Panel
	private JTextField TF_Lastname;
	private JTextField TF_firstname;
	private JTextField TF_bookRef;
	private JTextField TF_weight;
	private JTextField TF_height; 
	private JTextField TF_depth; 
	private JTextField TF_width;
	private JTextField TF_excessFees; 
	private JButton JB_CheckIn; 
	private JButton JB_cancel; 
	private JPanel CheckingPanel;
	
	private GUICheckInWindow()
	{
		this.setTitle("Check-in System");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		SetupCheckInPanel();
		this.setSize(350, 250);
		//this.pack();
	}
	
	/**
	 * Method which is called to updated the Textfield with booking information  
	 * @param object a reference to the booking require to check in 
	 * @param mainWindow : a reference to the main GUI window which need to be called after the check-in is completed
	 */
	public void update(Booking object, GUIMainWindow mainWindow)
	{
		// display the booking ref, last and first name 
		this.booking = object; 
		this.mainWindow = mainWindow; 
		this.TF_Lastname.setText(this.booking.GetPassenger().GetLastName());
		this.TF_bookRef.setText(booking.GetBookingRef());
		this.TF_firstname.setText(booking.GetPassenger().GetFirstName());
		
		// if passenger already checked-in then display information and disable input fields
		if(booking.GetcheckInStatus())
		{
			this.TF_weight.setText(Double.toString(booking.GetWeight()));
			TF_weight.setEditable(false);
			this.TF_height.setText(Double.toString(booking.getHeight()));
			TF_height.setEditable(false);
			this.TF_depth.setText(Double.toString(booking.getDepth()));
			TF_depth.setEditable(false);
			this.TF_width.setText(Double.toString(booking.getWidth()));
			TF_width.setEditable(false);
			TF_excessFees.setText(Double.toString(booking.getExcessFees()));
		}
		else
		{
			// if passenger didnt check-in allow input for the weigh, and volume
			TF_weight.setEditable(true);
			TF_height.setEditable(true);
			TF_depth.setEditable(true);
			TF_width.setEditable(true);
		}
		run(); 
	}
	/**
	 * The GUI Run method which sets the GUI to Visible
	 */
	public void run()
	{
		this.setVisible(true);
	}
	
	/* Handles the events register by the class
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) 
	{
		// check the button events
				if (e.getSource() == JB_CheckIn) { 
					
						// check if passenger already checked-in
						if (!booking.GetcheckInStatus()) {
							
							// get the passenger weight and volume 
							try {
								double weight = Double.valueOf(TF_weight.getText());
								double height = Double.valueOf(this.TF_height.getText());
								double width  = Double.valueOf(this.TF_width.getText());
								double depth  = Double.valueOf(this.TF_depth.getText());
							
								
								// set the check-in status 
								booking.CheckIn(weight, height,width,depth); 
							
								// get the excess fees and display it 
								TF_excessFees.setText(Double.toString(booking.getExcessFees()));
								// add the booking into the checkinlist 
								CheckedInList.getInstance().add(booking); 
								JOptionPane.showMessageDialog(null, 
					    				 "Congratulation Check-in Completed, please click ok to print your boarding Pass");
								this.setVisible(false);
								reset(); 
								mainWindow.run();
								
							}catch(NumberFormatException excep) {
								JOptionPane.showMessageDialog(null, 
					    				 "Please Enter a a number for weight, height, width, and depth" ,"Inane error",
					    				    JOptionPane.ERROR_MESSAGE);
								System.out.print(excep.getMessage());
							}
							
						}
						else {
							JOptionPane.showMessageDialog(null, 
				    				 "Passenger Already Checked-in", "Inane warning",
				    				    JOptionPane.WARNING_MESSAGE);
				    				 		
						}
						
				} 
				if (e.getSource() == JB_cancel)
				{
					this.setVisible(false);
					reset(); 
					mainWindow.run();
				}
					
	}
	
	

	/**
	 * Return a Singelton object for the class
	 * @return an single  instance of Class GUICheckInWindow
	 */
	public static GUICheckInWindow getInstance()
	{
		if (instance == null)
		{
			instance = new GUICheckInWindow();
		}
		return instance; 
	}
	
		
	/**
	 * This method should setup the required components to allow passenger to enter weigh, volume, and display the booking ref, lastname, firstname, and 
	 * excess fees. Also, should include a button to perform check-in. 
	 * This should be in the Center of the main Jframe. YOu can use a gridlayout for the Jpanel
	 */
	 
	private void SetupCheckInPanel() {
		// TODO Auto-generated method stub
		CheckingPanel = new JPanel();
		CheckingPanel.setLayout(new BorderLayout());
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(8,2));
		
		TF_Lastname = new JTextField(10);
		TF_Lastname.setEditable(false);
		
		TF_firstname = new JTextField(10);
		TF_firstname.setEditable(false);
		
		TF_bookRef = new JTextField(6);
		TF_bookRef.setEditable(false);
		
		TF_weight = new JTextField(4);
		TF_height = new JTextField(4);
		TF_depth = new JTextField(4);
		TF_width = new JTextField(4);
		TF_excessFees = new JTextField(4);
		TF_excessFees.setEditable(false);
		
		JB_CheckIn = new JButton("Confirm");
		JB_cancel = new JButton("Close");
		JB_CheckIn.addActionListener(this);
		JB_cancel.addActionListener(this);
		
		inputPanel.add(new JLabel("Last Name"));
		inputPanel.add(TF_Lastname); 
		
		inputPanel.add(new JLabel("First Name"));
		inputPanel.add(TF_firstname); 
		
		inputPanel.add(new JLabel("Booking Ref"));
		inputPanel.add(TF_bookRef); 
		
		inputPanel.add(new JLabel("Baggage Weight"));
		inputPanel.add(TF_weight); 
		
		inputPanel.add(new JLabel("Baggage Height"));
		inputPanel.add(TF_height); 
		
		inputPanel.add(new JLabel("Baggage Depth"));
		inputPanel.add(TF_depth); 
		
		inputPanel.add(new JLabel("Baggage Width"));
		inputPanel.add(TF_width);
		
		inputPanel.add(new JLabel("Excess Fees"));
		inputPanel.add(TF_excessFees);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		buttonPanel.add(JB_CheckIn);
		buttonPanel.add(JB_cancel);
		//buttonPanel.add(JB_CheckIn,BorderLayout.EAST);
		
		CheckingPanel.add(inputPanel,BorderLayout.NORTH);
		CheckingPanel.add(buttonPanel,BorderLayout.SOUTH);
		this.add(CheckingPanel,BorderLayout.CENTER); 
		
	}
	
	/**
	 * This method is used to reset the text fields after a successful check in , or a cancel button is clicked
	 */
	private void reset() {
		// TODO Auto-generated method stub
		this.TF_bookRef.setText(null);
		this.TF_Lastname.setText(null);
		this.TF_firstname.setText(null);
		this.TF_weight.setText(null);
		this.TF_height.setText(null);
		this.TF_depth.setText(null);
		this.TF_width.setText(null);
		this.TF_excessFees.setText(null);
		booking = null; 
		
	}
}
