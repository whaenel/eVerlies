package de.nikolauspflege.bbw.fia.eVerlies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeldTest {

	Held held = new Held("Waschlappen");
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
		assertEquals(true,held.setztAus(), "held sollte aussetzen");
	}

	@Test
	void testGreiftAn() {
		fail("Not yet implemented");
	}

	@Test
	void testIsAlive() {
		fail("Not yet implemented");
	}

	@Test
	void testStirbt() {
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	void testMussAussetzen() {
		fail("Not yet implemented");
	}

	@Test
	void testGetVermögen() {
		fail("Not yet implemented");
	}

	@Test
	void testVerliertAlles() {
		fail("Not yet implemented");
	}

	@Test
	void testAddAll() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveSchatz() {
		fail("Not yet implemented");
	}

	@Test
	void testHatVermoegen() {
		fail("Not yet implemented");
	}

	@Test
	void testZiehtSichZurueck() {
		fail("Not yet implemented");
	}

}
