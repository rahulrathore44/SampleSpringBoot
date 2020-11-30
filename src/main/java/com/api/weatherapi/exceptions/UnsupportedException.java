package com.api.weatherapi.exceptions;

public class UnsupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsupportedException(String message) {
		super(message);
	}
	
	public UnsupportedException() {
		this("");
	}
	
}
