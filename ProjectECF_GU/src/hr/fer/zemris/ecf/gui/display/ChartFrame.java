package hr.fer.zemris.ecf.gui.display;

import javax.swing.JFrame;

public class ChartFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public ChartFrame(LineChartPanel panel) {
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(panel);
		pack();
	}
	
}
