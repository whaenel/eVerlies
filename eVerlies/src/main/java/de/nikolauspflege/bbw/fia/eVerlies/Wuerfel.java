package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Random;

/**Ein Würfel mit variabler anzahl von Seiten
 * @author walter
 *
 */
public class Wuerfel {

	private int seiten;
	private Random rand;

	/**Erzeuge einen neuen Würfel 
	 * @param seiten die Anzahl der Wüfrelseiten.
	 */
	public Wuerfel(int seiten) {
		// setze anzahl seiten
		this.seiten = seiten;
		this.rand = new Random();
	}

	/**Wüerfeln gibt eine zufällige Zahl zwischen 1 und der Anzahl der Seiten des Würfels zurück
	 * @return die gewürfelte Augenzahl
	 */
	public int wuerfeln() {
		return rand.nextInt(seiten)+1;
	}

}
