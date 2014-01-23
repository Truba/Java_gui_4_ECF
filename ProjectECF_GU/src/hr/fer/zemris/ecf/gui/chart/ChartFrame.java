package hr.fer.zemris.ecf.gui.chart;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChartFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public ChartFrame(LineChartPanel panel, String solution) {
		super();
		setLayout(new BorderLayout());
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(panel, BorderLayout.CENTER);
		solution = "<html>Hall of fame:<br/><br/>" + solution + "</html>";
		add(new JLabel(solution), BorderLayout.SOUTH);
		pack();
	}
	
}
