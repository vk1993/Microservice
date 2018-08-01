package com.springRest.demo.SpringRest.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionAdvise extends ResponseEntityExceptionHandler {

//	@ExceptionHandler(BadRequest.class)
//	public ResponseEntity<ApiError> handellerNotFoundException(BadRequest exception, WebRequest request,
//			HttpStatus status) {
//		ApiError errorDetails = new ApiError(status, new Date(), exception.getMessage(), request.getDescription(false));
//		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final List<String> errors = new ArrayList<String>();
		errors.add(ex.getCause().getMessage());
//		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
//			errors.add(error.getField() + ": " + error.getDefaultMessage());
//		}
//		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//		}
		final ApiError errorDetails = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
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
         return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());

	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.info(ex.getRootCause());
		final String error = "Entered Value is "+ex.getValue().getClass().getName() + ".But,it should be of type "
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

	public RestExceptionAdvise() {
		super();
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleServletRequestBindingException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleConversionNotSupported(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleHttpMessageNotWritable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleMissingServletRequestPart(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleBindException(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
