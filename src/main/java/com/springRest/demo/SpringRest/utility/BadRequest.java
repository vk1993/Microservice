package com.springRest.demo.SpringRest.utility;

import org.springframework.stereotype.Component;

@Component
public class BadRequest extends Exception {

	private String errorMessage;

	public BadRequest(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public BadRequest() {
		super();
	}
}
