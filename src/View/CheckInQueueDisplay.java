package View;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Interface.Observer;

import Model.CheckInQueue;


public class CheckInQueueDisplay extends JPanel implements Observer{
	
private CheckInQueue model;
private JTextArea QueueDetail; 
public CheckInQueueDisplay(CheckInQueue model) 
{
	this.setLayout(new FlowLayout());
	this.model=model;
	// register the display class as an observer of the model
	model.registerObserver(this);
	Setup(); 
	update(); 
}



public void Setup()
{
	QueueDetail = new JTextArea();
	QueueDetail.setLineWrap(true);
	QueueDetail.setWrapStyleWord(true);
	QueueDetail.setEditable(false);
	QueueDetail.setSize(300, 300);
	this.add(QueueDetail);
	
	
}

public void update()
{
	QueueDetail.setText(model.getQueueDetail());
}

}
