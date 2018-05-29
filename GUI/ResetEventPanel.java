package GUI;
import javax.swing.table.DefaultTableModel;
import AllClasses.ElevatorManager;
import AllClasses.ResetEvent;

public class ResetEventPanel extends SingleElevatorEventPanel {
	
	public ResetEventPanel(ElevatorManager manager, DefaultTableModel tableModel) {
		super(manager, tableModel);
	}
	
	public void adding(int time) throws Exception {
		manager.addEvent(new ResetEvent(time, Integer.parseInt(floorNum[cmbId.getSelectedIndex()])));
	}
	
}
