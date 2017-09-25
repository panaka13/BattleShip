package stage2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import GeneralGUI.Notification;
import Main.Player;
import Main.Ship;
import Main.Shoot;
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
	private boolean isReverse;
	
	public PlayerGUI(Player me, boolean isReverse) {
		super();
		this.isReverse = isReverse;
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
		myBoard.setBorder(new LineBorder(Color.BLUE));
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++) {
				myCells[x][y] = new MyCoord(x, y, this);
				myBoard.add(myCells[x][y]);
			}
		if (!isReverse) {
			this.add(myBoard);
			this.add(myList);
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
		eShips[0] = new MyEnemyShipStatus(enemyGUI.getPlayer().getDestroyer());
		eShips[1] = new MyEnemyShipStatus(enemyGUI.getPlayer().getSubmarine());
		eShips[2] = new MyEnemyShipStatus(enemyGUI.getPlayer().getCruiser());
		eShips[3] = new MyEnemyShipStatus(enemyGUI.getPlayer().getBattleship());
		eShips[4] = new MyEnemyShipStatus(enemyGUI.getPlayer().getCarrier());
		for(int i=0; i<5; i++) 
			eList.add(eShips[i]);
		this.add(eBoard);
		this.add(eList);
		if (isReverse) {
			this.add(myBoard);
			this.add(myList);
		}
	}

	public Player getPlayer() {
		return player;
	}
	
	public PlayerGUI getEnemyGUI() {
		return enemyGUI;
	}
	
	public void shootAt(Shoot shoot) {

		switch (shoot.getResult()) {
		case DESTROYER:
			eShips[0].update();
			if (isWon()) {
				new Notification(new String[]{"Destroyer killed", "You win the game"});
				System.out.println(player);
				System.out.println(enemyGUI.getPlayer());
				Main.Test.stage2.endGame(player, enemyGUI.getPlayer());
			} else 
				new Notification(new String[] {"Destroyer killed"});
			break;
		case SUBMARINE:
			eShips[1].update();
			if (isWon()) {
				new Notification(new String[]{"Submarine killed", "You win the game"});
				Main.Test.stage2.endGame(player, enemyGUI.getPlayer());
			}
				new Notification(new String[] {"Submarine killed"});
			break;
		case CRUISER:
			eShips[2].update();
			new Notification(new String[]{"Cruiser killed"});
			break;
		case BATTLESHIP:
			eShips[3].update();
			new Notification(new String[]{"Battleship killed"});
			break;
		case CARRIER:
			eShips[4].update();
			new Notification(new String[]{"Carrier killed"});
			break;
		case MISS:
			new Notification(new String[]{"You miss your target"});
			break;
		case HIT:
			new Notification(new String[]{"You hit your target"});
			break;
		}
	}
	
	public Shoot getShot(int x, int y) {
		Shoot shot = player.getShotAt(x, y);
		if (shot.getResult() != ShootResult.MISS)
			for(int i=0; i<5; i++)
				myShips[i].update();
		myCells[x][y].update();
		return shot;
	}
	
	public boolean isLost() {
		return player.isLost();
	}
	
	private boolean isWon() {
		return enemyGUI.isLost();
	}
}
