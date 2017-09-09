package stage1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Main.Ship;

public class MyChooseShipButton extends JButton {
	
	private int ship;
	private PlayerGUI playerGUI;
	
	public MyChooseShipButton(int ship, PlayerGUI playerGUI) {
		super();
		String name = "";
		this.playerGUI = playerGUI;
		switch (ship) {
		case 1:
			name = "Destroyer (2)";
			this.setBackground(Color.RED);
			break;
		case 2:
			name = "Submarine (3)";
			this.setBackground(Color.BLUE);
			break;
		case 3:
			name = "Cruiser (3)";
			this.setBackground(Color.ORANGE);
			break;
		case 4:
			name = "Battleship (4)";
			this.setBackground(Color.GREEN);
			break;
		case 5:
			name = "Carrier (5)";
			this.setBackground(Color.CYAN);
			break;
		}
		this.ship = ship;
		this.setText(name);
		this.setSize(new Dimension(MainStage.WIDTH-MainStage.WBOARD-20, 30));
		this.setMaximumSize(this.getSize());
		this.setPreferredSize(this.getSize());
		this.addActionListener(new ClickAction());
	}
	
	public int getShip() {
		return this.ship;
	}
	
	public PlayerGUI getPlayerGUI() {
		return this.playerGUI;
	}
	
	private class ClickAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			MyChooseShipButton button = (MyChooseShipButton) e.getSource();
			button.getPlayerGUI().chooseShip(ship);
		}
	}
}
