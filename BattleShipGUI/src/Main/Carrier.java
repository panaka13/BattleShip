package Main;

import java.awt.Color;

public class Carrier extends Ship {
	public Carrier() {
		super(5, Color.CYAN);
	}
	
	@Override 
	public String getName() {
		return "Carrier";
	}
}
