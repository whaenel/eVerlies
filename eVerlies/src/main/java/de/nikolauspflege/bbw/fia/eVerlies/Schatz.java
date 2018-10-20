package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Random;

/**Ein Schat hat einen Wert in Goldstücken.  Je höher der Level,
 * desto warscheinlicher ist der Schatz grösser.
 * @author walter
 *
 */
public class Schatz {

	private int value;

	/**Erzeuge einen Schatz für einen Spile level
	 * Der Kleinste Schatz ist 100 Goldstücke
	 * @param level
	 */
	public Schatz(int level) {
		// Schatz ist 100 Goldstücke plus 0..4+level+50 Goldstücke
		Random rand = new Random();
		value = 100 ;
		if (level >=1) {
			value += ( rand.nextInt(level*4)*50);
		}
	}

	/**Was ist der Wert des Schatzes in Goldstücken
	 * @return den Wert des Schatzes
	 */
	public int getValue() {
		return value;
	}

}
