package GUI;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import AllClasses.ElevatorEvent;
import AllClasses.ElevatorManager;
import AllClasses.PressEvent;
import AllClasses.ShabbatEvent;
import AllClasses.SingleElevatorEvent;

public abstract class ElevatorEventPanel extends JPanel{
	
	private final static int WIDTH = 300;
	private final static int HEIGHT = 160;
	protected final static int DRAW_OFFSET = 5;
	
	protected ElevatorManager manager;
	protected DefaultTableModel tableModel;
	
	protected JLabel lbTime;
	protected JTextField textField;
	protected SpringLayout theLayout;
	
	public abstract void adding(int time) throws Exception;
	
	public ElevatorEventPanel(ElevatorManager manager, DefaultTableModel tableModel) {
		this.manager = manager;
		this.tableModel = tableModel;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		theLayout = new SpringLayout();
		setLayout(theLayout);
		
		lbTime = new JLabel("Time to dispatch:");
		textField = new JTextField();
		
		add(lbTime);
		add(textField);
		theLayout.putConstraint(SpringLayout.WEST, lbTime, DRAW_OFFSET, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.NORTH, lbTime, DRAW_OFFSET, SpringLayout.NORTH, this);
		
		theLayout.putConstraint(SpringLayout.EAST, textField, -DRAW_OFFSET, SpringLayout.EAST, this);
		theLayout.putConstraint(SpringLayout.NORTH, textField, DRAW_OFFSET, SpringLayout.NORTH, this);
		theLayout.putConstraint(SpringLayout.WEST, textField, DRAW_OFFSET, SpringLayout.EAST, lbTime);
		
	}
	
}
