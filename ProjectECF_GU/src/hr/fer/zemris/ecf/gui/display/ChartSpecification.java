package hr.fer.zemris.ecf.gui.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.xy.XYSeriesCollection;

public class ChartSpecification {

	XYSeriesCollection dataset;
	List<Color> seriesColor;
	String chartTitle = "";
	String xAxisLabel = "";
	String yAxisLabel = "";
	boolean legend = true;
	boolean tooltips = false;
	
	public ChartSpecification(XYSeriesCollection dataset, List<Color> seriesColor, String chartTitle,
			String xAxisLabel, String yAxisLabel, boolean legend, boolean tooltips) {
		super();
		this.dataset = dataset;
		this.seriesColor = seriesColor;
		this.chartTitle = chartTitle;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
		this.legend = legend;
		this.tooltips = tooltips;
	}

	public ChartSpecification(XYSeriesCollection dataset) {
		super();
		this.dataset = dataset;
		seriesColor = new ArrayList<>();
	}
	
	
}
