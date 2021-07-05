package com.compasso.apiproducts.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BindingResult bindingResult = ex.getBindingResult();
		
		ErrorResponse errorResponse = new ErrorResponse(status.value(), getErrorMessage(bindingResult));
		
		return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex ,WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage());
		
		return handleExceptionInternal(ex, errorResponse,
				new HttpHeaders(), status, request);
	}
	
	private String getErrorMessage(BindingResult bindingResult) {
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		}
		return "contain error";
	}
	
	@Data
	@AllArgsConstructor
	public class ErrorResponse {
		private int status_code;
		private String message;
	}
}
