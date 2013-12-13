package hr.fer.zemris.ecf.console;


public interface ITalk {
	/**
	 * Example:
	 * @param path "lib"
	 * @param command "java -jar mythsim-3.1.1.jar"
	 */
	public void write(String ecfPath, String paramsPath);
	
	public void write(Job job);

}
