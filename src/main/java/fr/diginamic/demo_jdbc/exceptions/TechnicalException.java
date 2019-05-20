package fr.diginamic.demo_jdbc.exceptions;

/**
 *
 */
public class TechnicalException extends RuntimeException {

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public TechnicalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
