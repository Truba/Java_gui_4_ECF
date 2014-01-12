package hr.fer.zemris.ecf.test.panels;

import hr.fer.zemris.ecf.gui.display.LineChartPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartPanelTest extends JFrame {

	private static final long serialVersionUID = 1L;

	public LineChartPanelTest() {
		setLayout(new BorderLayout());
		final XYSeriesCollection dataset = createDataset();
		List<Color> seriesColor = new ArrayList<>();
		seriesColor.add(Color.BLUE);
		seriesColor.add(Color.RED);
		String chartTitle = "Algorithm";
		String xAxisLabel = "Generation";
		String yAxisLabel = "Fitness";
		Component chart = new LineChartPanel(dataset, seriesColor, chartTitle, xAxisLabel, yAxisLabel, true, false);
		add(chart, BorderLayout.CENTER);
		JButton button = new JButton(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			private int i = 6;

			@Override
			public void actionPerformed(ActionEvent e) {
				dataset.getSeries(0).add(new Integer(i), new Double(13));
				dataset.getSeries(1).add(new Integer(i), new Double(17));
				i++;
			}
		});
		button.setText("Add");
		add(button, BorderLayout.SOUTH);
		pack();
	}
	
	private XYSeriesCollection createDataset() {
		XYSeries series = new XYSeries("Average");
		XYSeries series2 = new XYSeries("Best");
		series.add(new Integer(-5), new Double(-3));
		series.add(new Integer(-4), new Double(2.8));
		series.add(new Integer(-3), new Double(5.2));
		series.add(new Integer(-2), new Double(0.1));
		series.add(new Integer(-1), new Double(3.8));
		series.add(new Integer(0), new Double(1));
		series.add(new Integer(1), new Double(-2.4));
		series.add(new Integer(2), new Double(4.7));
		series.add(new Integer(3), new Double(-1));
		series.add(new Integer(4), new Double(5));
		series.add(new Integer(5), new Double(6));
		
		series2.add(new Integer(-5), new Double(0));
		series2.add(new Integer(-4), new Double(4));
		series2.add(new Integer(-3), new Double(6));
		series2.add(new Integer(-2), new Double(7));
		series2.add(new Integer(-1), new Double(7.5));
		series2.add(new Integer(0), new Double(7.75));
		series2.add(new Integer(1), new Double(8));
		series2.add(new Integer(2), new Double(8.2));
		series2.add(new Integer(3), new Double(8.3));
		series2.add(new Integer(4), new Double(8.35));
		series2.add(new Integer(5), new Double(8.4));
		
		XYSeriesCollection coll = new XYSeriesCollection();
		coll.addSeries(series);
		coll.addSeries(series2);
		return coll;
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
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
