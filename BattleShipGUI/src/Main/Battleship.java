package Main;

import java.awt.Color;

public class Battleship extends Ship {
	public Battleship() {
		super(4, Color.GREEN);
	}
	
	@Override 
	public String getName() {
		return "Battleship";
	}
}
