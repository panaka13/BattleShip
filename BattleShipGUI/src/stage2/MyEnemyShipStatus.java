package stage2;

import java.awt.Color;

import javax.swing.JLabel;

import Main.Ship;

public class MyEnemyShipStatus extends JLabel {
	
	private Ship ship;
	private String name;
	
	public MyEnemyShipStatus(int ship) {
		super();		
		switch (ship) {
		case 0:
			name = "DESTROYER ";
			this.ship = Ship.DESTROYER;
		case 1:
			name = "SUBMARINE ";
			this.ship = Ship.SUBMARINE;
		case 2:
			name = "CRUISER ";
			this.ship = Ship.CRUISER;
		case 3:
			name = "BATTLESHIP ";
			this.ship = Ship.BATTLESHIP;
		case 4:
			name = "CARRIER ";
			this.ship = Ship.CARRIER;
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