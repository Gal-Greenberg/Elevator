package AllClasses;

import java.util.Iterator;
import java.util.Vector;

public abstract class SingleElevatorEvent extends ElevatorEvent {
	private int elevatorId;
	
	public SingleElevatorEvent(int time, int elevatorId) {
		super(time);
		this.elevatorId = elevatorId;
	}
	
	public Elevator GetElevatorById(Vector<Elevator> elevators){
		Iterator<Elevator> iter = elevators.iterator();
		while (iter.hasNext()) {
			Elevator temp = iter.next();
			if (temp.getId() == elevatorId)
				return temp;
		}
		return null;
	}
	
	public Elevator getElevatorForEvent(Vector<Elevator> elevators) throws Exception{
		Elevator elv = GetElevatorById(elevators);
		if(elv == null)
			throw new Exception("No elevetor with id:"+getElevatorId());
		return elv;
	}

	
	
	public int getElevatorId() {
		return elevatorId;
	}

	
	
	@Override
	public String toString() {
		return super.toString() + ", elevatorId=" + elevatorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + elevatorId;
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
		SingleElevatorEvent other = (SingleElevatorEvent) obj;
		if (elevatorId != other.elevatorId)
			return false;
		return true;
	}
	
	
	
}
