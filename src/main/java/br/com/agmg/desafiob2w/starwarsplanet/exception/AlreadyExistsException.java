package br.com.agmg.desafiob2w.starwarsplanet.exception;

/**
 * 
 * Exception que representa um recurso que já existe
 *
 */
public class AlreadyExistsException extends GenericException {

	private static final long serialVersionUID = 6938321050763770382L;

	public AlreadyExistsException() {
		super();
	}

	public AlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyExistsException(String message) {
		super(message);
	}

	public AlreadyExistsException(Throwable cause) {
		super(cause);
	}



}
