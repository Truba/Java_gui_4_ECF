package hr.fer.zemris.cmdTerminal;

public interface ITalk {
	/**
	 * Example:
	 * @param path "lib"
	 * @param command "java -jar mythsim-3.1.1.jar"
	 */
	public void write(String path, String command);
	
	/**
	 * Example:
	 * @param partition "C:"
	 * @param path "C:/Users/Truba/Desktop/mythsim-3.1.1"s
	 * @param command "java -jar mythsim-3.1.1.jar"
	 */
	public void write(String partition, String path, String command);

}
