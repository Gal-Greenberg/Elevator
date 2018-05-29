package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;
import AllClasses.*;

public class ElevatorsPanel extends JPanel {
	
	private final static int WIDTH = 400;
	private final static int HEIGHT = 400;
	
	private static int x;
	private static int y;
	
	private static int min;
	private static int max;
	
	private int count;
	private ElevatorManager manager;
	Vector<Elevator> elevator;
	Iterator<Elevator> itr;
	
	public ElevatorsPanel(int count, ElevatorManager manager) {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.count = count;
		this.manager = manager;
		 elevator = this.manager.getElevators();
		 itr = elevator.iterator();
		 getMinAndMaxFloor();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font f = new Font("arial", Font.BOLD, 20);
		g.setFont(f);
		g.drawString("System Time = " + count, 120, 30);
		x = 10;
		y = 50;
		for (int i = max; i >= min; i--) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, 90, 50);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, 90, 50);
			g.drawString(new Integer(i).toString(), (x*5), y+(x*3));
			y += 50;
		}
		g.drawString("Elevators:", x, y+30);
		x = 130;
		y = 50;
		itr = elevator.iterator();
		while (itr.hasNext()) {
			Elevator e = itr.next();
			int currentFloor = e.getCurrentFloor();
			g.drawLine(x+20, y, x+20, (y*6)+50);
			
			if (e instanceof ShabbatElevator) {
				g.drawString(new Integer(e.getId()).toString() + "-Shabat", x-10, (y*7)+30);
			} else {
				g.drawString(new Integer(e.getId()).toString(), x+20, (y*7)+30);
			}
			g.setColor(Color.GRAY);
			g.fillRoundRect(x, y*((max-min+1)-currentFloor), 40, 50, 80, 20);
			g.setColor(Color.BLACK);
			g.drawRoundRect(x, y*((max-min+1)-currentFloor), 40, 50, 80, 20);
			x += 90;
		}
	}
	
	public void getMinAndMaxFloor() {
		while (itr.hasNext()) {
			Elevator e = itr.next();
			if (e.getThePanel().getMinFloor() <= min) {
				min = e.getThePanel().getMinFloor();
			}
			if (e.getThePanel().getMaxFloor() >= max) {
				max = e.getThePanel().getMaxFloor();
			}
		}
	}
	
	public void setCount(int newCount) {
		this.count = newCount;
	}
	
}
