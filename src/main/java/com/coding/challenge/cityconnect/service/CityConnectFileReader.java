package com.coding.challenge.cityconnect.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.coding.challenge.cityconnect.model.CityConnect;

/**
 * Reads the "CityConnect" file. This file has entries in the format -
 * 
 * Boston, New York
 * 
 * Philadelphia, Newark
 * 
 * Newark, Boston
 * 
 * Trenton, Albany
 * 
 * @author ajayk
 *
 */
@Component
public class CityConnectFileReader {
	private static final Logger logger = LoggerFactory.getLogger(CityConnectFileReader.class);

	private static final String CityConnectFilepath = "C:\\Temp\\";

	private static final String CityConnectFilename = "CityConnect.txt";

	private Map<String, CityConnect> cityConnectMap = new HashMap<>();

	public CityConnectFileReader() {
		this(null);
	}

	public CityConnectFileReader(String filename) {
		readCityConnectFile(filename);
	}

	public Map<String, CityConnect> getCityConnectMap() {
		return cityConnectMap;
	}

	/**
	 * Generates the hash map key based on the supplied input parameters.
	 * 
	 * @param origin      - Origin city
	 * @param destination - Destination city
	 * @return String - key to Hashmap entry
	 */
	private String getCityConnectMapKey(String origin, String destination) {
		StringBuilder sb = new StringBuilder();
		sb.append(origin).append(":").append(destination);
		return sb.toString();
	}

	/**
	 * Adds the city to the cityConnectMap Map
	 * 
	 * @param line - String, "Boston, New York"
	 */
	private void addToCityConnectMap(String line) {
		if (StringUtils.isNotEmpty(line)) {
			String[] cities = line.split(",");
			if (cities.length > 1) {
				String origin = cities[0].trim();
				String destination = cities[1].trim();
				String key = getCityConnectMapKey(origin, destination);
				cityConnectMap.putIfAbsent(key, new CityConnect(origin, destination));
			}
		}
	}

	/**
	 * Prints the cityConnectMap.
	 * 
	 * @param ccMap - cityConnectMap
	 */
	private static void printCityConnectMap(Map<String, CityConnect> ccMap) {
		logger.debug("Printing CityConnectMap: " + ccMap.size());
		for (Map.Entry<String, CityConnect> entry : ccMap.entrySet()) {
			String key = entry.getKey();
			CityConnect ccr = entry.getValue();
			logger.debug(key + " ===> " + ccr);
		}
	}

	/**
	 * Reads the CityConnect file - "CityCopnnect.txt"
	 * 
	 * @param filename
	 */
	private void readCityConnectFile(String filename) {
		String defaultFilename = CityConnectFilename + CityConnectFilename;
		File file = new File(StringUtils.isNotEmpty(filename) ? filename : defaultFilename);
		InputStream is = null;
		try {
			is = (file.exists()) ? new FileInputStream(file)
					: getClass().getClassLoader().getResourceAsStream(CityConnectFilename);
			logger.debug("Total file size to read (in bytes) : " + is.available());
			BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				// add the city Connection line to the repository.
				addToCityConnectMap(line);
				// read the next line
				line = br.readLine();
			}
			String everything = sb.toString();
			logger.debug("Processing File Data [ \n" + everything + "]");
		} catch (IOException ex) {
			logger.debug("Got Exception while file reading");
			ex.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ex) {
				logger.debug("Got Exception while file closing");
				ex.printStackTrace();
			}
		}
		printCityConnectMap(cityConnectMap);
	}
}
