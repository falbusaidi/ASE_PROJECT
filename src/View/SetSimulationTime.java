package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.DeskManager;
import Model.PassengerThread;

public class SetSimulationTime extends JFrame{
	
private DeskManager deskModel;
private PassengerThread passengerThread;
private JButton JBupdate; 
private JTextField deskDelay_TF; 
private JTextField passenger_TF; 

public SetSimulationTime(DeskManager deskModel, PassengerThread passengerThread) {
	 this.deskModel = deskModel; 
	 this.passengerThread= passengerThread;
	 
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 setLayout(new BorderLayout());
	 setup(); 
	 this.setSize(300, 150);
	 this.setTitle("GUI Simulation Configuration");
	 this.setVisible(true);

}

private void setup() {
	// TODO Auto-generated method stub
	JPanel fieldsPanel = new JPanel();
	fieldsPanel.setLayout(new BorderLayout());
	
	JBupdate = new JButton("Update");
	
	deskDelay_TF = new JTextField();
	deskDelay_TF.setEditable(true);
	deskDelay_TF.setText(Integer.toString(deskModel.getDelay()));
	
	passenger_TF = new JTextField();
	passenger_TF.setEditable(true);
	passenger_TF.setText(Integer.toString(passengerThread.getDelay()));
	
	JPanel filedPanel = new JPanel();
	filedPanel.setLayout(new GridLayout(2,2));
	
	filedPanel.add(new JLabel("Desk Delay Timer"));
	filedPanel.add(deskDelay_TF);
	filedPanel.add(new JLabel("Passenger Delay Timer"));
	filedPanel.add(passenger_TF);
	
	fieldsPanel.add(filedPanel,BorderLayout.CENTER);
	
	
	this.add(fieldsPanel, BorderLayout.NORTH); 
	JPanel buttonPanel = new JPanel(new FlowLayout());
	buttonPanel.add(JBupdate);
	this.add(buttonPanel,BorderLayout.SOUTH);
	
	
	
}

public void addSetListener(ActionListener al) {
	
	JBupdate.addActionListener(al);
}

public int getDeskDelay()
{
	return Integer.parseInt(deskDelay_TF.getText());
}

public int getPassengerThreadDelay()
{
	return Integer.parseInt(passenger_TF.getText());
}

}
