package hr.fer.zemris.ecf.gui.chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Panel containing chart and table for dispaying y-values at certain x-value.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class LineChartPanel extends JPanel implements ChartProgressListener {

	private static final long serialVersionUID = 1L;

	private XYSeriesCollection dataset;
	private List<Color> seriesColor = new ArrayList<>();
	private String chartTitle;
	private String xAxisLabel;
	private String yAxisLabel;
	private boolean legend;
	private boolean tooltips;

	private JFreeChart chart;
	private ChartPanel chartPanel;
	private ChartTableModel tableModel;

	/**
	 * @param dataset Dataset for chart
	 * @param seriesColor List of colors for each serie
	 * @param chartTitle Chart title
	 * @param xAxisLabel X-axis value
	 * @param yAxisLabel Y-axis value
	 * @param legend If legend sholud be displayed
	 * @param tooltips If tooltips should be displayed
	 */
	public LineChartPanel(XYSeriesCollection dataset, List<Color> seriesColor, String chartTitle, String xAxisLabel,
			String yAxisLabel, boolean legend, boolean tooltips) {
		super(new BorderLayout());
		this.dataset = dataset;
		this.seriesColor = seriesColor;
		this.chartTitle = chartTitle;
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
		this.legend = legend;
		this.tooltips = tooltips;

		// Creating and adding chart
		chart = createXYLineChart(dataset);
		chart.addProgressListener(this);
		chartPanel = new ChartPanel(chart);
		// chartPanel.setPreferredSize(new Dimension(600, 270));
		chartPanel.setDomainZoomable(true);
		chartPanel.setRangeZoomable(true);
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4),
				BorderFactory.createEtchedBorder());
		chartPanel.setBorder(border);
		add(chartPanel, BorderLayout.CENTER);

		// Adding table
		JPanel dashboard = new JPanel(new BorderLayout());
		dashboard.setPreferredSize(new Dimension(400, 60));
		dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));

		int size = dataset.getSeriesCount();
		List<String> colNames = new ArrayList<>(size + 1);
		colNames.add(xAxisLabel);
		for (int i = 0; i < size; i++) {
			colNames.add(dataset.getSeriesKey(i).toString());
		}

		tableModel = new ChartTableModel(1, size + 1, colNames);
		JTable table = new JTable(tableModel);
		dashboard.add(new JScrollPane(table));

		add(dashboard, BorderLayout.SOUTH);
	}

	/**
	 * @param spec Initial values
	 */
	public LineChartPanel(ChartSpecification spec) {
		this(spec.dataset, spec.seriesColor, spec.chartTitle, spec.xAxisLabel, spec.yAxisLabel, spec.legend,
				spec.tooltips);
	}

	/**
	 * Chart drawing
	 * @param dataset Dataset to be drawn
	 * @return Drawn chart
	 */
	private JFreeChart createXYLineChart(XYDataset dataset) {
		chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,
				legend, tooltips, false);
		chart.setBackgroundPaint(Color.WHITE);
		XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.BLUE);
		plot.setRangeGridlinePaint(Color.MAGENTA);
		// plot.setAxisOffset(new RectangleInsets(5, 5, 5, 5));
		plot.setDomainCrosshairVisible(true);
		plot.setDomainCrosshairLockedOnData(false);
		plot.setRangeCrosshairVisible(false);
		plot.setDomainCrosshairPaint(Color.BLACK);
		plot.setDomainCrosshairStroke(new BasicStroke(2));
		plot.getDomainAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		XYItemRenderer renderer = plot.getRenderer();
		int count = plot.getSeriesCount();
		for (int i = 0; i < count; i++) {
			try {
				renderer.setSeriesPaint(i, seriesColor.get(i));
			} catch (IndexOutOfBoundsException e) {
				renderer.setSeriesPaint(i, Color.RED);
			}
		}
		return chart;
	}

	/**
	 * @return Current dataset
	 */
	public XYSeriesCollection getDataset() {
		return dataset;
	}

	/**
	 * @param x
	 *            Value on domain axis to be added.
	 * @param y
	 *            Values to be added on range axis to be added for given x
	 *            value. Size of list must be equals to number of series.
	 */
	public void addData(Number x, List<Number> y) {
		int n = y.size();
		for (int i = 0; i < n; i++) {
			dataset.getSeries(i).add(x, y.get(i));
		}
	}

	@Override
	public void chartProgress(ChartProgressEvent event) {
		XYPlot plot = chart.getXYPlot();
		double d = plot.getDomainCrosshairValue();
		int serNum = dataset.getSeriesCount();
		tableModel.setValueAt(d, 0, 0);
		for (int i = 0; i < serNum; i++) {
			int index = findClosestItem(d);
			tableModel.setValueAt(dataset.getSeries(i).getY(index), 0, i + 1);
			plot.setDomainCrosshairValue(dataset.getSeries(0).getDataItem(index).getXValue());
		}
		repaint();
	}

	/**
	 * @param d Selected x-value
	 * @return Nearest x-value in the dataset
	 */
	private int findClosestItem(double d) {
		XYSeries s = dataset.getSeries(0);
		int size = s.getItemCount();
		double min = Double.POSITIVE_INFINITY;
		int index = 0;
		for (int i = 0; i < size; i++) {
			XYDataItem item = s.getDataItem(i);
			double t = item.getXValue();
			double diff = Math.abs(t - d);
			if (diff < min) {
				min = diff;
				index = i;
			}
		}
		return index;
	}

}
