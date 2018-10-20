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
						if (vermögen.isEmpty()) {
							System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
						} else {
							// verlust des 1. Schatzes
							Schatz verlust = vermögen.remove(0);
							System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken");
							monster.add(verlust);
						}
						break;
					case 4:
					case 5:
					case 9:
					case 10:
						// verlust von 2 schatzkarten, ein mal aussetzen 
						if (vermögen.isEmpty()) {
							System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
						} else {
							// verlust des 1. Schatzes
							Schatz verlust = vermögen.remove(0);
							System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken");
							monster.add(verlust);
							if (vermögen.isEmpty()) {
								System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
							} else {
								// verlust des 1. Schatzes
								verlust = vermögen.remove(0);
								System.out.println("Der Held verliert noch eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken");
								monster.add(verlust);
							}

						}
						held.mussAussetzen(1);
						break;

					default:
						// der Held ist tot
						System.out.println("Leider har der Held den Kampf mit dem Monster verlohren und ist nun tot!");
						held.stirbt();
						break;
					}
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
