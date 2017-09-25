package stage3;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Player;

public class PlayerGUI extends JPanel{
	
	private MyCoord myCells[][] = new MyCoord[10][10];
	private JPanel myBoard = new JPanel();
	private JLabel endNoti;
	private JPanel name = new JPanel();
	
	public PlayerGUI(Player player, boolean isWin) {
		super();
		this.setSize(MainStage.WIDTH, MainStage.HBOARD);
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setLayout(new FlowLayout());
		
		myBoard.setSize(MainStage.WBOARD, MainStage.HBOARD);
		myBoard.setMinimumSize(myBoard.getSize());
		myBoard.setMaximumSize(myBoard.getSize());
		myBoard.setPreferredSize(myBoard.getSize());
		myBoard.setLayout(new GridLayout(10, 10));		
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				myCells[i][j] = new MyCoord(i, j, player);
				myBoard.add(myCells[i][j]);
			}
				
		name.setLayout(new BoxLayout(name, BoxLayout.Y_AXIS));
		name.setAlignmentY(0.5f);
		name.add(new JLabel(player.getName()));
		if (isWin)
			endNoti = new JLabel("Victory");
		else 
			endNoti = new JLabel("Defeated");
		name.add(endNoti);
		
		this.add(myBoard);
		this.add(name);
	}
}
