package hr.fer.zemris.ecf.gui.model.conf;

/**
 * Thrown when configuration error occurs.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfigurationException() {
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
