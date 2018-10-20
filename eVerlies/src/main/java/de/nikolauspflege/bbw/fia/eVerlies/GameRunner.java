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
		
		
<<<<<<< Upstream, based on origin/Walter
		while (held.isAlive()) {
		while ((held.isAlive() ) && (level <= 10)) {
			// auf in die nächste Runde 
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
					System.out.println("Held "+ held.getName() +" schlägt daneben!");
					int rettungsWurf = wuerfel6.wuerfeln() + wuerfel6.wuerfeln();
					switch (rettungsWurf) {
					case 7:
					case 11:
						// nichts passiert 
						System.out.println("Der Held hat Glück, denn das Monster schlägt daneben");
						break;
					case 6:
					case 8:
						// verlust einer Schatzkarte 
						monster.add(held.removeSchatz());
						break;
					case 4:
					case 5:
					case 9:
					case 10:
						// verlust von 2 schatzkarten, ein mal aussetzen 
						monster.add(held.removeSchatz());
						monster.add(held.removeSchatz());
						held.mussAussetzen(1);
						break;
					case 3:
					case 12:
						System.out.println("Der Held verliert alle Schätze im Wert von " + held.getVermögen() + " Goldstücken");
						List <Schatz> schaetze = held.verliertAlles();
						monster.add(schaetze);

						break;

					default:
						// der Held ist tot
						System.out.println("Leider har der Held den Kampf mit dem Monster verlohren und ist nun tot!");
						held.stirbt();
						break;
					}
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
