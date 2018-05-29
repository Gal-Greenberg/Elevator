package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import AllClasses.ElevatorManager;

public class TheMainFrame extends JFrame {
	
	public TheMainFrame(ElevatorManager manager) throws Exception {
		setTitle("Elevators");
		JPanel mainPanel = new TheMainPanel(manager);
		add(mainPanel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
}
