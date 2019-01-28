package br.com.agmg.desafiob2w.starwarsplanet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648084734021170696L;

	public BadRequestException(String message) {
		super(message);
	}

	
	
}
