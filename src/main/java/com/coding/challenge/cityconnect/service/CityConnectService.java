package com.coding.challenge.cityconnect.service;

import java.util.List;

import com.coding.challenge.cityconnect.model.CityConnect;

/**
 * CityConnectService: City Connection Service provides the following
 * capabilities - 1. Helps the user to determine if the cities are connected by
 * road. 2. List the cities that are connected by road.
 * 
 * @author ajayk
 *
 */
public interface CityConnectService {

	/**
	 * Checks if the supplied cities are somehow connected with each other.
	 */
	boolean isConnected(String origin, String destination);

	/**
	 * Reads all the entries from the repository (DB).
	 */
	Iterable<CityConnect> lookup();

	/**
	 * Reads all the entries from the repository (DB) that match the "origin"
	 */
	Iterable<CityConnect> findByOrigin(String origin);

	/**
	 * Reads all the entries from the repository (DB) that match the "destination"
	 */
	Iterable<CityConnect> findByDestination(String destination);

	/**
	 * Reads all the entries from the repository (DB) that match the "origin" and
	 * "destination"
	 */
	List<CityConnect> findByOriginDestination(String origin, String destination);

	/**
	 * Counts the entries from the repository (DB).
	 */
	long total();

}
