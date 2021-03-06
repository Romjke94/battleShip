package battleShip;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Cell implements ActionListener {

	// TODO: create getters and setters
	Boolean shooted = false;
	JButton button = null;
	Boolean hasShip = false;
	Boolean shipField = false; 
	private Ship owner = null; 
	private String name = "nope";
	String steps;
	int incr = 0;
	// boolean isFloating = false;

	private ArrayList<MyControlListener> listeners = new ArrayList<MyControlListener>();
	//привязываем каждой клетке определенный корабль
	public void setShip(Ship ship) {
		this.owner = ship;
	}

	public Ship getShip() {
		return owner;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getName() {
		return name;
	}

	public void actionPerformed(ActionEvent a) {
		JButton from = (JButton) a.getSource();


		
		if (shooted == true) {
			System.out.println("В эту кнопку уже стреляли");
			return;
			
		} else {
			shooted = true;
			// incr++;

		}

		if (hasShip && owner != null) {
			// from.setText("X");
			from.setBackground(Color.red);
			owner.сheckYourself();
		} else {
			// from.setText("o");
			from.setBackground(Color.blue);
		}
		fireMyEvent("check");
	}

	public void addMyEventListener(MyControlListener listener) {
		listeners.add(listener);
	}
	public MyControlListener[] getMyEventListeners() {
		return listeners.toArray(new MyControlListener[listeners.size()]);
	}
	public void removeMyEventListener(MyControlListener listener) {
		listeners.remove(listener);
	}
	protected void fireMyEvent(String message) {
		MyEvent ev = new MyEvent(this, message);
		for (MyControlListener listener : listeners) {
			listener.onDataChanged(ev);
		}
	}
}