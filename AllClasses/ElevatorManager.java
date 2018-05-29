package AllClasses;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ElevatorManager {
	private Address address;
	private Vector<Elevator> elevators = new Vector<Elevator>();
	private Vector<ElevatorEvent> eventsVec = new Vector<ElevatorEvent>();
	private int theTime = 0;
	
	public Vector<Elevator> getElevators() {
		return elevators;
	}

	public Vector<ElevatorEvent> getEventsVec() {
		return eventsVec;
	}

	public ElevatorManager(Address address) throws Exception {
		this.address = address;
	}
	
	public boolean addElevator(Elevator elevator) throws Exception {
		if (elevators.contains(elevator))
			throw new Exception("Elevator already in list. Cannot add it again");
		Collections.sort(elevators);
		return elevators.add(elevator.clone());
	}
	
	public boolean addEvent(ElevatorEvent event) throws Exception {
		if (event.getTime() <= theTime) {
			throw new Exception("Cannot accept past events");
		}
		if (eventsVec.contains(event)) {
			throw new Exception("Event already in set. Cannot add it again");
		}
		return eventsVec.add(event);
	}
	
	public void tick() {
		theTime++;
		Iterator<ElevatorEvent> eventIter = eventsVec.iterator();
		while (eventIter.hasNext()) {
			ElevatorEvent temp = eventIter.next();
			if (temp.getTime() == theTime) {
				try {
					temp.doEvent(elevators);
//					if(temp instanceof Beepable)
//						JOptionPane.showMessageDialog(null, ((Beepable)temp).Beep());
				} catch (Exception e) {
					// We don't care about exceptions and ignore them
				}
				eventIter.remove();
			}
		}
		Iterator<Elevator> elevIter = elevators.iterator();
		while (elevIter.hasNext()) {
			Elevator elev = elevIter.next();
			elev.updateState();
		}
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("ElevatorManager at address=" + address + ", numElevators=" + elevators.size()
				+ ", numEvents=" + eventsVec.size() + ", theTime=" + theTime);
		Iterator<Elevator> elevIter = elevators.iterator();
		while (elevIter.hasNext()) {
			Elevator elev = elevIter.next();
			result.append("\n" + elev);
		}
		Collections.sort(eventsVec);
		Iterator<ElevatorEvent> eventIter = eventsVec.iterator();
		while (eventIter.hasNext()) {
			ElevatorEvent temp = eventIter.next();
			result.append("\n" + temp);
		}
		return result.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		ElevatorManager other = (ElevatorManager) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
	
}
