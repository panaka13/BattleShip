package stage2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import Main.Ship;
import stage1.MainStage;

public class MyEnemyShipStatus extends JLabel {
	
	private Ship ship;
	private String name;
	
	public MyEnemyShipStatus(Ship ship) {
		super();		
		this.setSize(new Dimension(MainStage.WIDTH-MainStage.WBOARD-20, 30));
		this.setPreferredSize(this.getSize());
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.ship = ship;
		this.name = ship.getName();
		update();
	}
	
	public void update() {
		if (ship == null) 
			return;
		this.setText(String.format("%s %d", name, ship.getHealth()));
		if (ship.getHealth() == 0) 
			this.setForeground(Color.WHITE);
		else
			this.setForeground(ship.getColor());
		this.setVisible(true);
	}
}