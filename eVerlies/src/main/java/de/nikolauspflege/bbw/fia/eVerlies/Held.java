package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.LinkedList;
import java.util.List;

public class Held {

	private int aussetzZaehler;
	private String name;
	private Wuerfel w1;
	private Wuerfel w2;
	private boolean isAlive=true;
	private List<Schatz> schaetze = new LinkedList<Schatz>();

	public Held(String string) {
		// setze den Namen
		name = string;
		w1 = new Wuerfel(6);
		w2 = new Wuerfel(6);
	}

	public boolean setztAus() {
		// der Held setzt einmal aus
		boolean pausiert = false;
		if (aussetzZaehler > 0) {
			aussetzZaehler--;
			pausiert = true;
		}
		return pausiert;
	}

	public int greiftAn() {
		// mit 2 Würfeln wurfeln
		return w1.wuerfeln() + w2.wuerfeln()      ;
	}

	public boolean isAlive() {
		// lebt der Held noch
		return isAlive;
	}

	public String getName() {
		return name;
	}

	public void mussAussetzen(int i) {
		// setze wieviele runden ausgesetzt werden muss
		aussetzZaehler=i;
		
	}

	public void stirbt() {
		isAlive=false;
		
	}

	public int getVermögen() {
		// gibt das Vermögen zurück
		int vermoegen = 0;
		for (Schatz schatz : schaetze) {
			vermoegen += schatz.getValue();
		}
		return vermoegen;
	}

	List<Schatz> verliertAlles() {
		List<Schatz> verlust = schaetze;
		schaetze = new LinkedList<Schatz>();
		return verlust;
	}

	public void addAll(List<Schatz> schaetze2) {
		// fügt alle Schaetze dem Vermögen des Helden hinzu
		this.schaetze.addAll(schaetze2);
		
	}

	public Schatz removeSchatz() {
		// gib einen Schatz an das Monster
		Schatz schatz = null; 
		if (schaetze.size() > 0 ) {
			schatz = schaetze.remove(0);
			System.out.println("Der Held verliert eine Schatzkarte im Wert von " + schatz.getValue() + " Goldstücken");
		} else {
			System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
		}
		return schatz;
	}


	public boolean hatVermoegen() {
		// hat der Held Schätze
		return (schaetze.size() > 0);
	}
	public void ziehtSichZurueck(Monster monster) {
		// mache den Rettungswurf
		int rettungsWurf = w1.wuerfeln() + w2.wuerfeln();
		switch (rettungsWurf) {
		case 7:
		case 11:
			// nichts passiert 
			System.out.println("Der Held hat Glück, denn das Monster schlägt daneben");
			break;
		case 6:
		case 8:
			// verlust einer Schatzkarte 
			if (!hatVermoegen()) {
				System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
			} else {
				// verlust des 1. Schatzes
				Schatz verlust = schaetze.remove(0);
				System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken");
				monster.add(verlust);
			}
			break;
		case 4:
		case 5:
		case 9:
		case 10:
			// verlust von 2 schatzkarten, ein mal aussetzen 
			if (!hatVermoegen()) {
				System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
			} else {
				// verlust des 1. Schatzes
				Schatz verlust = schaetze.remove(0);
				System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken");
				monster.add(verlust);
				if (!hatVermoegen()) {
					System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
				} else {
					// verlust des 1. Schatzes
					verlust = schaetze.remove(0);
					System.out.println("Der Held verliert noch eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken");
					monster.add(verlust);
				}

			}
			this.mussAussetzen(1);
			break;

		default:
			// der Held ist tot
			System.out.println("Leider har der Held den Kampf mit dem Monster verlohren und ist nun tot!"); 
			this.stirbt();
			break;
		}
		
	}
}
