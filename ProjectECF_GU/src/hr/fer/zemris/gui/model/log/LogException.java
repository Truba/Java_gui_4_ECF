package hr.fer.zemris.gui.model.log;

/**
 * Thrown when log error occurs.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class LogException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LogException() {
		super();
	}

	public LogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogException(String message) {
		super(message);
	}

	public LogException(Throwable cause) {
		super(cause);
	}

}
