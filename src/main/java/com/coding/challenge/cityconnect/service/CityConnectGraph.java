package com.coding.challenge.cityconnect.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class creates a graph of the cities that are connected.
 * 
 * @author ajayk
 *
 */
/**
 * @author ajayk
 *
 */
@Component
public class CityConnectGraph {
	private static final Logger logger = LoggerFactory.getLogger(CityConnectGraph.class);

	// data structure for adjacency list node
	static class Vertex {
		String label;

		Vertex(String label) {
			this.label = label;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Vertex vertex = (Vertex) o;
			return Objects.equals(label, vertex.label);
		}

		@Override
		public int hashCode() {
			return Objects.hash(label);
		}
	};

	// A map of lists to represent adjacency list
	private Map<Vertex, List<Vertex>> adjVertices;

	/**
	 * Constructor
	 */
	public CityConnectGraph() {
		this.adjVertices = new HashMap<>();
	}

	/**
	 * Getter method
	 * 
	 * @return - Map<Vertex, List<Vertex>> - Adjacent vertices map
	 */
	private Map<Vertex, List<Vertex>> getAdjVertices() {
		return adjVertices;
	}

	/**
	 * Add the city to the graph
	 * 
	 * @param label - String, City
	 */
	private void addVertex(String label) {
		adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	}

	/**
	 * Removes the city from the graph
	 * 
	 * @param label - String, City
	 */
	private void removeVertex(String label) {
		Vertex v = new Vertex(label);
		adjVertices.values().stream().map(e -> e.remove(v)).collect(Collectors.toList());
		adjVertices.remove(new Vertex(label));
	}

	/**
	 * Adds the edge between the cities to the graph
	 * 
	 * @param label1 - String, Origin City
	 * @param label2 - String, Destination City
	 */
	private void addEdge(String label1, String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		adjVertices.get(v1).add(v2);
		adjVertices.get(v2).add(v1);
	}

	/**
	 * Adds the new edge between the cities to the graph
	 * 
	 * @param label1 - String, Origin City
	 * @param label2 - String, Destination City
	 */
	public void addNewEdge(String label1, String label2) {
		addVertex(label1);
		addVertex(label2);
		addEdge(label1, label2);
	}

	/**
	 * Removes the edge between the cities to the graph
	 * 
	 * @param label1 - String, Origin City
	 * @param label2 - String, Destination City
	 */
	public void removeEdge(String label1, String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		List<Vertex> eV1 = adjVertices.get(v1);
		List<Vertex> eV2 = adjVertices.get(v2);
		if (eV1 != null)
			eV1.remove(v2);
		if (eV2 != null)
			eV2.remove(v1);
	}

	/**
	 * Returns the adjacent Vertices as per the graph matching the "label"
	 * 
	 * @param label - String, Origin City
	 * @return -List<Vertex> - List of adjacent vertices.
	 */
	private List<Vertex> getAdjVertices(String label) {
		return adjVertices.get(new Vertex(label));
	}

	/**
	 * Traverses the Graph and return the list of possible connection as per the
	 * graph network.
	 * 
	 * @param graph - CityConnectGraph
	 * @param root  - root node from where to start search.
	 * @return
	 */
	private static Set<String> breadthFirstTraversal(CityConnectGraph graph, String root) {
		Set<String> visited = new LinkedHashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			String vertex = queue.poll();
			for (Vertex v : graph.getAdjVertices(vertex)) {
				if (!visited.contains(v.label)) {
					visited.add(v.label);
					queue.add(v.label);
				}
			}
		}
		return visited;
	}

	/*
	 * print adjacency list representation of graph
	 */
	public void printCityConnectGraph() {
		logger.debug("Print City Graph");
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Vertex, List<Vertex>> entry : getAdjVertices().entrySet()) {
			Vertex src = entry.getKey();
			for (Vertex dest : entry.getValue()) {
				sb.append("(" + src.label + " --> " + dest.label + ")\t");
			}
			sb.append("\n");
		}
		logger.debug("City Graph:\n" + sb.toString());
	}

	/**
	 * Checks if the cities are connected as per graph representation.
	 * 
	 * @param source      - String, Origin city
	 * @param destination - String, Destination City
	 * @return
	 */
	public boolean isConnected(String source, String destination) {
		Set<String> destinations = breadthFirstTraversal(this, source);
		return destinations.contains(destination);
	}

}
