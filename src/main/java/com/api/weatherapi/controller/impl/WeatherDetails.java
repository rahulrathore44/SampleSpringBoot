package com.api.weatherapi.controller.impl;

import java.util.List;

import com.api.weatherapi.converter.Unit;
import com.api.weatherapi.converter.impl.CelsiusImpl;
import com.api.weatherapi.converter.impl.FahrenheitImpl;
import com.api.weatherapi.exceptions.UnsupportedException;

public interface WeatherDetails {
	public CelsiusImpl getTempInCelsius();
	public FahrenheitImpl getTempInFahrenheitImpl() throws UnsupportedException;
	public List<Unit> getLastSevenDaysTemp(String aUnit) throws Exception;

}
