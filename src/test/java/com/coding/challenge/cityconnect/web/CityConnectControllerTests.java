package com.coding.challenge.cityconnect.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.coding.challenge.cityconnect.model.CityConnect;
import com.coding.challenge.cityconnect.service.CityConnectService;

@RunWith(SpringRunner.class)
@WebMvcTest(CityConnectController.class)
public class CityConnectControllerTests {

	@MockBean
	private CityConnectService cityConnectService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getConnectedCitiesList() throws Exception {
		List<CityConnect> cityConnectedList = new ArrayList<>();
		cityConnectedList.add(new CityConnect("NEWARK", "BOSTON"));

		Mockito.when(cityConnectService.lookup()).thenReturn(cityConnectedList);
		this.mockMvc.perform(get("/list")).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"origin\":\"NEWARK\",\"destination\":\"BOSTON\"")));

		Mockito.when(cityConnectService.findByOrigin("Newark")).thenReturn(cityConnectedList);
		this.mockMvc.perform(get("/list?origin=Newark")).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"origin\":\"NEWARK\",\"destination\":\"BOSTON\"")));

		Mockito.when(cityConnectService.findByDestination("Boston")).thenReturn(cityConnectedList);
		this.mockMvc.perform(get("/list?destination=Boston")).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"origin\":\"NEWARK\",\"destination\":\"BOSTON\"")));

		Mockito.when(cityConnectService.findByOriginDestination("Newark", "Boston")).thenReturn(cityConnectedList);
		this.mockMvc.perform(get("/list?origin=Newark&destination=Boston")).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"origin\":\"NEWARK\",\"destination\":\"BOSTON\"")));
	}

	@Test
	public void isConnectedCities() throws Exception {
		Mockito.when(cityConnectService.isConnected("Newark", "Boston")).thenReturn(true);
		this.mockMvc.perform(get("/connected?origin=Newark&destination=Boston")).andExpect(status().isOk())
				.andExpect(content().string(containsString("YES")));

		Mockito.when(cityConnectService.isConnected("Newark", "Chicago")).thenReturn(false);
		this.mockMvc.perform(get("/connected?origin=Newark&destination=Chicago")).andExpect(status().isOk())
				.andExpect(content().string(containsString("NO")));

	}

}
