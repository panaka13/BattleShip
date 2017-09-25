package stage3;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Player;

public class MainStage extends JFrame {
	public static final int HEIGHT = 600;
	public static final int WIDTH = 480;
	public static final int WBOARD = 300;
	public static final int HBOARD = 300;
	private PlayerGUI winner;
	private PlayerGUI loser;
	
	public MainStage(Player winner, Player loser) {
		super();		
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setSize(WIDTH, HEIGHT);
		panel.setMaximumSize(panel.getSize());
		panel.setMinimumSize(panel.getSize());
		panel.setPreferredSize(panel.getSize());
		panel.setLayout(new FlowLayout());
		this.winner = new PlayerGUI(winner, true);
		this.loser = new PlayerGUI(loser, false);
		panel.add(this.winner);
		panel.add(this.loser);
		this.add(panel);
		this.setVisible(true);
	}
}
