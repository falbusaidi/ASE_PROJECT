package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.DeskManager;
import Model.PassengerThread;
import View.SetSimulationTime;

public class Controller {

	private DeskManager deskModel;
	private PassengerThread passengerThread;
	private SetSimulationTime view;
	
	public Controller(SetSimulationTime Gui, DeskManager deskModel, PassengerThread passengerThread) {
		this.deskModel = deskModel;
		this.passengerThread = passengerThread;
		this.view = Gui; 
		view.addSetListener(new SetListener ());
	}
	
	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("hellow");
			deskModel.setDelay(view.getDeskDelay());
			passengerThread.setDelay(view.getPassengerThreadDelay());;
		}
	}
}
