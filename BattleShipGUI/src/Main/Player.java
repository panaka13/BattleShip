package Main;

import java.util.LinkedList;
import java.util.List;

public class Player {
	protected Ship submarine = new Submarine(); //2
	protected Ship destroyer = new Destroyer(); //1
	protected Ship cruiser = new Cruiser(); //3
	protected Ship battleship = new Battleship(); //4
	protected Ship carrier = new Carrier(); //5
	protected String name;
	protected List<Shoot> history;
	protected Ship[][] mine;
	protected int[][] enemy;
	
	public Player(String name) {
		this.name = name;
		history = new LinkedList<Shoot>();
		mine = new Ship[10][10];
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
		if (mine[x][y] == null) {
			mine[x][y] = new ShipHit();
			return new Shoot(position, ShootResult.MISS);
		}
		switch (mine[x][y].getName()) {
		case "Submarine":
			submarine.getHit();
			if (submarine.isDestroyed())
				result = ShootResult.SUBMARINE;
			break;
		case "Destroyer":
			destroyer.getHit();
			if (destroyer.isDestroyed())
				result = ShootResult.DESTROYER;
			break;
		case "Cruiser":
			cruiser.getHit();
			if (cruiser.isDestroyed())
				result = ShootResult.CRUISER;
			break;
		case "Battleship":
			battleship.getHit();
			if (battleship.isDestroyed())
				result = ShootResult.BATTLESHIP;
			break;
		case "Carrier":
			carrier.getHit();
			if (carrier.isDestroyed())
				result = ShootResult.CARRIER;
			break;
		default:
			result = ShootResult.MISS;
			break;
		}
		mine[x][y] = new ShipHit();
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
	
	public Ship getMyBoard(int x, int y) { 
		return mine[x][y];
	}
}