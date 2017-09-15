package stage1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Main.Coord;
import Main.Direction;
import Main.Player;
import Main.Ship;

public class PlayerGUI {
	
	public static final int DESTROYER = 1;
	public static final int SUBMARINE = 2;	
	public static final int CRUISER = 3;
	public static final int BATTLESHIP = 4;
	public static final int CARRIER = 5;
	
	private Direction direction = Direction.SOUTH;
	private int ship = 0;
	
	private MyCoord[][] button = new MyCoord[10][10];
	private JPanel board;
	private JPanel name = new JPanel();
	private JPanel panel;
	private Player player;
	private MyRotateButton rotateButton;
	private MyChooseShipButton[] shipButtons = new MyChooseShipButton[5];
	private MyFinishButton finish;
	private JLabel currentShip;
	private JLabel currentDirection;
	
	public PlayerGUI(Player p) {
		player = p;
		panel = new JPanel(new FlowLayout());
		
		board = new JPanel(new GridLayout(10, 10));
		board.setSize(new Dimension(MainStage.WBOARD, MainStage.HBOARD));
		board.setMinimumSize(board.getSize());
		board.setMaximumSize(board.getSize());
		board.setPreferredSize(board.getSize());
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) {
				button[i][j] = new MyCoord(p.getMyBoard(i, j), this, i, j);
				board.add(button[i][j]);
			}
		board.setBorder(new LineBorder(Color.BLUE));
		
		name.setLayout(new BoxLayout(name, BoxLayout.Y_AXIS));
		name.setAlignmentY(0.5f);
		name.add(new JLabel(player.getName()));
		currentShip = new JLabel("Ship: none");
		currentDirection = new JLabel("Direction: South");
		name.add(currentShip);
		name.add(currentDirection);
		for(int i=0; i<5; i++) {
			shipButtons[i] = new MyChooseShipButton(i+1, this);
			name.add(shipButtons[i]);
		}
		rotateButton = new MyRotateButton(this);
		name.add(rotateButton);
		finish = new MyFinishButton(this);
		name.add(finish);
		
		panel.add(board);
		panel.add(name);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void hide() {
		panel.setVisible(false);
	}
	
	public void show() {
		panel.setVisible(true);
	}
	
	public void chooseShip(int ship) {
		this.ship = ship;
		switch (ship) {
		case 0:
			currentShip.setText("Ship: null");
			break;
		case 1:
			currentShip.setText("Ship: Destroyer");
			break;
		case 2:
			currentShip.setText("Ship: Submarine");
			break;
		case 3:
			currentShip.setText("Ship: Cruiser");
			break;
		case 4:
			currentShip.setText("Ship: Battleship");
			break;
		case 5:
			currentShip.setText("Ship: Carrier");
			break;
		}
	}
	
	public void changeDirection() {
		switch (direction) {
		case EAST:
			currentDirection.setText("Direction: SOUTH");
			direction = Direction.SOUTH;
			break;
		case SOUTH:
			currentDirection.setText("Direction: EAST");
			direction = Direction.EAST;
			break;
		}		
	}
	
	public void placeShip(Coord coord) {
		int x = coord.getX();
		int y = coord.getY();		
		boolean isPlaced = false;
		Ship chosen = null;
		switch (this.ship) {
		case 0:
			return;
		case 1:
			isPlaced = player.placeDestroyer(x, y, this.direction);
			chosen = Ship.DESTROYER;
			break;
		case 2:
			isPlaced = player.placeSubmarine(x, y, this.direction);
			chosen = Ship.SUBMARINE;
			break;
		case 3:
			isPlaced = player.placeCruiser(x, y, this.direction);
			chosen = Ship.CRUISER;
			break;
		case 4:
			isPlaced = player.placeBattleship(x, y, this.direction);
			chosen = Ship.BATTLESHIP;
			break;
		case 5:
			isPlaced = player.placeCarrier(x, y, this.direction);
			chosen = Ship.CARRIER;
			break;
		}		
		if (isPlaced) { 
			for(int i=0; i<chosen.getHealth(); i++) {
				int xx = x+i*this.direction.getDx();
				int yy = y+i*this.direction.getDy();
				button[xx][yy].setBackground(chosen.getColor());
			}		
			for (ActionListener action: shipButtons[this.ship-1].getActionListeners()) {
				shipButtons[this.ship-1].removeActionListener(action);	
			}
			chooseShip(0);
		}
	}
}