package br.com.agmg.desafiob2w.starwarsplanet.exception;

/**
 * 
 * @author guerra
 *
 */
public class AlreadyExistsException extends GenericException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
