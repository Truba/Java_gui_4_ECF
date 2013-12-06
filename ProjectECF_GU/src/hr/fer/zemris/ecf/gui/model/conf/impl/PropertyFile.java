package hr.fer.zemris.ecf.gui.model.conf.impl;

import hr.fer.zemris.ecf.gui.model.conf.ConfigurationException;
import hr.fer.zemris.ecf.gui.model.conf.IConfiguration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Configuration based on the .properties file.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class PropertyFile implements IConfiguration {

	private Properties properties = new Properties();
	private String filePath;
	
	/**
	 * @param filePath Path of the .properties configuration file
	 * @throws IOException If file cannot be opened or read
	 */
	public PropertyFile(String filePath) {
		super();
		this.filePath = filePath;
		try {
			read();
		} catch (IOException | NullPointerException e) {
			throw new ConfigurationException("Configuration file reading failed!");
		}
	}

	private void read() throws IOException {
		InputStream is = new FileInputStream(filePath);
		properties.load(is);
		is.close();
	}
	
	private void write() throws IOException {
		OutputStream os = new FileOutputStream(filePath);
		properties.store(os, "");
		os.close();
	}

	@Override
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void changeValue(String key, String value) {
		properties.setProperty(key, value);
		try {
			write();
		} catch (IOException | NullPointerException e) {
			throw new ConfigurationException("Writing to configuration file failed!");
		}
	}

}
