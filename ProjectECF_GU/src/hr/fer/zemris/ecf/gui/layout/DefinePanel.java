package hr.fer.zemris.ecf.gui.layout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

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

	public String getParamsPath() {
		return params.getText();
	}

	public void setParamsPath(String path) {
		params.setText(path);
	}

	public String getLogPath() {
		return log.getText();
	}

	public void setLogPath(String path) {
		log.setText(path);
	}

	public int getThreadsCount() {
		return Integer.valueOf(threadsCount.getText());
	}

	public void setThreadsCount(int num) {
		threadsCount.setText(String.valueOf(num));
	}

}
