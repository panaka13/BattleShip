package Main;

import java.util.Arrays;
import java.util.Scanner;

import stage1.MainStage;

public class Test {
	
	public static void main(String[] args) {
		Player p1 = new Player("Panaka_13");
		Player p2 = new Computer(35);
		new MainStage(p1, p2);
	}
}
