package stage1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import Main.Direction;
import Main.Player;

public class MainStage extends JFrame{
	public static final int HEIGHT = 600;
	public static final int WIDTH = 480;
	public static final int WBOARD = 300;
	public static final int HBOARD = 300;
	private static PlayerGUI p1;
	private static PlayerGUI p2;
	
	public static void endTurn(PlayerGUI p) {
		if (p.equals(p1)) {
			p1.hide();
			p2.show();
		} else {
			p2.hide();
			//TODO: link to new stage.
		}
	}
	
	public MainStage(Player player1, Player player2) {
		super();
		this.setLayout(new GridLayout(2,1));
//		this.setResizable(false);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setMinimumSize(this.getSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p1 = new PlayerGUI(player1);
		p2 = new PlayerGUI(player2);
		this.add(p1.getPanel());
		this.add(p2.getPanel());
		p2.hide();
		this.setVisible(true);
	}
}
