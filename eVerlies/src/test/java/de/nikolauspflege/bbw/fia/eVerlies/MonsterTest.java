package de.nikolauspflege.bbw.fia.eVerlies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonsterTest {

	@Test
	void testGetSiegespunkte() {
		int [] values = new int[13];
		boolean result = true;
		for (int i = 0; i < values.length; i++) {
			values[i]= 0;
		}
		
		
		Monster mon = new Monster(10);
		for (int i = 0; i < 1000; i++) {
			mon= new Monster(10);
			int j = mon.getSiegespunkte();
			values[j]++;
		}
		assertEquals(0,values[0]);
		assertEquals(0,values[1]);
		assertEquals(0,values[2]);
		assertEquals(0,values[3]);
		for (int i = 4; i < values.length; i++) {
			System.out.println("Got value "+i+ " for " + values[i]+ " times");
			result &= (values[i] != 0);
		}
		assertTrue(result);
		
		for (int i = 0; i < values.length; i++) {
			values[i]= 0;
		}
		for (int i = 0; i < 1000; i++) {
			mon= new Monster(5);
			int j = mon.getSiegespunkte();
			values[j]++;
		}
		assertEquals(0,values[0]);
		assertEquals(0,values[1]);
		assertEquals(0,values[2]);
		assertEquals(0,values[3]);
		for (int i = 4; i < 9; i++) {
			System.out.println("Got value "+i+ " for " + values[i]+ " times");
			result &= (values[i] != 0);
		}
		assertTrue(result);
		
		mon = new Monster(1) ;
		assertEquals(4,mon.getSiegespunkte());

	}

}
