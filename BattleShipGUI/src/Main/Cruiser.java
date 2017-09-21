package Main;

import java.awt.Color;

public class Cruiser extends Ship {
	public Cruiser() {
		super(3, Color.ORANGE);
	}
	
	@Override
	public String getName() {
		return "Cruiser";
	}
}
