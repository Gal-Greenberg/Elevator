package GUI;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import AllClasses.*;

public class PressEventPanel extends SingleElevatorEventPanel {
	
	private static int min;
	private static int max;
	private static final int FPS_INIT = 0; 
	
	private JLabel lbFloor;
	private JSlider sliderFloor;
	
	public PressEventPanel(ElevatorManager manager, DefaultTableModel tableModel) throws Exception {
		super(manager, tableModel);
		
		Iterator<Elevator> itr = elevator.iterator();
		while (itr.hasNext()) {
			Elevator e = itr.next();
			if (e.getThePanel().getMinFloor() <= min) {
				min = e.getThePanel().getMinFloor();
			}
			if (e.getThePanel().getMaxFloor() >= max) {
				max = e.getThePanel().getMaxFloor();
			}
		}
		
		lbFloor = new JLabel("Floor number");
		sliderFloor = new JSlider(JSlider.HORIZONTAL, min, max, FPS_INIT);
		sliderFloor.setMajorTickSpacing(1);
		sliderFloor.setPaintTicks(true);
		sliderFloor.setPaintLabels(true);
		
		add(lbFloor);
		add(sliderFloor);
		
		theLayout.putConstraint(SpringLayout.WEST, lbFloor, DRAW_OFFSET*23, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.NORTH, lbFloor, DRAW_OFFSET*DRAW_OFFSET, SpringLayout.SOUTH, cmbId);
		
		theLayout.putConstraint(SpringLayout.WEST, sliderFloor, DRAW_OFFSET, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.EAST, sliderFloor, -DRAW_OFFSET, SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.NORTH, sliderFloor, DRAW_OFFSET, SpringLayout.SOUTH, lbFloor);
	}
	
	public void adding(int time) throws Exception {
		manager.addEvent(new PressEvent(time, Integer.parseInt(floorNum[cmbId.getSelectedIndex()]), sliderFloor.getValue()));
	}
	
}
