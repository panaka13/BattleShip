package Main;

public class Shoot {
	private Coord coord;
	private ShootResult result;
	
	public Shoot(Coord coord, ShootResult result) {
		this.coord = coord;
		this.result = result;
	}

	public Coord getCoord() {
		return this.coord;
	}

	public ShootResult getResult() {
		return this.result;
	}
}
