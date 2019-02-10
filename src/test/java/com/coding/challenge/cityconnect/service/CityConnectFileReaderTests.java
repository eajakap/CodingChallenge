package com.coding.challenge.cityconnect.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.challenge.cityconnect.model.CityConnect;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityConnectFileReaderTests {

	@Autowired
	private CityConnectFileReader reader;

	@Test
	public void testCityConnectFileReader() {
		assertNotNull(reader.getCityConnectMap());
		assertEquals(7, reader.getCityConnectMap().size());
		assertNotNull(reader.getCityConnectMap().containsValue(new CityConnect("Boston", "New York")));
		assertNotNull(reader.getCityConnectMap().containsValue(new CityConnect("Philadelphia", "Newark")));
		assertNotNull(reader.getCityConnectMap().containsValue(new CityConnect("Newark", "Boston")));
		assertNotNull(reader.getCityConnectMap().containsValue(new CityConnect("Trenton", "Albany")));
		assertNotNull(reader.getCityConnectMap().containsValue(new CityConnect("Boston", "Chicago")));
		assertNotNull(reader.getCityConnectMap().containsValue(new CityConnect("New York", "Princeton")));
	}

}
