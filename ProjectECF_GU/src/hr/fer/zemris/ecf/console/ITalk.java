package hr.fer.zemris.ecf.console;

import hr.fer.zemris.ecf.tasks.Job;

public interface ITalk {
	/**
	 * Example:
	 * @param path "lib"
	 * @param command "java -jar mythsim-3.1.1.jar"
	 */
	public void write(String path, String command);
	
	public void write(Job job);

}
