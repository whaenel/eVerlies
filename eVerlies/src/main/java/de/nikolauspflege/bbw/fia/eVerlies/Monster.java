package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.List;
import java.util.Random;

import de.nikolauspflege.bbw.fia.eVerlies.AngriffsErgebnis.Ergebnis;

/**Ein Monster ist der Gegner des Helden im Spiel. Wenn der Held mit einer Zahl >= den Siegespunkten des Monsters
 * angreift, gewinnt der Held. Damit bekommt er den Schatz des Monsters. Damit das MOnster auch einen Schatz in seiner Schatzkiste hat,
 * muss ihm am Anfang einer gegeben werden. Ein frisch erzeugtes Monster hat keinen.
 * @author walter
 *
 */
public class Monster extends Schatzbesitzer {

	private int siegespunkte;
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
		name="Monster";

	}

	/**Gib die Würfelpunkte zurück, die nötig sind, um das Monster zu besiegen
	 * @return die Anzahl der Punkte
	 */
	@Override
	public int getSiegespunkte() {
		return siegespunkte;
	}

	@Override
	public AngriffsErgebnis wirdAngegriffen(Schatzbesitzer held) {
		AngriffsErgebnis result= new AngriffsErgebnis();
		int angriffspunkte = held.greiftAn();
		if (angriffspunkte >= getSiegespunkte()) {
			// und bekommt den Schatz oder die Schätze des Monsters
			int wert = getVermögen();
			List<Schatz> schaetze = verliertAlles();
			result.setErgebnis(Ergebnis.TOT);
			result.setGoldStücke(wert);
			result.setVerlohreneSchaetze(schaetze.size());
//
//			if (schaetze.size() > 1) {
//				System.out.println(
//						"Held " + held.getName() + " erschlägt das Monster (" + monster.getSiegespunkte()
//								+ ") und bekommt die Schätze im Wert von " + wert + " Goldstücken");
//			} else {
//				System.out.println(
//						"Held " + held.getName() + " erschlägt das Monster (" + monster.getSiegespunkte()
//								+ ") und bekommt den Schatz im Wert von " + wert + " Goldstücken");
//			}
			held.addAll(schaetze);
		} 
		return result;
	}

	@Override
	public int greiftAn() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		return name+"("+ siegespunkte +")";
	}

}
