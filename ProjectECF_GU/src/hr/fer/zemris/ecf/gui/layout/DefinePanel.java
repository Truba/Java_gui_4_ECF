package hr.fer.zemris.ecf.gui.layout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel for defining configuration and log file path and number of threads.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class DefinePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefineField params;
	private DefineField log;
	private DefineField threadsCount;

	public DefinePanel(String paramsPath, String logPath, int threads, JButton button) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		params = new DefineField("Parameters path", paramsPath);
		add(params);
		log = new DefineField("Log path", logPath);
		add(log);
		threadsCount = new DefineField("Number of threads", String.valueOf(threads));
		add(threadsCount);
		add(button);
	}

	/**
	 * @return Path to the configuration file
	 */
	public String getParamsPath() {
		return params.getText();
	}

	/**
	 * @param path Path to the configuration file
	 */
	public void setParamsPath(String path) {
		params.setText(path);
	}

	/**
	 * @return Path to the log file
	 */
	public String getLogPath() {
		return log.getText();
	}

	/**
	 * @param path Path to the log file
	 */
	public void setLogPath(String path) {
		log.setText(path);
	}

	/**
	 * @return Number of threads
	 */
	public int getThreadsCount() {
		return Integer.valueOf(threadsCount.getText());
	}

	/**
	 * @param num Number of threads
	 */
	public void setThreadsCount(int num) {
		threadsCount.setText(String.valueOf(num));
	}

}
