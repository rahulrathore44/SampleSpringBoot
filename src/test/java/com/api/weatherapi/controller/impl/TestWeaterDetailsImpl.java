package com.api.weatherapi.controller.impl;

import static com.api.weatherapi.converter.type.ConversionType.Celsius;
import static com.api.weatherapi.converter.type.ConversionType.Fahrenheit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.api.weatherapi.converter.Unit;
import com.api.weatherapi.converter.impl.CelsiusImpl;
import com.api.weatherapi.converter.impl.FahrenheitImpl;
import com.api.weatherapi.converter.type.ConversionType;
import com.api.weatherapi.exceptions.UnsupportedException;

public class TestWeaterDetailsImpl {

	private WeatherDetails weatherDetails = new WeatherDetailsImpl();

	@Test
	public void test_Call_to_getTempInCelsius() {
		CelsiusImpl celsiusImpl = weatherDetails.getTempInCelsius();
		Assertions.assertNotNull(celsiusImpl);
		Assertions.assertEquals(Celsius, celsiusImpl.getUnitType());
	}

	@Test
	public void test_Call_to_getLastSevenDaysTemp_Celsius() throws Exception {
		List<Unit> aList = weatherDetails.getLastSevenDaysTemp(ConversionType.Celsius.name());
		CelsiusImpl celsiusImpl = (CelsiusImpl) aList.get(0);
		Assertions.assertNotNull(celsiusImpl);
		Assertions.assertEquals(Celsius, celsiusImpl.getUnitType());
	}

	@Test
	public void test_Call_to_getLastSevenDaysTemp_Fahrenheit() throws Exception {
		List<Unit> aList = weatherDetails.getLastSevenDaysTemp(ConversionType.Fahrenheit.name());
		FahrenheitImpl fahrenheitImpl = (FahrenheitImpl) aList.get(0);
		Assertions.assertNotNull(fahrenheitImpl);
		Assertions.assertEquals(Fahrenheit, fahrenheitImpl.getUnitType());
	}

	@Test
	public void test_Call_to_getLastSevenDaysTemp_expect_exception() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			weatherDetails.getLastSevenDaysTemp("test");
		});
	}

	@Test
	public void test_Call_to_getTempInFahrenheitImpl() throws UnsupportedException {
		FahrenheitImpl fahrenheitImpl = weatherDetails.getTempInFahrenheitImpl();
		Assertions.assertNotNull(fahrenheitImpl);
		Assertions.assertEquals(Fahrenheit, fahrenheitImpl.getUnitType());
	}

}
