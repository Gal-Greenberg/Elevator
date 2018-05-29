package AllClasses;

import java.util.Vector;

public abstract class ElevatorEvent implements Comparable<ElevatorEvent> {

	private int time;

	public ElevatorEvent(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

	public abstract void doEvent(Vector<Elevator> elevators) throws Exception;
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": time=" + time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + time;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElevatorEvent other = (ElevatorEvent) obj;
		return time == other.time;
	}
	
	@Override
	public int compareTo(ElevatorEvent other) {
		if (time > other.time) {
			return 1;
		} else if (time < other.time) {
			return -1;
		} else
			return 0;
	}


}
