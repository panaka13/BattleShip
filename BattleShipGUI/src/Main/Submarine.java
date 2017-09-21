package Main;

import java.awt.Color;

public class Submarine extends Ship {
	public Submarine() {
		super(3, Color.BLUE);
	}
	
	@Override
	public String getName() {
		return "Submarine";
	}
}
