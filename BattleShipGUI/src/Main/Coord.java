package Main;

public class Coord {
	
	private int x;
	private int y;
	
	public Coord (int x, int y) {  
		this.x = x;
		this.y = y;
	}
	
	public Coord () {
		new Coord(0,0);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
