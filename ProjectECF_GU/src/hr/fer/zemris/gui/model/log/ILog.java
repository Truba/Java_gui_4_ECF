package hr.fer.zemris.gui.model.log;

/**
 * Defines a way of logging errors.
 * @author Domagoj Stanković
 * @version 1.0
 */
public interface ILog {
	
	/**
	 * Logs given message.
	 * @param message Message to be logged
	 */
	public void log(String message);
	
	/**
	 * Logs exception message and stack trace.
	 * @param e Exception to be logged
	 */
	public void log(Exception e);
	
}
