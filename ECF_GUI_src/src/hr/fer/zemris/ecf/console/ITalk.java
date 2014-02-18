package hr.fer.zemris.ecf.console;

/**
 * This interface describes methods that are used for communication to the ECF through the different operating systems terminal/cmd.
 * This interface is implemented by classes that specify this type of communication for specific operating system.
 * @version 1.0
 *
 */
public interface ITalk {
	/**
	 * This method is used for getting initial ECF's xml parameters.
	 * @param ecfPath Path to the ECF's .exe file.
	 * @param paramsPath Path to file where initial ECF's parameters will be dumped. If file exists it will be overridden.   
	 */
	public void write(String ecfPath, String paramsPath);
	/**
	 * This method is used for starting one of ECF's computations with given {@link Job}.
	 * @param job class job with needed parameters for one of ECF's computation to start.
	 */
	public void write(Job job);

}
