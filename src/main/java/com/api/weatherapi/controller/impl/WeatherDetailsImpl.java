package com.api.weatherapi.controller.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.api.weatherapi.converter.Unit;
import com.api.weatherapi.converter.impl.CelsiusImpl;
import com.api.weatherapi.converter.impl.FahrenheitImpl;
import com.api.weatherapi.converter.type.ConversionType;
import com.api.weatherapi.exceptions.UnsupportedException;

public class WeatherDetailsImpl implements WeatherDetails {

	@Override
	public CelsiusImpl getTempInCelsius() {
		List<Unit> aList = getSevenDaysTempInCelsius();
		Unit aUnit = aList.get(aList.size() - 2);
		if(aUnit instanceof CelsiusImpl) {
			return (CelsiusImpl)aUnit;
		}
		throw new RuntimeException("Something Went Wrong");
	}

	@Override
	public FahrenheitImpl getTempInFahrenheitImpl() throws UnsupportedException {
		List<Unit> aList = getSevenDaysTempInFahrenheitImpl();
		Unit aUnit = aList.get(aList.size() - 2);
		if(aUnit instanceof FahrenheitImpl) {
			return (FahrenheitImpl)aUnit;
		}
		throw new RuntimeException("Something Went Wrong");
	}

	@Override
	public List<Unit> getLastSevenDaysTemp(String aUnit) throws IllegalArgumentException, UnsupportedException {
		if(aUnit == null || aUnit.isEmpty() || aUnit.isBlank())
			return getSevenDaysTempInCelsius();
		ConversionType type = ConversionType.valueOf(aUnit);
		switch (type) {
		case Fahrenheit:
			return getSevenDaysTempInFahrenheitImpl();
		default:
			return getSevenDaysTempInCelsius();
		}
	}
	
	private List<Unit> getSevenDaysTempInCelsius(){
		ArrayList<CelsiusImpl> aList = new ArrayList<CelsiusImpl>();
		for(int i = 15; i<= 50; i = i + ((int)(Math.random() * 10))) {
			CelsiusImpl aObj = new CelsiusImpl(Double.valueOf(i));
			aList.add(aObj);
		}
		return Collections.unmodifiableList(aList);
	}
	
	private List<Unit> getSevenDaysTempInFahrenheitImpl() throws UnsupportedException{
		ArrayList<Unit> aList = new ArrayList<Unit>(getSevenDaysTempInCelsius());
		ArrayList<FahrenheitImpl> bList = new ArrayList<FahrenheitImpl>();
		for (Unit unit : aList) {
			if( unit instanceof CelsiusImpl) {
				CelsiusImpl celsiusImpl = (CelsiusImpl)unit;
				bList.add((FahrenheitImpl) celsiusImpl.convertTo(celsiusImpl, ConversionType.Fahrenheit));
			}
		}
		return Collections.unmodifiableList(bList);
	}

}
