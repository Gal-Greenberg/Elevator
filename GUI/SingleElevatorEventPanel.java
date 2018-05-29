package GUI;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import AllClasses.Elevator;
import AllClasses.ElevatorManager;

public abstract class SingleElevatorEventPanel extends ElevatorEventPanel {
	
	protected String[] floorNum;
	protected JComboBox<String> cmbId;
	protected JLabel lbElevator;
	
	protected Vector<Elevator> elevator;
	
	public SingleElevatorEventPanel(ElevatorManager manager, DefaultTableModel tableModel) {
		super(manager, tableModel);
		
		elevator = this.manager.getElevators();
		getFloorNum();
		
		lbElevator = new JLabel("Elevator ID:");
		cmbId = new JComboBox<String>(floorNum);
		cmbId.setEnabled(true);
		
		add(lbElevator);
		add(cmbId);
		
		theLayout.putConstraint(SpringLayout.WEST, lbElevator, DRAW_OFFSET, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.NORTH, lbElevator, DRAW_OFFSET+7, SpringLayout.SOUTH, lbTime);
		
		theLayout.putConstraint(SpringLayout.EAST, cmbId, -DRAW_OFFSET, SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.WEST, cmbId, DRAW_OFFSET*8, SpringLayout.EAST, lbElevator);
		theLayout.putConstraint(SpringLayout.NORTH, cmbId, DRAW_OFFSET, SpringLayout.SOUTH, textField);
	}
	
	public void getFloorNum() {
		floorNum = new String[elevator.size()];
		Iterator<Elevator> itr = elevator.iterator();
		for (int i = 0; i < floorNum.length; i++) {
			floorNum[i] = new Integer(itr.next().getId()).toString();
		}
	}
	
}
