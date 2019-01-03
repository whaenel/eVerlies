package de.nikolauspflege.bbw.fia.eVerlies;

public class AngriffsErgebnis {
	public enum Ergebnis { DANEBEN,SCHATZKARTE,SCHATZKARTEN,VERWUNDET,TOT};
	private Ergebnis ergebnis = Ergebnis.DANEBEN;
	private int goldStücke = 0;
	private int verlohreneSchaetze = 0;
	private int aussetzRunden = 0;
	
	
	public Ergebnis getErgebnis() {
		return ergebnis;
	}
	public void setErgebnis(Ergebnis ergebnis) {
		this.ergebnis = ergebnis;
	}
	public int getGoldStücke() {
		return goldStücke;
	}
	public void setGoldStücke(int goldStücke) {
		this.goldStücke = goldStücke;
	}
	public int getVerlohreneSchaetze() {
		return verlohreneSchaetze;
	}
	public void setVerlohreneSchaetze(int verlohreneSchaetze) {
		this.verlohreneSchaetze = verlohreneSchaetze;
	}
	public int getAussetzRunden() {
		return aussetzRunden;
	}
	public void setAussetzRunden(int aussetzRunden) {
		this.aussetzRunden = aussetzRunden;
	}
	
	

}
