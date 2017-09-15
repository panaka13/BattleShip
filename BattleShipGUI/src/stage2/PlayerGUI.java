package stage2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Player;
import Main.Ship;
import Main.ShootResult;

public class PlayerGUI extends JPanel{
	
	private Player player;
	private PlayerGUI enemyGUI;
	private MyCoord[][] myCells = new MyCoord[10][10];
	private MyEnemyCoord[][] eCells = new MyEnemyCoord[10][10];
	private JPanel myBoard = new JPanel(new GridLayout(10, 10));
	private JPanel eBoard = new JPanel(new GridLayout(10, 10));
	private JPanel myList = new JPanel();
	private JPanel eList = new JPanel();
	private MyShipStatus[] myShips = new MyShipStatus[5];
	private MyEnemyShipStatus[] eShips = new MyEnemyShipStatus[5];
	
	public PlayerGUI(Player me) {
		super();
		this.player = me;
		this.setSize(new Dimension(MainStage.WIDTH, MainStage.HEIGHT));
		this.setMaximumSize(this.getSize());
		this.setMinimumSize(this.getSize());
		this.setPreferredSize(this.getSize());
		this.setLayout(new FlowLayout());
		
		myList.setLayout(new BoxLayout(myList, BoxLayout.Y_AXIS));
		myList.setAlignmentY(0.5f);
		myList.add(new JLabel(player.getName()));
		myShips[0] = new MyShipStatus(player.getDestroyer());
		myShips[1] = new MyShipStatus(player.getSubmarine());
		myShips[2] = new MyShipStatus(player.getCruiser());
		myShips[3] = new MyShipStatus(player.getBattleship());
		myShips[4] = new MyShipStatus(player.getCarrier());
		for(int i=0; i<5; i++) 
			myList.add(myShips[i]);		
		
		myBoard.setSize(new Dimension(MainStage.WBOARD, MainStage.HBOARD));
		myBoard.setMinimumSize(myBoard.getSize());
		myBoard.setMaximumSize(myBoard.getSize());
		myBoard.setPreferredSize(myBoard.getSize());
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++) {
				myCells[x][y] = new MyCoord(x, y, this);
				myBoard.add(myCells[x][y]);
			}
	}   
	
	public void addEnemy(PlayerGUI enemyGUI) {
		this.enemyGUI = enemyGUI;
		eBoard.setSize(new Dimension(MainStage.WBOARD, MainStage.HBOARD));
		eBoard.setMinimumSize(myBoard.getSize());
		eBoard.setMaximumSize(myBoard.getSize());
		eBoard.setPreferredSize(myBoard.getSize());
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++) {
				eCells[x][y] = new MyEnemyCoord(this, enemyGUI, x, y);
				eBoard.add(eCells[x][y]);
			}
		eList.setLayout(new BoxLayout(eList, BoxLayout.Y_AXIS));
		eList.setAlignmentY(0.5f);
		eList.add(new JLabel(enemyGUI.getPlayer().getName()));
		for(int i=0; i<5; i++) {
			eShips[i] = new MyEnemyShipStatus(i);
			eList.add(eShips[i]);
		}
		this.add(eBoard);
		this.add(eList);
	}

	public Player getPlayer() {
		return player;
	}
	
	public PlayerGUI getEnemyGUI() {
		return enemyGUI;
	}
	
	public void shootAt(int x, int y, ShootResult result) {
		//TODO: Shoot enemy at (x, y)
	}
	
	public ShootResult getShot(int x, int y) {
		//TODO: get shoot at (x, y);
		return ShootResult.NONE;
	}
}
