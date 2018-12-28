package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.LinkedList;
/*Die Klasse Abenteurer bekämpft die Monster. Dazu benötigt sie 2 Würfel um damit den Angriff zu starten.
* Wenn er das Monster besiegt kann er den Schatz des Monsters in seine Schatztruhe legen.
* Schlägt er daneben, muss er sich um seinen Rückzug kümmern. Dabei kann er einen, zwei oder gar alle Schätze an das Monster verlieren und
* muss möglicherweise aussetzen.
* Wenn er Pech hat stirbt er sogar.
* Natürlich hat unser Abenteurer auch einen Namen, wie alle Schatzbesitzer
* @author walter
*
*/
public abstract class Abenteurer extends Schatzbesitzer {

	private int aussetzZaehler;
	protected Wuerfel w1;
	protected Wuerfel w2;
	private boolean isAlive = true;
	private Type type  = Type.HELD;
	enum Type  {HELD,ELVE};

	public Abenteurer() {
		super();
		w1 = new Wuerfel(6);
		w2 = new Wuerfel(6);

	}

	public Type getType() {
		return type;
	}

	protected void setType(Type type) {
		this.type = type;
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

	/**Rückzug nachdem er das Monster nicht erledigt hat
	 * macht einen Rettungswurf mit 2 6er Würfel
	 * 7,11 nichts passiert da das Monster daneben schlägt
	 * 6,8  der Held verliert eine Schatzkarte an das Monster, wenn er eine hat
	 * 4,5,9,10 der Held verliert 2 Schatzkarten an das Monster und muß eine Runde aussetzen
	 * 3,12 Der Held verliert alle Schatzkarten an das Monster
	 * 2 Der Held stirbt
	 * @param monster
	 * @return 
	 */

}