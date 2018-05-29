package AllClasses;

import java.util.Iterator;
import java.util.Vector;

public class ShabbatEvent extends ElevatorEvent implements Beepable{
	private boolean value;
	
	public ShabbatEvent(int time, boolean value) {
		super(time);
		this.value = value;
	}


	@Override
	public void doEvent(Vector<Elevator> elevators) {
		Iterator<Elevator> iter = elevators.iterator();
		while (iter.hasNext()) {
			Elevator temp = iter.next();
			if (temp instanceof ShabbatElevator) {
				ShabbatElevator sElev = (ShabbatElevator)(temp);
				sElev.setbShabbatMode(value);
			}
		}
	}

	public boolean getValue() {
		return value;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (value ? 1231 : 1237);
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
		ShabbatEvent other = (ShabbatEvent) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return super.toString() + ", value=" + value;
	}


	@Override
	public String Beep() {
		return 	getClass().getSimpleName()+ " Beep Beep There should not be any beeping on Saturday, "
				+ "I must do it before closing the door";
	}

	
}
