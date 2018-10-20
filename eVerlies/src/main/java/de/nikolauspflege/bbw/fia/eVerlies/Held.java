package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.LinkedList;
import java.util.List;

/**Die Klasse Held bekämpft die Monster. Dazu benötigt sie 2 Würfel um damit den Angriff zu starten.
 * Wenn er das Monster besiegt kann er den Schatz des Monsters in seine Schatztruhe legen.
 * Schlägt er daneben, muss er sich um seinen Rückzug kümmern. Dabei kann er einen, zwei oder gar alle Schätze an das Monster verlieren und
 * muss möglicherweise aussetzen.
 * Wenn er Pech hat stirbt er sogar.
 * Natürlich hat unser Held auch einen Namen.
 * @author walter
 *
 */
public class Held {

	private int aussetzZaehler;
	private String name;
	private Wuerfel w1;
	private Wuerfel w2;
	private boolean isAlive=true;
	private List<Schatz> schaetze = new LinkedList<Schatz>();

	/** Erzeuge einen neuen Helden
	 * @param name des Name des Helden
	 */
	public Held(String name) {
		// setze den Namen
		this.name = name;
		w1 = new Wuerfel(6);
		w2 = new Wuerfel(6);
	}

	/**Der Held muss aussetzen
	 * @param i die Anzahl der Runden
	 */
	public void mussAussetzen(int i) {
		// setze wieviele runden ausgesetzt werden muss
		aussetzZaehler=i;
		
	}

	/**Gibt an, ob der Held aussetzen muss
	 * @return true wenn er aussetzen muss
	 */
	public boolean mussAussetzen() {
		return (aussetzZaehler > 0 );
	}

	/**Der Held setzt einmal aus 
	 * 
	 */
	public void setztAus() {
		// der Held setzt einmal aus
		if (aussetzZaehler > 0) {
			aussetzZaehler--;
		}
	}

	/** Der Held macht den Angriffswurf mit 2 6er Würfel 
	 * @return die Gesamtzahl der Würfel-Punkte
	 */
	public int greiftAn() {
		// mit 2 Würfeln wurfeln
		return w1.wuerfeln() + w2.wuerfeln()      ;
	}

	/** Prüft ob der Held noch lebt
	 * @return true wenn der Held noch lebt.
	 */
	public boolean isAlive() {
		// lebt der Held noch
		return isAlive;
	}
	
	/**Lass den Helden sterben
	 * 
	 */
	public void stirbt() {
		isAlive=false;
	}

	/** bekomme den Namen
	 * @return Name des Heldes
	 */
	public String getName() {
		return name;
	}



	/**wieviel Vermögen hat der Held schon ?
	 * @return die Summe der GoldStücke der Schätze die er besitzt
	 */
	public int getVermögen() {
		// gibt das Vermögen zurück
		int vermoegen = 0;
		for (Schatz schatz : schaetze) {
			vermoegen += schatz.getValue();
		}
		return vermoegen;
	}

	/**Der Held verliert alle Schätze
	 * @return die Liste der Schätze die er besaß
	 */
	public List<Schatz> verliertAlles() {
		List<Schatz> verlust = schaetze;
		schaetze = new LinkedList<Schatz>();
		return verlust;
	}

	/**Fügt eine Liste von Schätzen zu den Schätzen des Heldes hinzu
	 * @param schaetzeListe die Liste der neuen Schätze die hinzugefügt werden sollen
	 */
	public void addAll(List<Schatz> schaetzeListe) {
		// fügt alle Schaetze dem Vermögen des Helden hinzu
		this.schaetze.addAll(schaetzeListe);
		
	}

	/**Der Held verliert eine Schatz und screibt auf Console, was er verliert
	 * @return den Schatz den er verliert
	 */
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

	/**Prüft ob ein Held Schätze besitzt
	 * @return true wenn er Schätze hat
	 */
	public boolean hatVermoegen() {
		// hat der Held Schätze
		return (schaetze.size() > 0);
	}
	
	/**Rückzug nachdem er das Monster nicht erledigt hat
	 * macht einen Rettungswurf mit 2 6er Würfel
	 * 7,11 nichts passiert da das Monster daneben schlägt
	 * 6,8  der Held verliert eine Schatzkarte an das Monster, wenn er eine hat
	 * 4,5,9,10 der Held verliert 2 Schatzkarten an das Monster und muß eine Runde aussetzen
	 * 3,12 Der Held verliert alle Schatzkarten an das Monster
	 * 2 Der Held stirbt
	 * @param monster
	 */
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
				System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken an das Monster (" + monster.getSiegespunkte() +  ")");
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
				System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken an das Monster (" + monster.getSiegespunkte() +  ")");
				monster.add(verlust);
				if (!hatVermoegen()) {
					System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
				} else {
					// verlust des 1. Schatzes
					verlust = schaetze.remove(0);
					System.out.println("Der Held verliert noch eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken an das Monster (" + monster.getSiegespunkte() +  ")");
					monster.add(verlust);
				}

			}
			this.mussAussetzen(1);
			break;
		case 3:
		case 12:
			int wert = getVermögen();
			monster.addAll(schaetze);
			schaetze = new LinkedList<Schatz>();
			System.out.println("Der Held verliert alle Schatzkarten im Wert von " + wert + " Goldstücken");
			break;

		default:
			// der Held ist tot
			System.out.println("Leider hat der Held den Kampf mit dem Monster verlohren und ist nun tot!"); 
			this.stirbt();
			break;
		}
		
	}
}
