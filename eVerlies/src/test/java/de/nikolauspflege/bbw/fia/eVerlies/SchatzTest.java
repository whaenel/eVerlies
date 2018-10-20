package de.nikolauspflege.bbw.fia.eVerlies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SchatzTest {

	@Test
	void testGetValue() {
		Schatz schatz = new Schatz(0);
		assertEquals(100, schatz.getValue(), "Der erwartete Wert vom Schatz stimmt nicht ");
		schatz = new Schatz(5);
		assertTrue(schatz.getValue() <= 100 + 4*4*50);
	}

}
