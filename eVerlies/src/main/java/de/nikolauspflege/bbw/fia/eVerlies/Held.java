package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.LinkedList;

import de.nikolauspflege.bbw.fia.eVerlies.AngriffsErgebnis.Ergebnis;

/**Die Klasse Held bekämpft die Monster. Dazu benötigt sie 2 Würfel um damit den Angriff zu starten.
 * Wenn er das Monster besiegt kann er den Schatz des Monsters in seine Schatztruhe legen.
 * Schlägt er daneben, muss er sich um seinen Rückzug kümmern. Dabei kann er einen, zwei oder gar alle Schätze an das Monster verlieren und
 * muss möglicherweise aussetzen.
 * Wenn er Pech hat stirbt er sogar.
 * Natürlich hat unser Held auch einen Namen.
 * @author walter
 *
 */
public class Held extends Abenteurer {

	private int letzterWurf;

	/** Erzeuge einen neuen Helden
	 * @param name des Name des Helden
	 */
	public Held(String name) {
		super();
		// setze den Namen
		this.name = name;
		this.setType(Type.HELD);
	}

	/** Der Held macht den Angriffswurf mit 2 6er Würfel 
	 * @return die Gesamtzahl der Würfel-Punkte
	 */
	@Override
	public int greiftAn() {
		// mit 2 Würfeln wurfeln
		letzterWurf =w1.wuerfeln() + w2.wuerfeln() ;
		return    letzterWurf  ;
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
	@Override
	public AngriffsErgebnis wirdAngegriffen(Schatzbesitzer monster) {
		// mache den Rettungswurf
		int rettungsWurf = w1.wuerfeln() + w2.wuerfeln();
		AngriffsErgebnis result = new AngriffsErgebnis();
		switch (rettungsWurf) {
		case 7:
		case 11:
			// nichts passiert 
			// System.out.println("Der Held hat Glück, denn das Monster schlägt daneben");
			// rettungswurf passt
			break;
		case 6:
		case 8:
			// verlust einer Schatzkarte 
			result.setErgebnis(Ergebnis.SCHATZKARTE);
			if (!hatVermoegen()) {
				result.setVerlohreneSchaetze(0);
				//System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
			} else {
				// verlust des 1. Schatzes
				Schatz verlust = schaetze.remove(0);
				//System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken an das Monster (" + monster.getSiegespunkte() +  ")");
				monster.add(verlust);
				result.setVerlohreneSchaetze(1);
				result.setGoldStücke(verlust.getValue());
			}
			break;
		case 4:
		case 5:
		case 9:
		case 10:
			// verlust von 2 schatzkarten, ein mal aussetzen 
			result.setErgebnis(Ergebnis.SCHATZKARTEN);
			result.setAussetzRunden(1);
			if (!hatVermoegen()) {
				//System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
				result.setVerlohreneSchaetze(0);
			} else {
				// verlust des 1. Schatzes
				Schatz verlust = schaetze.remove(0);
				//System.out.println("Der Held verliert eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken an das Monster (" + monster.getSiegespunkte() +  ")");
				monster.add(verlust);
				result.setGoldStücke(verlust.getValue());
				if (!hatVermoegen()) {
					System.out.println("Der Held hat noch keine Schätze und kann daher auch nichts verlieren! Glück gehabt!");
					result.setVerlohreneSchaetze(1);
				} else {
					// verlust des 1. Schatzes
					verlust = schaetze.remove(0);
					//System.out.println("Der Held verliert noch eine Schatzkarte im Wert von " + verlust.getValue() + " Goldstücken an das Monster (" + monster.getSiegespunkte() +  ")");
					monster.add(verlust);
					result.setVerlohreneSchaetze(2);
					result.setGoldStücke(result.getGoldStücke() + verlust.getValue());
				}

			}
			this.mussAussetzen(1);
			break;
		case 3:
		case 12:
			int wert = getVermögen();
			monster.addAll(schaetze);
			int anzahl = schaetze.size();
			result.setErgebnis(Ergebnis.VERWUNDET);
			result.setGoldStücke(wert);
			result.setVerlohreneSchaetze(anzahl);

			schaetze = new LinkedList<Schatz>();
			//System.out.println("Der Held verliert alle Schatzkarten im Wert von " + wert + " Goldstücken");
			break;

		default:
			// der Held ist tot
			//System.out.println("Leider hat der Held den Kampf mit dem Monster verlohren und ist nun tot!"); 
			this.stirbt();
			wert = getVermögen();
			monster.addAll(schaetze);
			anzahl = schaetze.size();
			result.setErgebnis(Ergebnis.TOT);
			result.setGoldStücke(wert);
			result.setVerlohreneSchaetze(anzahl);

			schaetze = new LinkedList<Schatz>();

			break;
		}
		return  result;
	}

	@Override
	public int getSiegespunkte() {
		// TODO Auto-generated method stub
		return 12;
	}
	@Override
	public String toString() {
		return name+"("+letzterWurf+")";
	}
}
