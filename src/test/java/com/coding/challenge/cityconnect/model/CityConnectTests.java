package com.coding.challenge.cityconnect.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityConnectTests {

	@Test
	public void testCityConnect() {
		CityConnect cc1 = new CityConnect("Trenton", "Chicago");
		assertEquals("Trenton", cc1.getOrigin());
		assertEquals("Chicago", cc1.getDestination());
		CityConnect cc2 = new CityConnect("Trenton", "Boston");
		assertEquals("Trenton", cc2.getOrigin());
		assertEquals("Boston", cc2.getDestination());
		assertEquals(null, cc1.getId());
		assertNotEquals(cc1, cc2);
		CityConnect cc3 = new CityConnect();
		cc3.setOrigin("Trenton");
		cc3.setDestination("Baltimore");
		assertTrue(cc3.toString().contains("Trenton"));
		assertTrue(cc3.toString().contains("Baltimore"));
		assertTrue(cc1.equals(cc1));
		assertFalse(cc1.equals(null));
		assertTrue(cc1.hashCode() == cc1.hashCode());
	}

}
