package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {

	public static void main(String[] args) {
		// run the Game

		// Erstelle Held und Monster 
		Held held = new Held("Pantoffel");		
		Monster monster = new Monster(1);
		Schatz schatz = new Schatz(1);
		monster.add(schatz);
		int level = 1;

		// solange der Held lebt und wir level 10 noch nicht erreicht haben 
		while ((held.isAlive()) && (level <= 10)) {
			// auf in die nächste Runde
			System.out.println("Level: " + level);
			// nur wenn der held nicht aussetzen muss
			if (!held.mussAussetzen()) {
				// nun greift der Held an
				int angriffspunkte = held.greiftAn();
				System.out.println("held greift mit " + angriffspunkte + " Punkten an");
				// Wenn die Angriffspunkte >= den Siegespunkten des Monsters sind, hat der Held gewonnen
				if (angriffspunkte >= monster.getSiegespunkte()) {
					// und bekommt den Schatz oder die Schätze des Monsters
					int wert = monster.getVermögen();
					List<Schatz> schaetze = monster.verliertAlles();
					if (schaetze.size() > 1) {
						System.out.println(
								"Held " + held.getName() + " erschlägt das Monster (" + monster.getSiegespunkte()
										+ ") und bekommt die Schätze im Wert von " + wert + " Goldstücken");
					} else {
						System.out.println(
								"Held " + held.getName() + " erschlägt das Monster (" + monster.getSiegespunkte()
										+ ") und bekommt den Schatz im Wert von " + wert + " Goldstücken");
					}
					held.addAll(schaetze);
					// erstelle ein neues monster mit schatz
					monster = new Monster(level);
					schatz = new Schatz(level);
					monster.add(schatz);

				} else {
					// Rückzug des Helden
					held.ziehtSichZurueck(monster);
				}
			} else {
				// der Held setzt aus, aber der level wird hochgezählt
				held.setztAus();
				System.out.println("Der Held setzt eine Runde aus!");
			}
			level++;

		}
		// jetzt sind wir fertig mit kämpfen und möchten wissen, was der Held gewonnen hat
		int goldstuecke = held.getVermögen();
		// und ob er noch lebt
		if (held.isAlive()) {
			System.out.println(
					"Der Held " + held.getName() + " beendet seine Schatzsuche mit " + goldstuecke + " Goldstücke");
		} else {
			System.out
					.println("Der Held " + held.getName() + " hinterläßt der Nachwelt " + goldstuecke + " Goldstücke");
		}

	}

}
