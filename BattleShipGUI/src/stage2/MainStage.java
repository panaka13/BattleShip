package stage2;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Player;
import stage2.PlayerGUI;

public class MainStage extends JFrame {
	public static final int HEIGHT = 600;
	public static final int WIDTH = 480;
	public static final int WBOARD = 300;
	public static final int HBOARD = 300;
	private PlayerGUI p1;
	private PlayerGUI p2;
	private JPanel panel = new JPanel();
	
	//TODO: Check win / lose
	
	public MainStage(Player player1, Player player2) {
		super();		
		this.setSize(WIDTH, HEIGHT);
		panel.setSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(new CardLayout());
		p1 = new PlayerGUI(player1);
		p2 = new PlayerGUI(player2);
		p1.addEnemy(p2);
		p2.addEnemy(p1);
		panel.add(p1);
		panel.add(p2);
		this.add(panel);
		this.setVisible(true);
		this.validate();
	}
}
