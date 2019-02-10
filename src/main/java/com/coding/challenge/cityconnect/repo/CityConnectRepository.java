package com.coding.challenge.cityconnect.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coding.challenge.cityconnect.model.CityConnect;

/**
 * City Connect Repository Interface
 *
 */
@Repository
public interface CityConnectRepository extends CrudRepository<CityConnect, String> {

	/**
	 * Returns the list of cities (entries) that start with "origin" from the
	 * repository.
	 * 
	 * @param origin - Origin city
	 * @return - Iterable<CityConnect> - List of CityConnect entries
	 */
	Iterable<CityConnect> findByOrigin(@Param("origin") String origin);

	/**
	 * Returns the list of cities (entries) that end with "destination" from the
	 * repository.
	 * 
	 * @param destination - Destination city
	 * @return - Iterable<CityConnect> - List of CityConnect entries
	 */
	Iterable<CityConnect> findByDestination(@Param("destination") String destination);

}
