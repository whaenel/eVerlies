package de.nikolauspflege.bbw.fia.eVerlies;

public class Held {

	private int aussetzZaehler;
	private String name;
	private Wuerfel w1;
	private Wuerfel w2;
	private boolean isAlive=true;

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
		// TODO Auto-generated method stub
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

}
