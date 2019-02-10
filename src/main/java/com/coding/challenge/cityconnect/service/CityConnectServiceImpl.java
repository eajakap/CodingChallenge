package com.coding.challenge.cityconnect.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.challenge.cityconnect.model.CityConnect;
import com.coding.challenge.cityconnect.repo.CityConnectRepository;

/**
 * City Connect Service
 *
 */
@Service
public class CityConnectServiceImpl implements CityConnectService {
	private static final Logger logger = LoggerFactory.getLogger(CityConnectServiceImpl.class);

	@Autowired
	private CityConnectRepository cityConnectRepository;

	@Autowired
	private CityConnectGraph graph;

	@Autowired
	private CityConnectFileReader fileReader;

	/*
	 * Populate the City Connection Graph based on the entries read from the file.
	 */
	@PostConstruct
	private void populateGraph() {
		Map<String, CityConnect> ccMap = fileReader.getCityConnectMap();
		for (Map.Entry<String, CityConnect> entry : ccMap.entrySet()) {
			String origin = entry.getValue().getOrigin();
			String destination = entry.getValue().getDestination();
			createCityConnect(origin, destination);
			logger.info("Created CityConnect: " + origin + "," + destination);

		}
	}

	/*
	 * Creates City Connection Graph and persists the entries in the DB.
	 */
	public CityConnect createCityConnect(String origin, String destination) {
		CityConnect cityConnect = new CityConnect(origin, destination);
		graph.addNewEdge(origin, destination);
		List<CityConnect> cityConnectList = (List<CityConnect>) cityConnectRepository.findByOrigin(origin);
		if (cityConnectList == null) {
			return cityConnectRepository.save(cityConnect);
		} else if (!cityConnectList.contains(cityConnect)) {
			return cityConnectRepository.save(cityConnect);
		}
		return null;
	}

	/**
	 * Checks if the supplied cities are somehow connected with each other.
	 */
	@Override
	public boolean isConnected(String origin, String destination) {
		boolean result = graph.isConnected(origin, destination);
		return result;
	}

	/**
	 * Reads all the entries from the repository (DB).
	 */
	@Override
	public Iterable<CityConnect> lookup() {
		return cityConnectRepository.findAll();
	}

	/**
	 * Reads all the entries from the repository (DB) that match the "origin"
	 */
	@Override
	public Iterable<CityConnect> findByOrigin(String origin) {
		return cityConnectRepository.findByOrigin(origin);
	}

	/**
	 * Reads all the entries from the repository (DB) that match the "destination"
	 */
	@Override
	public Iterable<CityConnect> findByDestination(String destination) {
		return cityConnectRepository.findByDestination(destination);
	}

	/**
	 * Reads all the entries from the repository (DB) that match the "origin" and
	 * "destination"
	 */
	@Override
	public List<CityConnect> findByOriginDestination(String origin, String destination) {
		CityConnect cityConnect = new CityConnect(origin, destination);
		List<CityConnect> cityConnectList = (List<CityConnect>) cityConnectRepository.findByOrigin(origin);
		if (cityConnectList != null & cityConnectList.contains(cityConnect)) {
			int index = cityConnectList.indexOf(cityConnect);
			return Collections.singletonList(cityConnectList.get(index));
		}
		return null;
	}

	/**
	 * Counts the entries from the repository (DB).
	 */
	@Override
	public long total() {
		return cityConnectRepository.count();
	}

}
