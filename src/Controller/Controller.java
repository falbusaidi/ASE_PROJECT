package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.DeskManager;
import Model.PassengerThread;
import View.AdminGUI;

/**
 * @author fahad
 * This class acts as a controller between the view : AdminGUI and model ( passengerThread and DeskManager)
 * its proposed it to assign register the view as observer to the model and
 * handle the event between the view and model 
 *
 */
public class Controller {

	private DeskManager deskModel; // present the Desk model to control the opening and closing of desk
	private PassengerThread passengerThread;// represent the thread to add passenger to the queue
	private AdminGUI view; // represent the GUI used to update simulation speed and open/close desk
	
	/**
	 * Constructor 
	 * @param Gui : represent the view as object of AdminGUI
	 * @param deskModel: represent the model to control the Desk
	 * @param passengerThread: represent the model to control the passengerThread
	 */
	public Controller(AdminGUI Gui, DeskManager deskModel, PassengerThread passengerThread) {
		this.deskModel = deskModel;
		this.passengerThread = passengerThread;
		this.view = Gui; 
		deskModel.registerObserver(view);
		view.addSetListener(new SetListener ());		
	}
	
	/**
	 * A listener class to handle a specific event of updating the simulation delays
	 * @author Fahad
	 *
	 */
	public class SetListener implements ActionListener {
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 * This method handles the event:
		 * 1. when user click up of Simulation speed
		 * 2.  handle event of opening a Desk
		 * 3.  handle event of closing a Desk
		 */
		public void actionPerformed(ActionEvent e) {
			int result =0;
			int ID=0; 
			if (e.getActionCommand()=="Update") {
				
				// below code update the delys on the desk thread to process the passengers
				deskModel.setDelay(view.getDeskDelay());
				// below code update the delays on the PassengerThread to simulate passenger arrival to queue
				passengerThread.setDelay(view.getPassengerThreadDelay());
				
			}else if (e.getActionCommand()=="Open") { // handle event of opening a Desk
				
				ID = view.getDeskID();
				// if ID is not blank means ID exist and then call the model method to open the desk
				if(ID!=0) {
					result = deskModel.openDeskByID(ID);
				}
				 
				// if result is 1 means checking time is over and can't open a desk
				if (result == 1) {
					JOptionPane.showMessageDialog(null, 
		    				 "Check-in time is over");
				//if result is -1 then provided ID is not correct
				}else if( result == -1)
				{
					JOptionPane.showMessageDialog(null, 
		    				 "Desk ID does not Exist, enter a valid ID");
				}
			}
			else if (e.getActionCommand()=="Close") {//  handle event of closing Desk
				 
				ID = view.getDeskID();
				// if ID is not blank means ID exist and then call the model method to open the desk
				if(ID!=0) {
					result = deskModel.closeDeskByID(ID);
				}
				// if result is 1 means checking time is over and can't open a desk
				if (result == 1) {
					JOptionPane.showMessageDialog(null, 
		    				 "Check-in time is over");
				//if result is -1 then provided ID is not correct
				}else if( result == -1)
				{
					JOptionPane.showMessageDialog(null, 
		    				 "Desk ID does not Exist, enter a valid ID");
				}
			}
			
		}
	}
	
}
