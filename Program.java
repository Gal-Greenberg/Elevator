import AllClasses.Address;
import AllClasses.Elevator;
import AllClasses.ElevatorEvent;
import AllClasses.ElevatorManager;
import AllClasses.PressEvent;
import AllClasses.ResetEvent;
import AllClasses.ShabbatElevator;
import AllClasses.ShabbatEvent;
import GUI.TheMainFrame;

public class Program {

	public static void main(String[] args) {
		try {
			Address addr = new Address("Hashalom", 50, "Tel-Aviv");
			ElevatorManager manager = new ElevatorManager(addr);
			
			Elevator elev1 = new Elevator(0, 5, 0);
			Elevator elev2 = new Elevator(0, 5, 1);
			ShabbatElevator elev3 = new ShabbatElevator(0, 5, 2);
			
			manager.addElevator(elev1);
			manager.addElevator(elev2);
			manager.addElevator(elev3);
			
			ElevatorEvent event1 = new PressEvent(1, 1, 4);
			ElevatorEvent event2 = new PressEvent(1, 3, 2);
			ElevatorEvent event3 = new PressEvent(1, 3, 3);
			ElevatorEvent event4 = new PressEvent(2, 1, 2);
			ElevatorEvent event5 = new PressEvent(2, 2, 5);
			ElevatorEvent event6 = new PressEvent(4, 2, 0);
			ElevatorEvent event7 = new ResetEvent(7, 2);
			ElevatorEvent event8 = new ShabbatEvent(1, true);
			ElevatorEvent event9 = new ShabbatEvent(9, false);
			
			manager.addEvent(event1);
			manager.addEvent(event2);
			manager.addEvent(event3);
			manager.addEvent(event4);
			manager.addEvent(event5);
			manager.addEvent(event6);
			manager.addEvent(event7);
			manager.addEvent(event8);
			manager.addEvent(event9);
			
			new TheMainFrame(manager);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
