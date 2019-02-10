package com.coding.challenge.cityconnect.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityConnectGraphTests {
	@Autowired
	private CityConnectGraph cityConnectGraph;

	@Test
	public void testCityConnectGraph() {
		cityConnectGraph.addNewEdge("A", "E");
		cityConnectGraph.addNewEdge("B", "C");
		cityConnectGraph.addNewEdge("C", "D");
		cityConnectGraph.addNewEdge("A", "C");
		cityConnectGraph.addNewEdge("D", "A");
		cityConnectGraph.addNewEdge("A", "F");
		cityConnectGraph.printCityConnectGraph();
		assertTrue(cityConnectGraph.isConnected("B", "D"));
		assertTrue(cityConnectGraph.isConnected("B", "C"));
		assertFalse(cityConnectGraph.isConnected("A", "M"));
		cityConnectGraph.removeEdge("A", "F");
		assertFalse(cityConnectGraph.isConnected("A", "F"));
		cityConnectGraph.addNewEdge("P", "Q");
		assertTrue(cityConnectGraph.isConnected("P", "Q"));
		cityConnectGraph.printCityConnectGraph();
		cityConnectGraph.removeEdge("P", "Q");
		assertFalse(cityConnectGraph.isConnected("P", "Q"));
		cityConnectGraph.printCityConnectGraph();
	}

}
