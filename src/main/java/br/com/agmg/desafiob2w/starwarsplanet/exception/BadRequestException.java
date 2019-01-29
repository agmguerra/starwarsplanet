package br.com.agmg.desafiob2w.starwarsplanet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception que representa um ocorrência de BAD REQUEST
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends GenericException {

	private static final long serialVersionUID = 8648084734021170696L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}
	
}
