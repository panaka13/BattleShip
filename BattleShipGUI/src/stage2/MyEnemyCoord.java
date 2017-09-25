package stage2;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.Coord;
import Main.Player;
import Main.Ship;
import Main.Shoot;
import Main.ShootResult;

public class MyEnemyCoord extends Button {
	private Coord coord;
	private PlayerGUI myGUI;
	private PlayerGUI enemyGUI;
	private ShootResult result;
	
	public MyEnemyCoord(PlayerGUI me, PlayerGUI enemy, int x, int y) {
		super(Integer.toString(x*10+y));
		this.coord = new Coord(x, y);
		this.myGUI = me;
		this.enemyGUI  = enemy;
		this.result = ShootResult.NONE;
		this.setBackground(Color.WHITE);
		this.addActionListener(new ClickAction());
	}

	public Coord getCoord() {
		return coord;
	}

	public PlayerGUI getMyGUI() {
		return myGUI;
	}
	
	public PlayerGUI getEnemyGUI() {
		return enemyGUI;
	}
	
	public void update(ShootResult result) {
		this.result = result;
		switch (result) {
		case NONE:
			this.setBackground(Color.WHITE);
			break;
		case MISS:
			this.setBackground(Color.BLACK);
			for(ActionListener a: this.getActionListeners())
				this.removeActionListener(a);
			break;
		default:
			this.setBackground(Color.YELLOW);
			for(ActionListener a: this.getActionListeners())
				this.removeActionListener(a);
		}
		this.validate();
	}
	
	private class ClickAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!GeneralGUI.ControlResponse.isResponsive())
				return;
			MyEnemyCoord button = (MyEnemyCoord) e.getSource();
			int x = button.getCoord().getX();
			int y = button.getCoord().getY();
			Shoot shot = button.getEnemyGUI().getShot(x, y);
			update(shot.getResult());
			button.getMyGUI().shootAt(shot);
		}
	}
}
