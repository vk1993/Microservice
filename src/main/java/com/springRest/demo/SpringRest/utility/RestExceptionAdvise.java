package com.springRest.demo.SpringRest.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionAdvise extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ApiError> handellerNotFoundException(BadRequest exception, WebRequest request,
			HttpStatus status) {
		ApiError errorDetails = new ApiError(status, new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request, HttpStatus status) {
		ApiError errorDetails = new ApiError(status, new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ApiError errorDetails = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

		return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.info(ex.getRootCause());
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final ApiError errorDetails = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error, new Date());
		return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.info(ex.getRootCause());

		final String error = "Enter integer value arays, not STRING";

		final ApiError errorDetails = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error, new Date());
		return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
