package GUI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import AllClasses.*;

public class TheMainPanel extends JPanel {
	
	protected final static int DRAW_OFFSET = 5;
	
	private final static int WIDTH_ADD = 300;
	private final static int HEIGHT_ADD = 40;
	
	private final static int WIDTH_TICKING = 400;
	private final static int HEIGHT_TICKING = 40;
	
	private final static int WIDTH_TABLE = 500;
	private final static int HEIGHT_TABLE = 200;
	private final static String[] option = {"Press Floor Button", "Reset all Buttons", "Toggle Shabbat Mode"};
	
	private ElevatorManager manager;
	private SpringLayout theLayout;
	
	private JPanel pLeft;
	private JPanel pRight;
	private JPanel pCardsAll;

	private JPanel pTable;
	private JPanel pSelect;
	protected JPanel pCards;
	private JPanel pAdd;
	
	private JPanel pStart;
	private ElevatorsPanel pElevators;
	
	private JTable tableElevatorEvent;
	private DefaultTableModel tableModel;
	private JScrollPane scrollerTable;
	
	private JRadioButton[] radioArr;
	private ElevatorEventPanel[] panelArr = new ElevatorEventPanel[3];
	private ElevatorEventPanel theChosenPanel;
	
	private boolean isTicking;
	private Timer theTimer;
	private int count;
	
	private JButton ticking;
	private JButton addButton;
	
	public TheMainPanel(ElevatorManager manager) throws Exception {
		this.manager = manager;
		theLayout = new SpringLayout();
		setLayout(new BorderLayout());

		panelArr[0] = new PressEventPanel(manager, tableModel);
		panelArr[1] = new ResetEventPanel(manager, tableModel);
		panelArr[2] = new ShabbatEventPanel(manager, tableModel);
		
		pLeft = new JPanel(new BorderLayout());
		pRight = new JPanel(new BorderLayout());
		pCardsAll = new JPanel(new BorderLayout());
		
		add(pRight, BorderLayout.EAST);
		add(pLeft, BorderLayout.WEST);
		pLeft.add(pCardsAll, BorderLayout.EAST);
		
		pTable = tableElevatorEvent();
		pSelect = select();
		pCards = createCardPanel();
		pAdd = addButton();
		
		pStart = TickingButton();
		pElevators = new ElevatorsPanel(count, manager);
		
		pLeft.add(pTable, BorderLayout.NORTH);
		pLeft.add(pSelect, BorderLayout.WEST);
		
		pCardsAll.add(pCards, BorderLayout.NORTH);
		pCardsAll.add(pAdd, BorderLayout.SOUTH);
		
		pRight.add(pStart, BorderLayout.SOUTH);
		pRight.add(pElevators, BorderLayout.NORTH);
		
		theChosenPanel = panelArr[0];
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		pElevators.paintComponent(g);
		updateTable();
	}
	
	public JPanel tableElevatorEvent() {
		JPanel table = new JPanel();
		tableModel = new DefaultTableModel();
		tableElevatorEvent = new JTable(tableModel);
		
		tableElevatorEvent.setPreferredScrollableViewportSize(new Dimension(WIDTH_TABLE, HEIGHT_TABLE));
		tableModel.setColumnIdentifiers(new Object[] {"Time", "Type", "ID", "Value"});
		scrollerTable = new JScrollPane(tableElevatorEvent);
		scrollerTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		table.add(scrollerTable);
		return table;
	}
	
	public JPanel select() {
		JPanel select = new JPanel(new GridLayout(0, 1));
		radioArr = new JRadioButton[3];
		ButtonGroup group = new ButtonGroup();
		select.add(new JLabel("Select Event Type:"));
		for(int i = 0; i < radioArr.length; i++){
			radioArr[i] = new JRadioButton(option[i]);
			select.add(radioArr[i]);
			group.add(radioArr[i]);
			radioArr[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					changePanels(e.getSource());
				}
			});
		}
		radioArr[0].setSelected(true);
		return select;
	}
	
	protected void changePanels(Object source) {
		CardLayout layout = (CardLayout)pCards.getLayout();
		for (int i = 0; i < radioArr.length; i++) {
			if (radioArr[i] == source) {
				layout.show(pCards, radioArr[i].getActionCommand());
				theChosenPanel = panelArr[i];
			}
		}
	}
	
	public JPanel createCardPanel() {
		JPanel panel = new JPanel(new CardLayout());
		for (int i = 0; i < panelArr.length; i++) {
			panel.add(panelArr[i], option[i]);
		}
		return panel;
	}
	
	public void updateTable() {
		Vector<ElevatorEvent> elevator = manager.getEventsVec();
		Iterator<ElevatorEvent> itr = elevator.iterator();
		if (tableModel.getRowCount() != 0) {
			for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
				tableModel.removeRow(i);;
			}
		}
		while (itr.hasNext()) {
			ElevatorEvent event = itr.next();
			Object[] row = new Object[4];
			row[0] = event.getTime();
			row[1] = event.getClass().getSimpleName();
			if (event instanceof ShabbatEvent) {
				row[2] = "N/A";
				ShabbatEvent e = (ShabbatEvent)event;
				row[3] = e.getValue();
			} else {
				SingleElevatorEvent e = (SingleElevatorEvent)event;
				row[2] = e.getElevatorId();
			}
			if (event instanceof PressEvent) {
				PressEvent e = (PressEvent)event;
				row[3] = e.getFloor();
			}
			tableModel.addRow(row);
		}
	}
	
	public JPanel addButton() {
		JPanel pAdd = new JPanel(theLayout);
		pAdd.setPreferredSize(new Dimension(WIDTH_ADD, HEIGHT_ADD));
		
		addButton = new JButton("add Event");
		pAdd.add(addButton);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int time = Integer.parseInt(theChosenPanel.textField.getText());
					theChosenPanel.adding(time);
					updateTable();
					theChosenPanel.textField.setText("");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter valid time");
				}
			}
		});
		theLayout.putConstraint(SpringLayout.SOUTH, addButton, -DRAW_OFFSET, SpringLayout.SOUTH, pAdd);
		theLayout.putConstraint(SpringLayout.WEST, addButton, DRAW_OFFSET, SpringLayout.WEST, pSelect);
		theLayout.putConstraint(SpringLayout.EAST, addButton, -DRAW_OFFSET, SpringLayout.EAST, pAdd);
		return pAdd;
	}
	
	public JPanel TickingButton() {
		JPanel pTick = new JPanel(theLayout);
		pTick.setPreferredSize(new Dimension(WIDTH_TICKING, HEIGHT_TICKING));
		
		ticking = new JButton("Start Ticking");
		pTick.add(ticking);
		
		ticking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isTicking) {
					startTicking();
				} else {
					stopTicking();
				}
			}
		});
		
		theLayout.putConstraint(SpringLayout.SOUTH, ticking, -DRAW_OFFSET, SpringLayout.SOUTH, pTick);
		theLayout.putConstraint(SpringLayout.EAST, ticking, -DRAW_OFFSET, SpringLayout.EAST, pTick);
		theLayout.putConstraint(SpringLayout.WEST, ticking, DRAW_OFFSET, SpringLayout.WEST, pTick);
		return pTick;
	}
	
	public void startTicking() {
		isTicking = true;
		ticking.setText("Stop Ticking");
		ticking.setActionCommand("Stop Ticking");
		theTimer = new Timer();
		theTimer.schedule(new ArcsTimer(), 0, 1000);
	}

	public void stopTicking() {
		isTicking = false;
		theTimer.cancel();
		ticking.setText("Start Ticking");
		ticking.setActionCommand("Start Ticking");
		validate();
		repaint();
	}
	
	class ArcsTimer extends TimerTask {
		@Override
		public void run() {
			count++;
			pElevators.setCount(count);
			manager.tick();
			validate();
			repaint();
		}
	}
	
}
