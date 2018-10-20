package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {

	public static void main(String[] args) {
		// run the Game

		Held held = new Held("Pantoffel");
		Monster monster = new Monster(1);
		Schatz schatz = new Schatz(1);
		monster.add(schatz);
		Wuerfel wuerfel6 = new Wuerfel(6);
		int level = 1;

		while ((held.isAlive()) && (level <= 10)) {
			// auf in die nächste Runde
			System.out.println("Level: " + level);
			if (!held.mussAussetzen()) {
				int angriffspunkte = held.greiftAn();
				System.out.println("held greift mit " + angriffspunkte + " Punkten an");
				if (angriffspunkte >= monster.getSiegespunkte()) {
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
					held.ziehtSichZurueck(monster);
				}
			} else {
				// monster = new Monster(level);
				// schatz = new Schatz(level);
				held.setztAus();
				System.out.println("Der Held setzt eine Runde aus!");
			}
			level++;

		}
		int goldstuecke = held.getVermögen();
		if (held.isAlive()) {
			System.out.println(
					"Der Held " + held.getName() + " beendet seine Schatzsuche mit " + goldstuecke + " Goldstücke");
		} else {
			System.out
					.println("Der Held " + held.getName() + " hinterläßt der Nachwelt " + goldstuecke + " Goldstücke");
		}

	}

}
