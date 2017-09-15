package stage2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import Main.Ship;
import stage1.MainStage;

public class MyShipStatus extends JLabel {
	
	private Ship ship;
	private String name;
	
	public MyShipStatus(Ship ship) {
		super();
		this.setSize(new Dimension(MainStage.WIDTH-MainStage.WBOARD-20, 30));
		this.setPreferredSize(this.getSize());
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.ship = ship;
		switch (ship) {
		case DESTROYER:
			name = "DESTROYER ";
			break;
		case CRUISER:
			name = "CRUISER ";
			break;
		case SUBMARINE:
			name = "SUBAMRINE ";
			break;
		case BATTLESHIP:
			name = "BATTLESHIP ";
			break;
		case CARRIER:
			name = "CARRIER ";
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
