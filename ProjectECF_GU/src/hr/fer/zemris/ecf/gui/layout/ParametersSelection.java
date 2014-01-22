package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.console.Job;
import hr.fer.zemris.ecf.gui.display.ChartFrame;
import hr.fer.zemris.ecf.gui.display.LineChartPanel;
import hr.fer.zemris.ecf.log.Generation;
import hr.fer.zemris.ecf.log.reader.OfflineReading;
import hr.fer.zemris.ecf.param.AlgGenRegUser;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.Genotype;
import hr.fer.zemris.ecf.param.Registry;
import hr.fer.zemris.ecf.tasks.TaskMannager;
import hr.fer.zemris.ecf.xmldom.XmlWriting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ParametersSelection extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String FILE = "res/dump/writing.txt";
	private static final String LOG = "res/dump/log.txt";
	
	private AlgorithmSelection algSel;
	private GenotypeSelection genSel;
	private EntryListPanel regList;
	private String ecfPath;
	
	public ParametersSelection(String ecfPath, List<Algorithm> algList, List<Genotype> genList, Registry reg) {
		super(new BorderLayout());
		algSel = new AlgorithmSelection(algList);
		genSel = new GenotypeSelection(genList);
		regList = new EntryListPanel(reg.getEntryList());
		this.ecfPath = ecfPath;
		add(new JScrollPane(new TempPanel(algSel, genSel, regList)), BorderLayout.CENTER);
		JButton button = new JButton(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				clicked();
			}
		});
		button.setText("Run");
		add(button, BorderLayout.SOUTH);
	}
	
	protected void clicked() {
		// Algorithm filling
		EntryListPanel pan = algSel.getSelectedEntryList();
		int size = pan.getEntriesCount();
		Algorithm alg = algSel.getSelectedItem();
		List<Entry> entries = alg.getEntryList();
		for (int i = 0; i < size; i++) {
			entries.get(i).value = pan.getText(i);
		}
		List<Algorithm> algs = new ArrayList<>(1);
		algs.add(alg);
		
		// Genotype filling
		pan = genSel.getSelectedEntryList();
		size = pan.getEntriesCount();
		Genotype gen = genSel.getSelectedItem();
		entries = gen.getEntryList();
		for (int i = 0; i < size; i++) {
			entries.get(i).value = pan.getText(i);
		}
		List<Genotype> gens = new ArrayList<>(1);
		gens.add(gen);
		List<List<Genotype>> genBlock = new ArrayList<>(1);
		genBlock.add(gens);
		
		// Registry filling
		size = regList.getEntriesCount();
		entries = regList.getList();
		Registry reg = new Registry(entries);
		for (int i = 0; i < size; i++) {
			entries.get(i).value = regList.getText(i);
		}
		
		AlgGenRegUser temp = new AlgGenRegUser();
		temp.algorithm = algs;
		temp.genotypes = genBlock;
		temp.registry = reg;
		
		XmlWriting.write(FILE, temp);
		Job job = new Job(ecfPath, LOG, FILE); // FIXME 3. FILE <-> "lib/parameters1.txt"
		TaskMannager tm = new TaskMannager();
		int pn = tm.getCpuCors();
		List<Job> jobs = new ArrayList<>(1);
		jobs.add(job);
		tm.startTasks(jobs, pn);
		OfflineReading off = new OfflineReading();
		off.read(LOG);
		ArrayList<Generation> generations = off.getLogFile().generations;
		size = generations.size();
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
		String chartTitle = alg.getName();
		String xAxisLabel = "Generation";
		String yAxisLabel = "Fitness";
		LineChartPanel lineChart = new LineChartPanel(coll, colors, chartTitle, xAxisLabel, yAxisLabel, true, false);
		JFrame frame = new ChartFrame(lineChart);
		frame.setVisible(true);
	}

	public Algorithm getSelectedAlgortihm() {
		return algSel.getSelectedItem();
	}
	
	public Genotype getSelecteGenotype() {
		return genSel.getSelectedItem();
	}
	
	public List<Entry> getSelectedRegistry() {
		return regList.getList();
	}
	
	private static class TempPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		public TempPanel(AlgorithmSelection algSel, GenotypeSelection genSel, EntryListPanel regList) {
			super();
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(algSel);
			add(new JSeparator(JSeparator.VERTICAL));
			add(genSel);
			add(new JSeparator(JSeparator.VERTICAL));
			add(regList);
		}
		
	}
	
}
