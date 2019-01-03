package de.nikolauspflege.bbw.fia.eVerlies;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeldTest {

	Abenteurer held = new Held("Waschlappen");
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testHeld() {
		assertTrue(held.isAlive(), "Held sollte am anfang leben");
		assertEquals(0,held.getVermögen(),"Held hat am anfang kein Geld");
		assertEquals("Waschlappen", held.getName(), "Held sollte seinen Namen Beibehalten");
	}

	@Test
	void testSetztAus() {
		held.mussAussetzen(1);
		assertTrue(held.mussAussetzen(), "held sollte aussetzen");
		held.setztAus();
		assertFalse(held.mussAussetzen(), "held sollte jezt nicht mehr aussetzen müssen");

	}

	@Test
	void testGreiftAn() {
		boolean result = true ;
		for (int i = 0; i < 60; i++) {
			int wurf = held.greiftAn() ;
			if ((wurf < 2) || (wurf > 12 ) ) {
				result = false;
			}
			
		}
		assertTrue(result);
	}


	@Test
	void testStirbt() {
		assertTrue(held.isAlive(), "Held sollte am anfang leben");
		held.stirbt();
		assertFalse(held.isAlive(), "Held sollte nun tod sein");
	}



	@Test
	void testGetVermögen() {
		assertEquals(0,held.getVermögen(),"Held hat am anfang kein Geld");
		assertFalse(held.hatVermoegen(), "Held hat am anfang kein Geld");
		Schatz schatz = new Schatz(0);
		List <Schatz> schaetzeListe = new LinkedList<>();
 		held.addAll(schaetzeListe);
		assertEquals(0,held.getVermögen(),"Held hat nach einer leeren Schatzliste immer noch kein  Geld");
		schaetzeListe.add(schatz);
 		held.addAll(schaetzeListe);
		assertEquals(100,held.getVermögen(),"Schatz aus level 0 sollte 100 Goldstücke sein");
		assertTrue(held.hatVermoegen(), "Held hat nun Geld und damit ein Vermögen");
		schatz = new Schatz(0);
		schaetzeListe.set(0, schatz);
		schatz = new Schatz(0);
		schaetzeListe.add( schatz);
		assertEquals(100,held.getVermögen(),"Schatz aus level 0 sollte 300 Goldstücke sein");
		
	}

	@Test
	void testVerliertAlles() {
		Schatz schatz = new Schatz(0);
		List <Schatz> schaetzeListe = new LinkedList<>();
		schaetzeListe.add(schatz);
		held.addAll(schaetzeListe);
		assertTrue(held.hatVermoegen(), "Held hat nun Geld und damit ein Vermögen");
		List <Schatz> verlustListe = held.verliertAlles();
		assertEquals(1,verlustListe.size(), "verluste sollten eine Schatzkarte sein");
		assertFalse(held.hatVermoegen(), "Held hat nun kein Geld mehr");
		schatz = new Schatz(0);
		verlustListe.add(schatz);
		schatz = new Schatz(0);
		verlustListe.add(schatz);
		held.addAll(verlustListe); // hat nun 3 Schätze
		assertTrue(held.hatVermoegen(), "Held hat nun 3 Schätze");
		verlustListe = held.verliertAlles();
		assertEquals(3,verlustListe.size(), "verluste sollten drei Schatzkarten sein");
		assertFalse(held.hatVermoegen(), "Held hat nun kein Geld mehr");

	}


	@Test
	void testZiehtSichZurueck() {
		Monster monster;
		int i = 0;
		boolean ueberlebteEinmal = false ;
		Schatz schatz = new Schatz(0);
		List <Schatz> schaetzeListe = new LinkedList<>();
		schaetzeListe.add(schatz);
		held.addAll(schaetzeListe);

		while ((i< 1000 )&&  ( held.isAlive())) {
			monster = new Monster(i);
			monster.add(new Schatz(i));
			held.wirdAngegriffen(monster);
			ueberlebteEinmal |= held.isAlive();
		}
		assertTrue(ueberlebteEinmal, "Held sollte es mindestens einmal überleben");
		assertTrue((i < 999), " held sollte eher sterben ");
		
	}

}
