package AllClasses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ButtonPanel implements Cloneable {
	private int minFloor;
	private int maxFloor;
	private Map<Integer, FloorButton> buttons = new HashMap<Integer, FloorButton>();
	
	public ButtonPanel(int minFloor, int maxFloor) throws Exception{
		if (minFloor > maxFloor) {
			throw new Exception("floor range illegal, bottom floor cannot be larger than top floor");
		}
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		
		for (int i = minFloor; i <= maxFloor; i++)
			buttons.put(i, new FloorButton(i));
	}

	public int getMinFloor() {
		return minFloor;
	}

	public int getMaxFloor() {
		return maxFloor;
	}

	public FloorButton getButton(int floorNum)throws Exception {
		FloorButton bt =  buttons.get(floorNum);
		 if(bt == null)
			 throw new Exception("Floor number must be within floor range");
		 return bt;
	}
			
	public boolean testFloorInRange(int floorNum) {
		return (floorNum >= minFloor && floorNum <= maxFloor);
	}
	
	public void setButtonState(int num, boolean value) throws Exception {
		FloorButton fb = getButton(num);// This will throw an exception if floor is not in range
		fb.setbPressed(value);
	}
	
	public boolean getButtonState(int num) throws Exception {
		FloorButton fb = getButton(num);
		return fb.isbPressed();
	}
	
	public void resetButtons() {
		Iterator<FloorButton> iter = buttons.values().iterator();
		while (iter.hasNext()) {
			FloorButton temp = iter.next();
			temp.setbPressed(false);
		}
	}

	public Vector<Integer> pressedNumbers() {
		Vector<Integer> resultVec = new Vector<Integer>();
		Iterator<Integer> iter = buttons.keySet().iterator();
		while (iter.hasNext()) {
			int i = iter.next();
			if (buttons.get(i).isbPressed())
				resultVec.add(i);
		}
		Collections.sort(resultVec);
		return resultVec;
	}

	@Override
	public String toString() {
		return "ButtonPanel [minFloor=" + minFloor + ", maxFloor=" + maxFloor + 
				", Pressed Floors=" + pressedNumbers() + "]";
	}

	@Override
	public ButtonPanel clone() throws CloneNotSupportedException {
		ButtonPanel result = (ButtonPanel)super.clone();
		result.buttons = new HashMap<Integer, FloorButton>();
		Iterator<Integer> iter = buttons.keySet().iterator();
		while (iter.hasNext()) {
			int i = iter.next();
			result.buttons.put(i, buttons.get(i).clone());
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buttons == null) ? 0 : buttons.hashCode());
		result = prime * result + maxFloor;
		result = prime * result + minFloor;
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
		ButtonPanel other = (ButtonPanel) obj;
		if (buttons == null) {
			if (other.buttons != null)
				return false;
		} else if (!buttons.equals(other.buttons))
			return false;
		if (maxFloor != other.maxFloor)
			return false;
		if (minFloor != other.minFloor)
			return false;
		return true;
	}
	
}
