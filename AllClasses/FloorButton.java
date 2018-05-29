package AllClasses;

public class FloorButton implements Cloneable {
	public final static boolean START_PRESS = false;
	private int floorNumber;
	private boolean bPressed = START_PRESS;
	
	public FloorButton(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	public boolean isbPressed() {
		return bPressed;
	}

	public void setbPressed(boolean bPressed) {
		this.bPressed = bPressed;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	@Override
	public String toString() {
		return "FloorButton [floorNumber=" + floorNumber + ", bPressed=" + bPressed + "]";
	}

	@Override
	public FloorButton clone() throws CloneNotSupportedException {
		return (FloorButton)super.clone();
	}
	
		@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + floorNumber;
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
		FloorButton other = (FloorButton) obj;
		if (floorNumber != other.floorNumber)
			return false;
		return true;
	}


}
