package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import AllClasses.ElevatorEvent;
import AllClasses.ElevatorManager;
import AllClasses.ShabbatEvent;

public class ShabbatEventPanel extends ElevatorEventPanel {
	
	private JCheckBox chkEnableMode;
	
	public ShabbatEventPanel(ElevatorManager manager, DefaultTableModel tableModel) {
		super(manager, tableModel);
		chkEnableMode = new JCheckBox("Enable Shabbat mode");
		add(chkEnableMode);
		
		theLayout.putConstraint(SpringLayout.WEST, chkEnableMode, DRAW_OFFSET, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.EAST, chkEnableMode, DRAW_OFFSET, SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.NORTH, chkEnableMode, DRAW_OFFSET+DRAW_OFFSET, SpringLayout.SOUTH, lbTime);
	}
	
	public void adding(int time) throws Exception {
		manager.addEvent(new ShabbatEvent(time, chkEnableMode.isSelected()));
	}
	
}
