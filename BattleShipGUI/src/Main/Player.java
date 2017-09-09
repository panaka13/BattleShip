package Main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Player {
	protected Ship submarine = Ship.SUBMARINE; //1
	protected Ship destroyer = Ship.DESTROYER; //2
	protected Ship cruiser = Ship.CRUISER; //3
	protected Ship battleship = Ship.BATTLESHIP; //4
	protected Ship carrier = Ship.CARRIER; //5
	protected String name;
	protected List<Shoot> history;
	protected Ship[][] mine;
	protected int[][] enemy;
	protected JPanel panel = new JPanel();
	
	public Player(String name) {
		this.name = name;
		history = new LinkedList<Shoot>();
		mine = new Ship[10][10];
		createGUI();
	}
	
	public Ship getSubmarine() {
		return submarine;
	}

	public Ship getDestroyer() {
		return destroyer;
	}

	public Ship getCruiser() {
		return cruiser;
	}

	public Ship getBattleship() {
		return battleship;
	}

	public Ship getCarrier() {
		return carrier;
	}

	public String getName() {
		return name;
	}

	public List<Shoot> getHistory() {
		return history;
	}
	
	public boolean placeShip(int x, int y, Direction direction, Ship ship) {
		int dx = direction.getDx();
		int dy = direction.getDy();
		for(int i=0; i<ship.getHealth(); i++) {
			if (x+i*dx >= 10 || y+i*dy < 0) return false;
			if (y+i*dy >= 10 || y+i*dy < 0) return false;
			if (mine[x+i*dx][y+i*dy] != null)
				return false;
		}
		for(int i=0; i<ship.getHealth(); i++)
			mine[x+i*dx][y+i*dy] = ship;
		return true;
	}
	
	public boolean placeShip(Coord position, Direction direction, Ship ship) {
		return placeShip(position.getX(), position.getY(), direction, ship);
	}
	
	public boolean placeSubmarine(int x, int y, Direction direction) {
		if (!placeShip(x, y, direction, submarine))
			return false;
		submarine.setCoords(x, y);
		submarine.setDirection(direction);
		return true;
	}

	public boolean placeSubmarine(Coord position, Direction direction) {
		return placeSubmarine(position.getX(), position.getY(), direction);
	}
	
	public boolean placeDestroyer(int x, int y, Direction direction) {
		if  (!placeShip(x, y, direction, destroyer))
			return false;
		destroyer.setCoords(x, y);
		destroyer.setDirection(direction);
		return true;
	}
	
	public boolean placeDestroyer(Coord position, Direction direction) {
		return placeDestroyer(position.getX(), position.getY(), direction);
	}
	
	public boolean placeCruiser(int x, int y, Direction direction) {
		if (!placeShip(x, y, direction, cruiser)) 
			return false;
		cruiser.setCoords(x, y);
		cruiser.setDirection(direction);
		return true;
	}
	
	public boolean placeCruiser(Coord position, Direction direction) {
		return placeCruiser(position.getX(), position.getY(), direction);
	}
	
	public boolean placeBattleship(int x, int y, Direction direction) {
		if (!placeShip(x, y, direction, battleship))
			return false;
		battleship.setCoords(x, y);
		battleship.setDirection(direction);
		return true;
	}
	
	public boolean placeBattleship(Coord position, Direction direction) {
		return placeBattleship(position.getX(), position.getY(), direction);
	}
	
	public boolean placeCarrier(int x, int y, Direction direction) {
		if (!placeShip(x, y, direction, carrier))
			return false;
		carrier.setCoords(x, y);
		carrier.setDirection(direction);
		return true;
	}
	
	public boolean placeCarrier(Coord position, Direction direction) {
		return placeCarrier(position.getX(), position.getY(), direction);
	}
	
	public void remove(Coord position) {
		remove(position.getX(), position.getY());
	}
	
	public void remove(int coordx, int coordy) {
		Ship ship = mine[coordx][coordy];
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++) 
				if (mine[x][y] == ship)
					mine[x][y] = null;
	}
	
	public void shootAt(Shoot shoot) {
		history.add(shoot);
	}
	
	public Shoot getShotAt(int x, int y) {
		return getShotAt(new Coord(x, y));
	}
	
	public Shoot getShotAt(Coord position) {
		ShootResult result = ShootResult.HIT;
		int x = position.getX();
		int y = position.getY();
		switch (mine[x][y]) {
		case SUBMARINE:
			submarine.getHit();
			if (submarine.isDestroyed())
				result = ShootResult.SUBMARINE;
			break;
		case DESTROYER:
			destroyer.getHit();
			if (destroyer.isDestroyed())
				result = ShootResult.DESTROYER;
			break;
		case CRUISER:
			cruiser.getHit();
			if (cruiser.isDestroyed())
				result = ShootResult.CRUISER;
			break;
		case BATTLESHIP:
			battleship.getHit();
			if (battleship.isDestroyed())
				result = ShootResult.BATTLESHIP;
			break;
		case CARRIER:
			carrier.getHit();
			if (carrier.isDestroyed())
				result = ShootResult.CARRIER;
			break;
		default:
			result = ShootResult.MISS;
			break;
		}
		return new Shoot(position, result);
	}
	
	public boolean isLost() {
		if (submarine.getHealth() > 0) 
			return false;
		if (destroyer.getHealth() > 0)
			return false;
		if (cruiser.getHealth() > 0)
			return false;
		if (battleship.getHealth() > 0)
			return false;
		if (carrier.getHealth() > 0)
			return false;
		return true;
	}
	
	public void createGUI() {		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
//		panel.setSize(new Dimension(MainStage.WIDTH, MainStage.HBOARD));
		panel.setBorder(new LineBorder(Color.BLACK));
		
		panel.removeAll();
		
		JPanel text = new JPanel();
//		text.setSize(MainStage.WIDTH-MainStage.WBOARD, MainStage.HBOARD);
		text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel name = new JLabel(getName());
		name.setAlignmentX(0.5f);
		text.add(name);
		text.add(Box.createRigidArea(new Dimension(0, 10)));
		text.add(addShip(getDestroyer()));
		text.add(Box.createRigidArea(new Dimension(0, 10)));
		text.add(addShip(getSubmarine()));
		text.add(Box.createRigidArea(new Dimension(0, 10)));
		text.add(addShip(getCruiser()));
		text.add(Box.createRigidArea(new Dimension(0, 10)));
		text.add(addShip(getBattleship()));
		text.add(Box.createRigidArea(new Dimension(0, 10)));
		text.add(addShip(getCarrier()));
		panel.add(text);
		panel.validate();
		panel.setPreferredSize(panel.getSize());
		panel.setMaximumSize(panel.getSize());
		panel.setMinimumSize(panel.getSize());
		panel.setVisible(true);
	}
	
	private JPanel addShip(Ship ship) {
		JPanel panel = new JPanel(new FlowLayout());
		JLabel name = null;
		switch (ship) {
		case DESTROYER:
			name = new JLabel(String.format("Destroyer %d", ship.getHealth()));
			name.setForeground(Color.RED);
			break;
		case SUBMARINE: 
			name = new JLabel(String.format("Submarine %d", ship.getHealth()));
			name.setForeground(Color.BLUE);
			break;
		case CRUISER:
			name = new JLabel(String.format("Cruiser %d", ship.getHealth()));
			name.setForeground(Color.ORANGE);
			break;
		case BATTLESHIP:
			name = new JLabel(String.format("Battleship %d", ship.getHealth()));
			name.setForeground(Color.GREEN);
			break;
		case CARRIER:
			name = new JLabel(String.format("Carrier %d",  ship.getHealth()));
			name.setForeground(Color.CYAN);
			break;
		}
		panel.add(name);
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		return panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void showGUI() {
		panel.setVisible(true);
	}
	
	public void hideGUI() {
		panel.setVisible(false);
	}
	
	public Ship getMyBoard(int x, int y) { 
		return mine[x][y];
	}
}