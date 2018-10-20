package de.nikolauspflege.bbw.fia.eVerlies;

import java.util.Random;

public class Wuerfel {

	private int seiten;
	private Random rand;

	public Wuerfel(int seiten) {
		// setze anzahl seiten
		this.seiten = seiten;
		this.rand = new Random();
	}

	public int wuerfeln() {
		// TODO Auto-generated method stub
		return rand.nextInt(seiten)+1;
	}

}
