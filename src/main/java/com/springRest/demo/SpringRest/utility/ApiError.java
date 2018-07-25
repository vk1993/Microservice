package com.springRest.demo.SpringRest.utility;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
	private HttpStatus status;
	private Date timestamp;
	private String message;
	private String details;
	private List<String> errors;

	public ApiError(HttpStatus status, Date date, String message, String description) {
		super();
		this.timestamp = date;
		this.message = message;
		this.details = description;
		this.status = status;
	}

	public ApiError(HttpStatus status, String message, List<String> errors) {

		this.message = message;
		this.status = status;
		this.errors = errors;
	}

	public ApiError(HttpStatus status, String message, String detail,Date date) {

		this.message = message;
		this.status = status;
		this.details = detail;
		this.timestamp = date; 
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public List<String> getErrors() {
		return errors;
	}

}