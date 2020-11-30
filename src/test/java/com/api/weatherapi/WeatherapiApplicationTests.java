package com.api.weatherapi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.api.weatherapi.controller.WeatherDataController;
import com.api.weatherapi.converter.type.ConversionType;

@WebMvcTest(controllers = { WeatherDataController.class })
public class WeatherapiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void test_get_data_in_celsius() throws Exception {
		mvc.perform(get("/TempInCelsius").accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	public void test_get_data_in_celsius_verify_unit_and_value() throws Exception {
		mvc.perform(get("/TempInCelsius").accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.unitType", equalToIgnoringCase("Celsius")))
				.andExpect(jsonPath("$.value", is(notNullValue())));
	}

	@Test
	public void test_get_data_in_Fahrenheit() throws Exception {
		mvc.perform(get("/TempInFahrenheit").accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	@Test
	public void test_get_data_in_Fahrenheit_verify_unit_and_value() throws Exception {
		mvc.perform(get("/TempInFahrenheit").accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.unitType", equalToIgnoringCase("Fahrenheit")))
				.andExpect(jsonPath("$.value", is(notNullValue())));
	}

	@Test
	public void test_get_data_for_lastsevendays_In_Celsius() throws Exception {
		mvc.perform(get("/LastSevenDaysTemp").accept(APPLICATION_JSON_VALUE).queryParam("type",
				ConversionType.Celsius.name())).andExpect(status().isOk());
	}

	@Test
	public void test_get_data_for_lastsevendays_In_Fahrenheit() throws Exception {
		mvc.perform(get("/LastSevenDaysTemp").accept(APPLICATION_JSON_VALUE).queryParam("type",
				ConversionType.Fahrenheit.name())).andExpect(status().isOk());
	}

	@Test
	public void test_get_data_for_lastsevendays_without_type_should_return_400() throws Exception {
		mvc.perform(get("/LastSevenDaysTemp").accept(APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest());
	}

	@Test
	public void test_get_data_for_lastsevendays_with_invalid_type_should_return_400() throws Exception {
		mvc.perform(get("/LastSevenDaysTemp").accept(APPLICATION_JSON_VALUE).queryParam("type", "a"))
				.andExpect(status().isInternalServerError());
	}
}
