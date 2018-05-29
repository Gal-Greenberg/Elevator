package AllClasses;

import java.util.Vector;

public class ResetEvent extends SingleElevatorEvent implements Beepable {

	
	public ResetEvent(int time, int elevatorId) {
		super(time,elevatorId);
	}
	
	@Override
	public void doEvent(Vector<Elevator> elevators) throws Exception{
		Elevator elv = getElevatorForEvent(elevators);
		elv.resetPanel();
	}

	@Override
	public String Beep() {
		return getClass().getSimpleName()+" Beeping!!!! Everything is reset now";
	}

	
}
