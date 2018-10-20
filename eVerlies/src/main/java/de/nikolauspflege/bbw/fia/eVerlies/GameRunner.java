package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.ArrayList;
import java.util.List;

public class GameRunner {

	public static void main(String[] args) {
		// run the Game
		
		Held held = new Held("Pantoffel");
		Monster monster = new Monster(1);
		Schatz schatz = new Schatz(1);
		Wuerfel wuerfel6 = new Wuerfel(6);
		int level = 1;
		List <Schatz> vermögen = new ArrayList<Schatz>();
		
		
		while (held.isAlive()) {
			// auf in die nächste Runde  
			System.out.println("Level: "+level);
			if (!held.setztAus()) {
				int angriffspunkte = held.greiftAn();
				System.out.println("held greift mit " + angriffspunkte +" Punkten an");
				if (angriffspunkte >= monster.getSiegespunkte()) {
					System.out.println("Held "+ held.getName() +" erschlägt das Monster ("+monster.getSiegespunkte()+") und bekommt den Schatz im Wert von " + schatz.getValue() + " Goldstücken" );
					vermögen.add(schatz);
					level++;
					monster = new Monster(level);
					schatz = new Schatz(level);

				} else {
					System.out.println("Held "+ held.getName() +" schlägt daneben und das Monster (\"+monster.getSiegespunkte()+\") schlägt zurück!");
					held.ziehtSichZurueck(monster, vermögen);
				}
			
			} else {
				level++;
				monster = new Monster(level);
				schatz = new Schatz(level);	
				System.out.println("Der Held setzt eine Runde aus!");
			}
			
		}
		int goldstuecke =0;
		for (Schatz schatz2 : vermögen) {
			goldstuecke += schatz2.getValue();
		}
		System.out.println("Der Held "+ held.getName() + " hinterläßt der Nachwelt " +goldstuecke + " Goldstücke");

	}

}
