package stage2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import Main.Ship;
import stage1.MainStage;

public class MyEnemyShipStatus extends JLabel {
	
	private Ship ship;
	private String name;
	
	public MyEnemyShipStatus(int ship) {
		super();		
		this.setSize(new Dimension(MainStage.WIDTH-MainStage.WBOARD-20, 30));
		this.setPreferredSize(this.getSize());
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		switch (ship) {
		case 0:
			name = "DESTROYER ";
			this.ship = Ship.DESTROYER;
			break;
		case 1:
			name = "SUBMARINE ";
			this.ship = Ship.SUBMARINE;
			break;
		case 2:
			name = "CRUISER ";
			this.ship = Ship.CRUISER;
			break;
		case 3:
			name = "BATTLESHIP ";
			this.ship = Ship.BATTLESHIP;
			break;
		case 4:
			name = "CARRIER ";
			this.ship = Ship.CARRIER;
			break;
		}
		update();
	}
	
	public void update() {
		this.setText(String.format("%s%d", name, ship.getHealth()));
		if (ship.getHealth() == 0) 
			this.setForeground(Color.WHITE);
		else
			this.setForeground(ship.getColor());
		this.setVisible(true);
	}
}