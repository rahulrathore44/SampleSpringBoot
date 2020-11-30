package com.api.weatherapi.converter.impl;

import static com.api.weatherapi.converter.type.ConversionType.Celsius;
import static com.api.weatherapi.converter.type.ConversionType.Fahrenheit;
import static com.api.weatherapi.converter.type.ConversionType.Kelvin;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.api.weatherapi.converter.type.ConversionType;
import com.api.weatherapi.exceptions.UnsupportedException;

public class TestCelsiusImpl {

	@Test
	public void test_When_Create_Instance_Using_Deafult_Cons() {
		CelsiusImpl impl = new CelsiusImpl();
		assertEquals(27.0, impl.getValue());
		assertEquals(Celsius, impl.getUnitType());
	}

	@Test
	public void test_When_Create_Instance_Using_Param_Cons() {
		CelsiusImpl impl = new CelsiusImpl(96.1);
		assertEquals(96.1, impl.getValue());
		assertEquals(Celsius, impl.getUnitType());
	}

	@Test
	public void test_When_Celsius_Is_Converted_To_Same() throws UnsupportedException {
		CelsiusImpl impl = new CelsiusImpl(96.1);
			CelsiusImpl implTwo = (CelsiusImpl) impl.convertTo(impl, Celsius);
			assertEquals(96.1, implTwo.getValue());
			assertEquals(Celsius, implTwo.getUnitType());
	}

	@Test
	public void test_When_Celsius_Is_Converted_To_Fahrenheit() throws UnsupportedException {
		CelsiusImpl impl = new CelsiusImpl(96.1);
			FahrenheitImpl implTwo = (FahrenheitImpl) impl.convertTo(impl, Fahrenheit);
			assertEquals(204.98, implTwo.getValue());
			assertEquals(Fahrenheit, implTwo.getUnitType());
	}

	@Test
	public void test_When_Celsius_Is_Converted_To_Fahrenheit_with_Fahrenheit_object() throws UnsupportedException {
		CelsiusImpl impl = new CelsiusImpl(96.1);
		FahrenheitImpl fImpl = new FahrenheitImpl(96.1);
			FahrenheitImpl implTwo = (FahrenheitImpl) impl.convertTo(fImpl, Fahrenheit);
			assertEquals(96.1, implTwo.getValue());
			assertEquals(Fahrenheit, implTwo.getUnitType());
	}

	@Test
	public void test_When_Invlaid_Type_Is_Passed_Throw_exception() {
		Assertions.assertThrows(UnsupportedException.class, () -> {
			CelsiusImpl impl = new CelsiusImpl(96.1);
			FahrenheitImpl fImpl = new FahrenheitImpl(96.1);
			impl.convertTo(fImpl, Kelvin);
		});

	}

}
