package AllClasses;

import java.util.Vector;

public class PressEvent extends SingleElevatorEvent{
	
	private int floor;
	
	public PressEvent(int time, int elevatorId, int floor) {
		super(time,elevatorId);
		this.floor = floor;
	}

	public int getFloor() {
		return floor;
	}
	
	@Override
	public void doEvent(Vector<Elevator> elevators) throws Exception{
		Elevator elv = getElevatorForEvent(elevators);
		elv.buttonPressed(floor);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", floor=" + floor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + floor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PressEvent other = (PressEvent) obj;
		return floor == other.floor;
	}
	
	
}
