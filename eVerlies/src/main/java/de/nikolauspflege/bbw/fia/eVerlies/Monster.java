package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Monster {

	private int siegespunkte;
	private List <Schatz> schaetze = new LinkedList<Schatz>();

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

	public void add(Schatz schatz) {
		// addiere den Schatz zum Vermögen des Monsters
		if (schatz != null) {
			schaetze.add(schatz);
		}
	}

	public void add(List<Schatz> schaetze) {
		// addiere alle schätze zum Vermögen des Monsters
		schaetze.addAll(schaetze);
		
	}

	public List<Schatz> getSchaetze() {
		// return das Vermögen
		return schaetze;
	}

	public int getVermögen() {
		int vermoegen = 0;
		for (Schatz schatz : schaetze) {
			vermoegen += schatz.getValue();
		}
		return vermoegen;
	}

}
