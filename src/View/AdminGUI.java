package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Controller.Controller.SetListener;
import Controller.Controller.SetListenerDeskOpen;
import Interface.Observer;
import Model.DeskManager;
import Model.PassengerThread;

/**
 * @author fahad
 * The purpose of this class is to provide a GUI interface to allow the user to :
 * 1. Alter the speed of the simulation 
 * 2.Control the opening and closing of Desk during the simulation
 *
 */
public class AdminGUI extends JFrame implements Observer{

		private JTabbedPane tabpane;
		private DeskManager deskModel;  // model to access the desk 
		private PassengerThread passengerModel; // model to access the passenger thread simulation
		
		// GUI component for setiing the speed of the simulation
		private JButton JBupdate; 
		private JTextField deskDelay_TF; 
		private JTextField passenger_TF; 
		
		//GUI component to open/close a desk
		private JTextField TF_OpenDesks; 
		private JTextField TF_DesksNumber;
		private JTextField TF_enterDesk; 
		private JTextField TF_closeDesk;
		private JButton JB_CloseDesk; 
		private JButton JB_OpenDesk; 
		
		
		/**
		 * Constructor 
		 * @param deskModel : represent the model to access the desk
		 * @param passengerModel  represent teh model to access the passengerThread class
		 */
		public AdminGUI(DeskManager deskModel, PassengerThread passengerModel) {
			tabpane = new JTabbedPane();
			this.passengerModel = passengerModel;
			this.deskModel= deskModel; 
			// create the first tab which contains the GUI to configure the simulation speed
			setSimulationSpeedsetup();
			//create the second tab which contain functionality to open and close the desks
			manageDeskSetup();
			// adding the tabpane to the Jframe
			this.add(tabpane); 
			this.setSize(500, 300);
			this.setTitle("Admin GUI");
		    this.setVisible(true);
		}
		
		/**
		 * method to setup the first tab which contains the functionalities to update the simulation speed for the desks
		 * and the speed to update passenger into the queue
		 */
		private void setSimulationSpeedsetup() {
			// TODO Auto-generated method stub
			JPanel SpeedTabPanel = new JPanel();
			SpeedTabPanel.setLayout(new BorderLayout());
			
			JPanel fieldsPanel = new JPanel();
			fieldsPanel.setLayout(new BorderLayout());
			
			JPanel filedPanel = new JPanel();
			filedPanel.setLayout(new GridLayout(2,2));
			
			filedPanel.add(new JLabel("Desk Delay Timer"));
			deskDelay_TF = new JTextField();
			deskDelay_TF.setEditable(true);
			deskDelay_TF.setText(Integer.toString(deskModel.getDelay()));
			filedPanel.add(deskDelay_TF);
			
			filedPanel.add(new JLabel("Passenger Delay Timer"));
			passenger_TF = new JTextField();
			passenger_TF.setEditable(true);
			passenger_TF.setText(Integer.toString(passengerModel.getDelay()));
			filedPanel.add(passenger_TF);
			fieldsPanel.add(filedPanel,BorderLayout.CENTER);
			
			JBupdate = new JButton("Update");
			JPanel buttonPanel = new JPanel(new FlowLayout());
			buttonPanel.add(JBupdate);
			fieldsPanel.add(buttonPanel, BorderLayout.SOUTH);
			
				
			tabpane.add("Speed Configuration",fieldsPanel); 
			
			
			
		}
		
		/**
		 * This method setup the second tab of the GUI which provides functionalities to 
		 * display the open/ and available desk 
		 * ability to open and close a desk provided the ID of the desk
		 */
		public void manageDeskSetup() {
			
			JPanel mainPanel = new JPanel(new BorderLayout()); 
			
			JPanel fieldsPanel = new JPanel();
			fieldsPanel.setLayout(new GridLayout(3,2));
			
			fieldsPanel.add(new JLabel ("Total Desks"));
			TF_DesksNumber= new JTextField();
			TF_DesksNumber.setEditable(false);
			TF_DesksNumber.setText(Integer.toString(deskModel.getNumberofDesks()));
			fieldsPanel.add(TF_DesksNumber);
			
			fieldsPanel.add(new JLabel ("Total Open Desks"));
			TF_OpenDesks= new JTextField();
			TF_OpenDesks.setEditable(false);
			TF_OpenDesks.setText(Integer.toString(deskModel.getDesksToOpen()));
			fieldsPanel.add(TF_OpenDesks);
			
			
			
			fieldsPanel.add(new JLabel ("Enter Desk number"));
			TF_enterDesk= new JTextField();
			TF_enterDesk.setEditable(true);
			fieldsPanel.add(TF_enterDesk);
									
			mainPanel.add(fieldsPanel, BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel(new FlowLayout());
			JB_OpenDesk = new JButton("Open Desk");
			buttonPanel.add(JB_OpenDesk);
			
			JB_CloseDesk = new JButton("Close Desk");
			buttonPanel.add(JB_CloseDesk);
			
			mainPanel.add(buttonPanel, BorderLayout.SOUTH);
			
			tabpane.add("Desks Configuration",mainPanel); 
		}
		
		
		/**
		 * Method to return the enter desk ID in order to open or close it 
		 * @return
		 */
		public int getDeskID()
		{
			int input = 0; 
			try {
				input = Integer.parseInt(TF_enterDesk.getText());
				
			}catch(NumberFormatException e ) {
				
				JOptionPane.showMessageDialog(null, 
	    				 "Enter a valid Desk ID");
			}
			return input; 
		}

		/**
		 * Method to set the listener to the GUI events to: 
		 * 1.update the simulation speed
		 * 2. open a desk
		 * 3. close a desk
		 * @param al represent the actionlistener class which will handle the event
		 */
		public void addSetListener(ActionListener al) {
			// Determine the action listener class and assign it to appropriate component
			if (al instanceof SetListener) {
				JBupdate.addActionListener(al);
			}else if(al instanceof SetListenerDeskOpen){
				JB_OpenDesk.addActionListener(al);
			} else {
				JB_CloseDesk.addActionListener(al);
			}
			
			
		}

		/**
		 * Method to get the delay entered by the user for the desk
		 * @return an integer value representing the delay in millisecond
		 */
		public int getDeskDelay()
		{
			int input = 0; 
			try {
				input = Integer.parseInt(deskDelay_TF.getText());
				
			}catch(NumberFormatException e ) {
				
				JOptionPane.showMessageDialog(null, 
	    				 "Enter a valid Desk ID");
			}
			return input; 
			
		}
		/**
		 * Method to get the delay entered by the user for adding passenger to the queue
		 * @return an integer value representing the delay in millisecond
		 */
		public int getPassengerThreadDelay()
		{
			int input = 0; 
			try {
				input = Integer.parseInt(passenger_TF.getText());
				
			}catch(NumberFormatException e ) {
				
				JOptionPane.showMessageDialog(null, 
	    				 "Enter a valid Desk ID");
			}
			return input; 
			
		}
		
		/* (non-Javadoc)
		 * @see Interface.Observer#update()
		 * used to update the observed value of open desks
		 */
		public void update()
		{
			TF_OpenDesks.setText(Integer.toString(deskModel.getDesksToOpen()));
		}
}
