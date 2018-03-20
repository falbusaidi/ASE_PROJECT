package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.DeskManager;
import Model.PassengerThread;
import View.AdminGUI;

public class Controller {

	private DeskManager deskModel;
	private PassengerThread passengerThread;
	private AdminGUI view;
	
	public Controller(AdminGUI Gui, DeskManager deskModel, PassengerThread passengerThread) {
		this.deskModel = deskModel;
		this.passengerThread = passengerThread;
		this.view = Gui; 
		deskModel.registerObserver(view);
		view.addSetListener(new SetListener ());
		view.addSetListener(new SetListenerDeskOpen ());
		view.addSetListener(new SetListenerDeskClose ());
		
	}
	
	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deskModel.setDelay(view.getDeskDelay());
			passengerThread.setDelay(view.getPassengerThreadDelay());
		}
	}
	
	public class SetListenerDeskOpen implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			int result =0; 
			int ID = view.getDeskID();
			if(ID!=0) {
				result = deskModel.openDeskByID(ID);
			}
			 
			
			if (result == 1) {
				JOptionPane.showMessageDialog(null, 
	    				 "Check-in time is out");
			}else if( result == -1)
			{
				JOptionPane.showMessageDialog(null, 
	    				 "Desk ID does not Exist, enter a valid ID");
			}
		}
	}
	
	public class SetListenerDeskClose implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result =0; 
			int ID = view.getDeskID();
			if(ID!=0) {
				result = deskModel.closeDeskByID(ID);
			}
			 
			if (result == 1) {
				JOptionPane.showMessageDialog(null, 
	    				 "Check-in time is out");
			}else if( result == -1)
			{
				JOptionPane.showMessageDialog(null, 
	    				 "Desk ID does not Exist, enter a valid ID");
			}
					
		}
	}
}
