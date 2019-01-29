package br.com.agmg.desafiob2w.starwarsplanet.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agmg.desafiob2w.starwarsplanet.resource.StatusResponse;

/**
 * 
 * Classe utilizada para tratar as principais exceptions
 *
 */
@ControllerAdvice
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {

	
	@Autowired
	private MessageSource messageSource;
	
				
	@ExceptionHandler(value = {NotFoundException.class})
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
		StatusResponse exceptionResponse = 
				new StatusResponse(HttpStatus.NOT_FOUND, new Date(), messageSource.getMessage(ex.getMessage(),
						null, request.getLocale()), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StatusResponse exceptionResponse = 
				new StatusResponse(HttpStatus.BAD_REQUEST, ex.getBindingResult(), ex.getBindingResult().toString());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
				
		StatusResponse exceptionResponse = 
				new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR, new Date(), messageSource.getMessage(ex.getMessage(),
						null, request.getLocale()), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(GenericException.class)
	public final ResponseEntity<Object> handleGenericException(GenericException ex, WebRequest request) {
				
		StatusResponse exceptionResponse = 
				new StatusResponse(HttpStatus.INTERNAL_SERVER_ERROR, new Date(), messageSource.getMessage(ex.getMessage(),
						null, request.getLocale()), request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

			
}
