package com.api.weatherapi.converter.impl;

import com.api.weatherapi.converter.Converter;
import com.api.weatherapi.converter.Unit;
import com.api.weatherapi.converter.type.ConversionType;
import com.api.weatherapi.exceptions.UnsupportedException;

public class FahrenheitImpl implements Converter, Unit {
	private double value;
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public ConversionType getUnitType() {
		return unitType;
	}

	private ConversionType unitType = ConversionType.Fahrenheit;
	
	public FahrenheitImpl(double value) {
		this.value = value;
		this.unitType = ConversionType.Fahrenheit;
	}

	public FahrenheitImpl() {
		this(27.0);
	}


	@Override
	public <T extends Unit> Unit convertTo(T targetType, ConversionType toType) throws UnsupportedException {
		switch (toType) {
		case Celsius:
			if (targetType instanceof CelsiusImpl)
				return (T) new CelsiusImpl(value);
			if (targetType instanceof FahrenheitImpl)
				return (T) new CelsiusImpl(convertToFahrenheit(((FahrenheitImpl)targetType).getValue()));
		case Fahrenheit:
			if (targetType instanceof FahrenheitImpl)
				return (T) new FahrenheitImpl(value);
		default:
			throw new UnsupportedException(String.format("The type %s is not supported", toType));
		}
	}
	
	private double convertToFahrenheit(double value) {
		return ((value - 32) * 5/9);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof CelsiusImpl))
			return false;
		FahrenheitImpl aObj = (FahrenheitImpl) obj;
		if (this.getValue() != aObj.getValue())
			return false;
		return true;
	}

}
