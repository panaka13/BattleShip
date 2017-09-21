package Main;

import java.util.Arrays;

public class Computer extends Player {
	
	private static int[] ma1 = {-1,0,1,0};
	private static int[] ma2 = {0,1,0,-1};
	
	private int heso;
	private int[][] score;
	private int[][] enemy;
	private Ship[] ships = new Ship[5];

	public Computer(int level) {
		super("Computer");
		heso = 35;
		score = new int[10][10];
		ships[0] = new Destroyer();
		ships[1] = new Submarine();
		ships[2] = new Cruiser();
		ships[3] = new Battleship();
		ships[4] = new Carrier();
	}
	
	Coord findPosition() {
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++)
				score[y][x] = 0;
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++)
				for(int i=0; i<5; i++)
					if (ships[i] != null)
						check(y, x, ships[i].getHealth());
		Coord res = null;
		for(int x=0; x<10; x++)
			for(int y=0; y<10; y++)
				if (enemy[y][x] == 0) 
					if (res == null || score[y][x] > score[res.getY()][res.getX()])
						res = new Coord(x, y);
		return res;
	}
	
	private void check(int x, int y, int len) {
		int cnt = 1;
		boolean ok = true;
		for(int i=0; i<len; i++)
			if (x+i >=10 || enemy[x+i][y] == -1)
				ok = false;
			else if (enemy[x+i][y] == 1) 
				cnt *= heso;		
		if (ok) 
			for(int i=0; i<len;i++) 
				score[x+i][y] += 1 + cnt;
		cnt = 1;
		ok = true;
		for(int i=0; i<len; i++)
			if (y+i >=10 || enemy[x][y+i] == -1)
				ok = false;
			else if (enemy[x][y+i] == 1) 
				cnt *= heso;		
		if (ok) 
			for(int i=0; i<len;i++) 
				score[x][y+i] += 1 + cnt;
	}
	
	private int checkShip(int x, int y, int dir, int len) {
		int cnt = 0;
		for(int i=0; i<len; i++) 
			if (x+ma1[dir]*i >=0 && y+ma2[dir]*i >=0 && x+ma1[dir]*i < 10 && y+ma2[dir]*i < 10)
				if (enemy[x+ma1[dir]*i][y+ma2[dir]*i] == 1)
					++cnt;
				else if (enemy[x+ma1[dir]*i][y+ma2[dir]*i] == -1)
					return -1;
		return cnt;
	}
	
	private void findShip(int x, int y, int len) {
		for(int i=0; i<4; i++) 
			if (checkShip(x, y, i, len) == len) {
				for(int j=0; j<len; j++)
					enemy[x+ma1[i]*j][y+ma2[i]*j] = -1;
				return;
			}
	}
	
	@Override
	public void shootAt(Shoot shoot) {
		history.add(shoot);
		int x = shoot.getCoord().getX();
		int y = shoot.getCoord().getY();
		switch (shoot.getResult()) {
		case MISS:
			enemy[y][x] = -1;
			break;
		case HIT:
			enemy[y][x] = 1;
			break;
		case DESTROYER:
			enemy[y][x] = 1;
			findShip(y, x, ships[0].getHealth());
			ships[0] = null;
			break;
		case SUBMARINE:
			enemy[y][x] = 1;
			findShip(y, x, ships[1].getHealth());
			ships[1] = null;
			break;
		case CRUISER:
			enemy[y][x] = 1;
			findShip(y, x, ships[2].getHealth());
			ships[2] = null;
			break;
		case BATTLESHIP:	
			enemy[y][x] = 1;
			findShip(y, x, ships[3].getHealth());
			ships[3] = null;
			break;
		case CARRIER:
			enemy[y][x] = 1;
			findShip(y, x, ships[4].getHealth());
			ships[4] = null;
			break;
		}
	}	
}