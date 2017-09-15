package stage2;

import java.awt.Color;

import javax.swing.JLabel;

import Main.Ship;

public class MyShipStatus extends JLabel {
	
	private Ship ship;
	private String name;
	
	public MyShipStatus(Ship ship) {
		super();
		this.ship = ship;
		switch (ship) {
		case DESTROYER:
			name = "DESTROYER ";
		case CRUISER:
			name = "CRUISER ";
		case SUBMARINE:
			name = "SUBAMRINE ";
		case BATTLESHIP:
			name = "BATTLESHIP ";
		case CARRIER:
			name = "CARRIER ";
		}
		update();
	}
	
	public void update() {
		this.setText(String.format("%s%d", name, ship.getHealth()));
		if (ship.getHealth() == 0) 
			this.setBackground(Color.WHITE);
		else
			this.setBackground(ship.getColor());
		this.setVisible(true);
	}
}
