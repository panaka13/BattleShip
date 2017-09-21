package Main;

import java.awt.Color;

public enum Ship {
	CARRIER(5, Color.CYAN),
	BATTLESHIP(4, Color.GREEN),
	CRUISER(3, Color.ORANGE),
	SUBMARINE(3, Color.BLUE),
	DESTROYER(2, Color.RED),
	HIT(0, Color.BLACK);
	
	private int health;
	private Coord position;
	private Direction direction;
	private Color color;
	
	Ship(int health, Color color) {
		this.health = health;
		this.color = color;
	}
	
	public void setDirection(Direction d) {
		this.direction = d;
	}
	
	public void setCoords(int x, int y) {
		this.position = new Coord(x, y);
	}
	
	public void setCoords(Coord pos) {
		this.position = pos;
	}
	
	public Coord getPosition() {
		return this.position;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void getHit() {
		--this.health;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public boolean isDestroyed() {
		return health <= 0;
	}
	
	public Color getColor() {
		return color;
	}
	
}
