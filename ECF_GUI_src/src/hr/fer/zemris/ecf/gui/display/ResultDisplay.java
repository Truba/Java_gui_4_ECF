package hr.fer.zemris.ecf.gui.display;

public interface ResultDisplay {

	/**
	 * Displays chart in a new frame. Reads results from the log file and then
	 * displays it.
	 * 
	 * @param logFile
	 *            Path to the log file
	 * @throws Exception
	 *             If reading log file goes wrong
	 */
	public void displayResult(String logFile) throws Exception;
	
}
