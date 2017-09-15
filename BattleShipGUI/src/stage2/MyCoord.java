package stage2;

import java.awt.Button;
import java.awt.Color;

import Main.Coord;
import Main.Ship;

public class MyCoord extends Button{
	
	private Coord coord;
	private PlayerGUI playerGUI;
	
	public MyCoord(int x, int y, PlayerGUI playerGUI) {
		super();
		this.setSize(30, 30);
		this.setLabel(Integer.toString(x*10+y));
		this.coord = new Coord(x, y);
		this.playerGUI = playerGUI;
		update();
	}
	
	public Coord getCoord() {
		return this.coord;
	}
	
	public void update() {
		Ship ship = playerGUI.getPlayer().getMyBoard(coord.getX(), coord.getY());
		if (ship != null) 
			this.setBackground(ship.getColor());
		else 
			this.setBackground(Color.WHITE);
	}
}
