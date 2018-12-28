package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.LinkedList;

/**Die Klasse Elve ist auch ein Abenteurer. Zu ihren besonderen Fähigkeiten gehört es,
 * dass sie zaubern kann, Geheimtüren leichter öffnen kann und beim Verteidigen mehr Gück hat.
 * @author walter
 *
 */
public class Elve extends Abenteurer {

	/** Erzeuge einen neuen Helden
	 * @param name des Name des Helden
	 */
	public Elve(String name) {
		super();
		// setze den Namen
		this.name = name;
		this.setType(Type.ELVE);
	}

	/** Der Held macht den Angriffswurf mit 2 6er Würfel 
	 * @return die Gesamtzahl der Würfel-Punkte
	 */
	@Override
	public int greiftAn() {
		// mit 2 Würfeln wurfeln
		return w1.wuerfeln() + w2.wuerfeln()      ;
	}

	/**Rückzug nachdem er das Monster nicht erledigt hat
	 * macht einen Rettungswurf mit 2 6er Würfel
	 * 7,11,12 nichts passiert da das Monster daneben schlägt
	 * 6,8  der Held verliert eine Schatzkarte an das Monster, wenn er eine hat
	 * 4,5,9,10 der Held verliert 2 Schatzkarten an das Monster und muß eine Runde aussetzen
	 * 3 Der Held verliert alle Schatzkarten an das Monster
	 * 2 Der Held stirbt
	 * @param monster
	 * @return 
	 */
	@Override
	public AngriffsErgebnis wirdAngegriffen(Schatzbesitzer monster) {
		// mache den Rettungswurf
		int rettungsWurf = w1.wuerfeln() + w2.wuerfeln();
		switch (rettungsWurf) {
		case 7:
		case 11:
		case 12:
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
		return null;
		
	}


	@Override
	public int getSiegespunkte() {
		// TODO Auto-generated method stub
		return 0;
	}
}
