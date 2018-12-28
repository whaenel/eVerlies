package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.LinkedList;
import java.util.List;

/**DIe Klasse Schatzbesitzer ist die Basisklasse für Monster und Helden, da alle 
 * Schätze besitzen, die sie verlieren und gewinnen können.
 * Da es auch manche Schatzbesitzer geben wird, die zaubern können, sollte man das auch 
 * abfragen können
 * @author walter
 *
 */
public abstract class Schatzbesitzer {

	protected String name;
	protected boolean zauberer=false;
	protected List<Schatz> schaetze = new LinkedList<Schatz>();

	public Schatzbesitzer() {
		super();
	}

	/** bekomme den Namen
	 * @return Name des Helden/Monsters
	 */
	public String getName() {
		return name;
	}

	public boolean isZauberer() {
		return zauberer;
	}

	public void setZauberer(boolean zauberer) {
		this.zauberer = zauberer;
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

	/**Fügt eien Schatz zu den Schätzen des Monsters hinzu
	 * @param schatz
	 */
	public void add(Schatz schatz) {
		// addiere den Schatz zum Vermögen des Monsters
		if (schatz != null) {
			schaetze.add(schatz);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

	/**Rückzug beim Angriff
	 * macht einen Rettungswurf mit 2 6er Würfel
	 * 7,11 nichts passiert da das Monster daneben schlägt
	 * 6,8  der Held verliert eine Schatzkarte an das Monster, wenn er eine hat
	 * 4,5,9,10 der Held verliert 2 Schatzkarten an das Monster und muß eine Runde aussetzen
	 * 3,12 Der Held verliert alle Schatzkarten an das Monster
	 * 2 Der Held stirbt
	 * @param monster
	 * @return 
	 */
	public abstract AngriffsErgebnis wirdAngegriffen(Schatzbesitzer besitzer);

	public abstract int greiftAn();

	public abstract int getSiegespunkte();


}