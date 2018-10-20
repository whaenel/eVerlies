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
		
		
<<<<<<< Upstream, based on origin/master
		while ((held.isAlive() ) && (level <= 10)) {
			// auf in die nächste Runde 
=======
		while (held.isAlive()) {
			// auf in die nächste Runde  
>>>>>>> d604811 dummy Änderung
			System.out.println("Level: "+level);
			if (!held.setztAus()) {
				int angriffspunkte = held.greiftAn();
				System.out.println("held greift mit " + angriffspunkte +" Punkten an");
				if (angriffspunkte >= monster.getSiegespunkte()) {
					List<Schatz> schaetze = monster.getSchaetze();
					int wert =monster.getVermögen();
					if (schaetze.size() > 1) {
						System.out.println("Held "+ held.getName() +" erschlägt das Monster ("+monster.getSiegespunkte()+") und bekommt die Schätze im Wert von " + wert + " Goldstücken" );						
					} else {
						System.out.println("Held "+ held.getName() +" erschlägt das Monster ("+monster.getSiegespunkte()+") und bekommt den Schatz im Wert von " + wert + " Goldstücken" );						
					}
					held.addAll(schaetze);
					// erstelle ein neues monster mit schatz
					monster = new Monster(level);
					schatz = new Schatz(level);
					monster.add(schatz);

				} else {
					System.out.println("Held "+ held.getName() +" schlägt daneben und das Monster ("+monster.getSiegespunkte()+") schlägt zurück!");
					held.ziehtSichZurueck(monster);
				}
			
			} else {
				//monster = new Monster(level);
				//schatz = new Schatz(level);	
				System.out.println("Der Held setzt eine Runde aus!");  
			}
			level ++ ;
			
		}
		int goldstuecke = held.getVermögen();
		System.out.println("Der Held "+ held.getName() + " hinterläßt der Nachwelt " +goldstuecke + " Goldstücke");

	}

}
