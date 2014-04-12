package hr.fer.zemris.ecf.gui.display;

import hr.fer.zemris.ecf.gui.chart.ChartFrame;
import hr.fer.zemris.ecf.gui.chart.LineChartPanel;
import hr.fer.zemris.ecf.log.Generation;
import hr.fer.zemris.ecf.log.reader.OfflineReading;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FrameDisplayer implements IResultDisplay {

	@Override
	public void displayResult(String logFile) throws Exception {
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
			if (generation.population != null) {
				sMinFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.population.minFitness));
				sMaxFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.population.maxFitness));
				sAvgFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.population.avgFitness));
			} else {
				sMinFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.demes.get(0).minFitness));
				sMaxFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.demes.get(0).maxFitness));
				sAvgFit.add(Integer.valueOf(generation.id), Double.valueOf(generation.demes.get(0).avgFitness));
			}
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
