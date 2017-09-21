package Main;

import java.awt.Color;

public class Destroyer extends Ship {
	public Destroyer() {
		super(2, Color.RED);
	}
	
	@Override
	public String getName() {
		return "Destroyer";
	}
}
