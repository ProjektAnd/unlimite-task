package net.enemyofmankind.ultimatetask.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	private final HttpHeaders header = new HttpHeaders();

	private ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest webRequest, HttpStatus status) {
		GlobalErrorResponse errorResponse = new GlobalErrorResponse(status, exception, webRequest);
		return handleGlobalException(exception, errorResponse , webRequest , status);
	}

	private ResponseEntity<Object> handleGlobalException(Exception exception, GlobalErrorResponse response, WebRequest webRequest, HttpStatus status){
		header.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(exception, response , header, status, webRequest);
	}

	@ExceptionHandler (value = RecordNotFoundException.class)
	public ResponseEntity<Object> handleException(RecordNotFoundException exception, WebRequest webRequest) {
		return handleGlobalException(exception, webRequest, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler (value = RecordAlreadyExistsException.class)
	public ResponseEntity<Object> handleException(RecordAlreadyExistsException exception, WebRequest webRequest) {
		return handleGlobalException(exception, webRequest, HttpStatus.CONFLICT);
	}

	@ExceptionHandler (value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException exception, WebRequest webRequest) {
		return handleGlobalException(exception, webRequest, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
