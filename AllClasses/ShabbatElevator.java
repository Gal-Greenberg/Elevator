package AllClasses;


public class ShabbatElevator extends Elevator {

	private boolean bShabbatMode;
	
	public ShabbatElevator(int minFloor, int maxFloor, int currentFloor) throws Exception {
		super(minFloor, maxFloor, currentFloor);
		bShabbatMode = false;
	}

	public boolean isbShabbatMode() {
		return bShabbatMode;
	}

	public void setbShabbatMode(boolean bShabbatMode) {
		if (this.bShabbatMode == bShabbatMode)  // no change, do nothing
			return;
		this.bShabbatMode = bShabbatMode;
		if (bShabbatMode) {
			// Reset all buttons, make sure elevator is on the move
			thePanel.resetButtons();
			if (dir == Direction.Idle) {
				if (getCurrentFloor() == thePanel.getMaxFloor())
					dir = Direction.Down;
				else
					dir = Direction.Up;
			}
		} else {
			dir = Direction.Idle;
		}
	}

	
	
	@Override
	public void resetPanel() {
		super.resetPanel();
		setbShabbatMode(false);
	}

	@Override
	public int getTopToGo() throws Exception {
		if (bShabbatMode)
			return thePanel.getMaxFloor();
		return super.getTopToGo();
	}

	@Override
	public int getBottomToGo() throws Exception {
		if (bShabbatMode)
			return thePanel.getMinFloor();
		return super.getBottomToGo();
	}

	@Override
	public void buttonPressed(int num) throws Exception {
		// Only if not in Shabbat mode
		if (!bShabbatMode)
			super.buttonPressed(num);
	}

	@Override
	public String toString() {
		return super.toString() + ", bShabbatMode=" + bShabbatMode;
	}

}
