package hr.fer.zemris.ecf.test.panels;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class LineChartPanelTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public LineChartPanelTest() {
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				startGUIApp();
			}
		});
	}
	
	protected static void startGUIApp() {
		new LineChartPanelTest().setVisible(true);
	}

}
