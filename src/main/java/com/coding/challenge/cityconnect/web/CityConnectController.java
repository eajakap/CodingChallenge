package com.coding.challenge.cityconnect.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.cityconnect.model.CityConnect;
import com.coding.challenge.cityconnect.service.CityConnectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * City Connect Controller
 *
 */
@RestController
@RequestMapping(path = "/")
@Api(value = "cityConnect", description = "Web service operations on city connections", tags = ("cityConnection"))
public class CityConnectController {
	private static final Logger logger = LoggerFactory.getLogger(CityConnectController.class);

	@Autowired
	private CityConnectService service;

	public CityConnectController() {

	}

	/**
	 * This method lists the cities connected by road. Lists all cities if "origin"
	 * and "destination" are either null or not supplied. Lists all cities connected
	 * with origin if "origin" is supplied. i.e. the output would be similar to
	 * query (SELECT * FROM TABLE) Lists all cities connected with destination if
	 * "destination" is supplied. i.e. the output would be similar to query (SELECT
	 * * FROM TABLE WHERE destination = CityB). Lists all cities connected with
	 * origin and destination if "origin" and "destination" is supplied. i.e. the
	 * output would be similar to query (SELECT * FROM TABLE WHERE origin = cityA
	 * AND destination = CityB)
	 * 
	 * @param origin      - String, origin city, optional
	 * @param destination - String, destination city optional
	 * @return - List<CityConnect> - list of connected cities
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "list")
	@ApiOperation(value = "Lists all Connected Cities", notes = "Gets all connected cities in the system", nickname = "getCityConnections")
	public List<CityConnect> findAll(@RequestParam(name = "origin", required = false) String origin,
			@RequestParam(name = "destination", required = false) String destination) {
		logger.info("findAll: origin= " + origin + ", destination= " + destination);
		if (StringUtils.isNotEmpty(origin) && StringUtils.isNotEmpty(destination)) {
			return (List<CityConnect>) service.findByOriginDestination(origin, destination);
		} else if (StringUtils.isNotEmpty(origin)) {
			return (List<CityConnect>) service.findByOrigin(origin);
		} else if (StringUtils.isNotEmpty(destination)) {
			return (List<CityConnect>) service.findByDestination(destination);
		}
		return (List<CityConnect>) service.lookup();
	}

	/**
	 * This method checks if the cities are connected by road.
	 * 
	 * @param origin      - String, origin city, optional
	 * @param destination - String, destination city optional
	 * @return - String - "YES" if cities connected else "NO"
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, path = "connected")
	@ApiOperation(value = "Checks for connected Cities.", notes = "Returns YES for Connected Cities else NO", nickname = "isCityConnected")
	public String isCityConnected(@RequestParam(name = "origin", required = false) String origin,
			@RequestParam(name = "destination", required = false) String destination) {
		logger.info("isCityConnected: " + origin + "," + destination);
		boolean result = false;
		if (StringUtils.isNotEmpty(origin) && StringUtils.isNotEmpty(destination)) {
			result = service.isConnected(origin, destination);
		}
		logger.info("Service output : " + result);
		return (result ? "YES" : "NO");
	}
}
