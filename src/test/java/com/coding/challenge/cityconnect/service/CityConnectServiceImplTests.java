package com.coding.challenge.cityconnect.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.challenge.cityconnect.model.CityConnect;
import com.coding.challenge.cityconnect.repo.CityConnectRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityConnectServiceImplTests {

	@Autowired
	private CityConnectServiceImpl cityConnectService;

	@MockBean
	private CityConnectGraph graph;

	@MockBean
	private CityConnectFileReader fileReader;

	@MockBean
	private CityConnectRepository cityConnectRepository;

	@Test
	public void testFindByOrigin() {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("Newark", "Boston"));
		Mockito.when(cityConnectRepository.findByOrigin("Newark")).thenReturn(cityConnectedList);

		List<CityConnect> list = (List<CityConnect>) (cityConnectService.findByOrigin("Newark"));
		assertEquals(list.size(), 1);

	}

	@Test
	public void testFindByDestination() {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("Newark", "Boston"));
		Mockito.when(cityConnectRepository.findByDestination("Boston")).thenReturn(cityConnectedList);
		List<CityConnect> list = (List<CityConnect>) (cityConnectService.findByDestination("Boston"));
		assertEquals(list.size(), 1);
	}

	@Test
	public void testFindByOriginDestination() {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("Newark", "Boston"));
		Mockito.when(cityConnectRepository.findByOrigin("Newark")).thenReturn(cityConnectedList);
		List<CityConnect> list = (List<CityConnect>) (cityConnectService.findByOriginDestination("Newark", "Boston"));
		assertEquals(list.size(), 1);
	}

	@Test
	public void testLookup() {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("Newark", "Boston"));
		cityConnectedList.add(new CityConnect("Chicago", "Philadelphia"));
		cityConnectedList.add(new CityConnect("New York", "Boston"));
		cityConnectedList.add(new CityConnect("Newark", "New York"));
		Mockito.when(cityConnectRepository.findAll()).thenReturn(cityConnectedList);
		List<CityConnect> list = (List<CityConnect>) (cityConnectService.lookup());
		assertEquals(list.size(), 4);
	}

	@Test
	public void testTotal() {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("Newark", "Boston"));
		cityConnectedList.add(new CityConnect("Chicago", "Philadelphia"));
		cityConnectedList.add(new CityConnect("New York", "Boston"));
		cityConnectedList.add(new CityConnect("Newark", "New York"));
		Mockito.when(cityConnectRepository.count()).thenReturn((long) cityConnectedList.size());
		assertEquals(cityConnectService.total(), cityConnectedList.size());
	}

	@Test
	public void testIsConnected() {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("Newark", "Boston"));
		cityConnectedList.add(new CityConnect("Chicago", "Philadelphia"));
		cityConnectedList.add(new CityConnect("New York", "Boston"));
		cityConnectedList.add(new CityConnect("Newark", "New York"));
		Mockito.when(graph.isConnected("Newark", "Boston")).thenReturn(true);
		boolean result = cityConnectService.isConnected("Newark", "Boston");
		assertTrue(result);
	}

}
