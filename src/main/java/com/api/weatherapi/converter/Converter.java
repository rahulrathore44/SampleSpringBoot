package com.api.weatherapi.converter;

import com.api.weatherapi.converter.type.ConversionType;
import com.api.weatherapi.exceptions.UnsupportedException;

public interface Converter {
	public <T extends Unit> Unit convertTo(T targetType, ConversionType toType) throws UnsupportedException;
}
