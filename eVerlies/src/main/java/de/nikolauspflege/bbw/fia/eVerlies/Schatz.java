package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Random;

public class Schatz {

	private int value;

	public Schatz(int level) {
		// Schatz ist 100 Goldstücke plus 0..4+level+50 Goldstücke
		Random rand = new Random();
		value = 100 ;
		if (level >=1) {
			value += ( rand.nextInt(level*4)*50);
		}
	}

	public int getValue() {
		return value;
	}

}
