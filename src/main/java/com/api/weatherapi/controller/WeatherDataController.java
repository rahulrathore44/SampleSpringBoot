package com.api.weatherapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.weatherapi.controller.impl.WeatherDetails;
import com.api.weatherapi.controller.impl.WeatherDetailsImpl;
import com.api.weatherapi.exceptions.UnsupportedException;

@RestController
@ComponentScan("com.api.weatherapi.controller.impl")
public class WeatherDataController {

	@Autowired
	@Qualifier("weatherDetailsImpl")
	private WeatherDetails weatherDetails;
	//private WeatherDetails weatherDetails = new WeatherDetailsImpl();

	@RequestMapping(path = { "/TempInCelsius" }, method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getTempInCelsius() {
		return new ResponseEntity<Object>(weatherDetails.getTempInCelsius(), HttpStatus.OK);
	}

	@RequestMapping(path = { "/TempInFahrenheit" }, method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getTempInFahrenheitImpl() {
		try {
			return new ResponseEntity<Object>(weatherDetails.getTempInFahrenheitImpl(), HttpStatus.OK);
		} catch (UnsupportedException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path = { "/LastSevenDaysTemp" }, method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getLastSevenDaysTemp(@RequestParam String type) {
		try {
			return new ResponseEntity<Object>(weatherDetails.getLastSevenDaysTemp(type), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
