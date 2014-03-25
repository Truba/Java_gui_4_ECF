package hr.fer.zemris.ecf.gui.chart;

import hr.fer.zemris.ecf.log.Generation;
import hr.fer.zemris.ecf.log.reader.OfflineReading;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class with static methods for chart display.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class ChartUtils {
	
	/**
	 * Displays chart in a new frame. Reads results from the log file and then displays it.
	 * @param logFile Path to the log file
	 * @throws Exception If reading log file goes wrong
	 */
	public static void showResults(String logFile) throws Exception {
		OfflineReading off = new OfflineReading();
		off.read(logFile);
		ArrayList<Generation> generations = off.getLogFile().generations;
		String solution = off.getLogFile().hallOfFame.get(0).genotypes.get(0).toString();
		int size = generations.size();
		XYSeries sMinFit = new XYSeries("Min Fit");
		XYSeries sMaxFit = new XYSeries("Max Fit");
		XYSeries sAvgFit = new XYSeries("Avg Fit");
		for (int i = 0; i < size; i++) {
			Generation generation = generations.get(i);
			sMinFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.population.minFitness));
			sMaxFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.population.maxFitness));
			sAvgFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.population.avgFitness));
		}
		XYSeriesCollection coll = new XYSeriesCollection();
		coll.addSeries(sMinFit);
		coll.addSeries(sMaxFit);
		coll.addSeries(sAvgFit);
		List<Color> colors = new ArrayList<>(3);
		colors.add(Color.BLACK);
		colors.add(Color.RED);
		colors.add(Color.BLUE);
		String chartTitle = logFile;
		String xAxisLabel = "Generation";
		String yAxisLabel = "Fitness";
		LineChartPanel lineChart = new LineChartPanel(coll, colors, chartTitle, xAxisLabel, yAxisLabel, true, false);
		JFrame frame = new ChartFrame(lineChart, solution);
		frame.setVisible(true);
	}
	
}
