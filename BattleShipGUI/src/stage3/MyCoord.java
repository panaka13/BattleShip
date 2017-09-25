package stage3;

import java.awt.Button;
import java.awt.Color;

import Main.Player;

public class MyCoord extends Button {
	public MyCoord(int x, int y, Player player) {
		super();
		this.setSize(30, 30);
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		if (player.getMyBoard(x, y) == null)
			this.setForeground(Color.white);
		else 
			this.setForeground(player.getMyBoard(x, y).getColor());
	}
}
