package AllClasses;

public class Elevator implements Cloneable, Comparable<Elevator> {
	enum Direction {Idle, Down, Up}
	public final static int START_ID = 1;
	public final static Direction START_DIR = Direction.Idle; 
	
	private static int idGen = START_ID;
	private int id;
	private int currentFloor;
	protected Direction dir = START_DIR;
	protected ButtonPanel thePanel;
	
	public Elevator(int minFloor, int maxFloor, int currentFloor) throws Exception {
		thePanel = new ButtonPanel(minFloor, maxFloor);
		if (!thePanel.testFloorInRange(currentFloor)) {
			throw new Exception("Current Floor must be within floor range");
		}
		this.currentFloor = currentFloor;		
		id = idGen++;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}

	public ButtonPanel getThePanel() {
		return thePanel;
	}

	public int getId() {
		return id;
	}
	
	public int getTopToGo() throws Exception {
		for (int i = thePanel.getMaxFloor(); i > currentFloor; i--) {
			if (thePanel.getButtonState(i))
				return i;
		}
		return currentFloor;
	}

	public int getBottomToGo() throws Exception {
		for (int i = thePanel.getMinFloor(); i < currentFloor; i++) {
			if (thePanel.getButtonState(i))
				return i;
		}
		return currentFloor;
	}

	public void buttonPressed(int num) throws Exception {
		// Illegal button
		if (!thePanel.testFloorInRange(num)) {
			throw new Exception("Button " + num + " not in elevator range");
		}
		// Current floor pressed
		if (num == currentFloor)
			return;
		// Already pressed
		if (thePanel.getButtonState(num))
			return;
		// Otherwise - press button
		thePanel.setButtonState(num, true);
		
		// Idle elevator should start moving
		if (dir == Direction.Idle) {
			if (num > currentFloor)
				dir = Direction.Up;
			else
				dir = Direction.Down;
		}
		return;
	}
	
	public void updateState() {
		try {
			switch (dir) {
			case Up:
				currentFloor++;
				if (currentFloor == getTopToGo()) {
					if (currentFloor == getBottomToGo())
						dir = Direction.Idle;
					else
						dir = Direction.Down;
				}
				break;
			case Down:
				currentFloor--;
				if (currentFloor == getBottomToGo()) {
					if (currentFloor == getTopToGo())
						dir = Direction.Idle;
					else
						dir = Direction.Up;
				}
				break;
			default:
				// Idle - do nothing
			}
			// turn off button (if on)
			thePanel.setButtonState(currentFloor, false);
		} catch (Exception e) {
			// This should never happen
			throw new AssertionError();
		}
	}

	public void resetPanel() {
		thePanel.resetButtons();
		dir = Direction.Idle;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": id=" + id + ", currentFloor=" + currentFloor + ", dir=" + dir + ", thePanel=" + thePanel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Elevator other = (Elevator) obj;
		return id == other.id;
	}

	@Override
	public Elevator clone() throws CloneNotSupportedException {
		Elevator result = (Elevator)super.clone();
		result.thePanel = thePanel.clone();
		return result;
	}

	@Override
	public int compareTo(Elevator other) {
		if (id > other.id) {
			return 1;
		} else if (id < other.id) {
			return -1;
		} else
			return 0;
	}

	
}
