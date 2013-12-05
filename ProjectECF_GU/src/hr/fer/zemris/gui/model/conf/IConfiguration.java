package hr.fer.zemris.gui.model.conf;

/**
 * Retrieves values from configuration object.
 * @author Domagoj Stanković
 * @version 1.0
 */
public interface IConfiguration {
	
	/**
	 * Retrieves value mapped by specified key from the configuration object.
	 * @param key Configuration key
	 * @return Value mapped by configuration key in the configuration object
	 */
	public String getValue(String key);
	
	/**
	 * Changes value under a specified key.
	 * @param key Configuration key
	 * @param value New value
	 */
	public void changeValue(String key, String value);
	
}
