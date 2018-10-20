package de.nikolauspflege.bbw.fia.eVerlies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WuerfelTest {

	@Test
	void testWuerfeln() {
		Wuerfel w6 = new Wuerfel(6);
		boolean result = true ;
		for (int i = 0; i < 60; i++) {
			int wurf = w6.wuerfeln() ;
			if ((wurf < 1) || (wurf > 6 ) ) {
				result = false;
			}
			
		}
		assertTrue(result);
	}

}
