package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**Ein Monster ist der Gegner des Helden im Spiel. Wenn der Held mit einer Zahl >= den Siegespunkten des Monsters
 * angreift, gewinnt der Held. Damit bekommt er den Schatz des Monsters. Damit das MOnster auch einen Schatz in seiner Schatzkiste hat,
 * muss ihm am Anfang einer gegeben werden. Ein frisch erzeugtes Monster hat keinen.
 * @author walter
 *
 */
public class Monster {

	private int siegespunkte;
	private List <Schatz> schaetze = new LinkedList<Schatz>();

	/**Erzeugt ein neues Monster für den level i.
	 * Je höher der level, desto wahrscheinlicher liegen die Siegespunkte bei 12
	 * @param level der Level im Spiel
	 */
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

	/**Gib die Würfelpunkte zurück, die nötig sind, um das Monster zu besiegen
	 * @return die Anzahl der Punkte
	 */
	public int getSiegespunkte() {
		return siegespunkte;
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

	/**Fügt eine Liste von Schätzen zu den Schätzen des Monsters hinzu
	 * @param schaetzeListe die Liste der neuen Schätze die hinzugefügt werden sollen
	 */
	public void addAll(List<Schatz> schaetzeListe) {
		// fügt alle Schaetze dem Vermögen des Helden hinzu
		this.schaetze.addAll(schaetzeListe);
		
	}


	/**Das Monster verliert alle Schätze
	 * @return die Liste der Schätze die er besaß
	 */
	public List<Schatz> verliertAlles() {
		List<Schatz> verlust = schaetze;
		schaetze = new LinkedList<Schatz>();
		return verlust;
	}

	/**Prüft ob ein Monster Schätze besitzt
	 * @return true wenn er Schätze hat
	 */
	public boolean hatVermoegen() {
		// hat der Held Schätze
		return (schaetze.size() > 0);
	}

	/**wieviel Vermögen hat das Monster schon ?
	 * @return die Summe der GoldStücke der Schätze die es besitzt
	 */
	public int getVermögen() {
		// gibt das Vermögen zurück
		int vermoegen = 0;
		for (Schatz schatz : schaetze) {
			vermoegen += schatz.getValue();
		}
		return vermoegen;
	}

}
