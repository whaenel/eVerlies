package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Random;

public class Monster {

	private int siegespunkte;

	public Monster(int level) {
		// set siegespunkte from level
		Random rand = new Random();
		siegespunkte = 4;
		if (level > 1) {

			siegespunkte += rand.nextInt(level);
			if (siegespunkte > 12) {
				siegespunkte = 12;
			}
		}

	}

	public int getSiegespunkte() {
		return siegespunkte;
	}

	public void add(Schatz verlust) {
		// TODO Auto-generated method stub
		
	}

}
