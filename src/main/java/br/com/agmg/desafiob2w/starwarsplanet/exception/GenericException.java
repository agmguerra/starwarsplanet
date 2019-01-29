package br.com.agmg.desafiob2w.starwarsplanet.exception;

/**
 * 
 *
 */
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GenericException(String message) {
		super(message);
	}


	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericException(Throwable cause) {
		super(cause);
	}

	
	
}
