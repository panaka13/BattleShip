package stage1;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Coord;
import Main.Ship;

public class MyCoord extends Button{
	
	private Coord coord;
	private Ship ship;
	private PlayerGUI playerGUI;
	
	public MyCoord() {
		super();
	}
	
	public MyCoord(Ship ship, PlayerGUI playerGUI, int x, int y) {
		super();
		this.setSize(30, 30);
		this.setLabel(Integer.toString(x*10+y));
		this.coord = new Coord(x, y);
		this.ship = ship;
		this.playerGUI = playerGUI;
		if (ship == null) 
			this.setBackground(Color.WHITE);
		else 
			this.setBackground(ship.getColor());
		this.addActionListener(new ClickAction());
	}
	
	public Coord getCoords() {
		return coord;
	}
	
	public Ship getShip() {
		return this.ship;
	}
	
	public PlayerGUI getPlayerGUI() {
		return this.playerGUI;
	}
	
	private class ClickAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MyCoord button = (MyCoord) e.getSource();
			if (button.getShip() == null) {
				if (playerGUI != null)
					playerGUI.placeShip(button.getCoords());
			} else {
				//TODO: remove ship;
			}
			System.out.println("Finish");
		}
	}
}
