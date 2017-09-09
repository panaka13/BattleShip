package Main;

public enum Direction {
	EAST(0,1), WEST(0,-1), SOUTH(1,0), NORTH(-1,0);
	
	private int dx;
	private int dy;
	
	Direction(int x, int y) {
		this.dx = x;
		this.dy = y;
	}

	public int getDx() {
		return this.dx;
	}   

	public int getDy() {
		return this.dy;
	}
}
