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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DropDownSelection extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;

	private static final String FILE = "res/dump/writing.txt";

	private static final String LOG = "res/dump/log.txt";
	
	private JPanel cards;
	private List<Algorithm> list;
	private String[] model;
	private JComboBox<String> box = null;
	private List<EntryListPanel> algPanels = new ArrayList<>();
	
	public DropDownSelection(List<Algorithm> list) {
		this.list = list;
		setLayout(new BorderLayout());
		int n = list.size();
		model = new String[n];
		for (int i = 0; i < n; i++) {
			model[i] = list.get(i).toString();
		}
		box = new JComboBox<>(model);
		box.setEditable(false);
		box.addItemListener(this);
		
		cards = new JPanel(new CardLayout());
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			List<Entry> ent = list.get(i).getEntryList();
			EntryListPanel card = new EntryListPanel(ent);
			algPanels.add(card);
			cards.add(card, model[i]);
		}
		
		JButton button = new JButton(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				runECF();
			}
		});
		button.setText("Run");
		
		add(box, BorderLayout.NORTH);
		add(cards, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}
	
	protected void runECF() {
		// FIXME samo za jedan algoritam
		AlgGenRegUser temp = new AlgGenRegUser();
		int index = box.getSelectedIndex();
		EntryListPanel pan = algPanels.get(index);
		int size = pan.getEntriesCount();
		Algorithm alg = list.get(index);
		List<Entry> entries = alg.getEntryList();
		for (int i = 0; i < size; i++) {
			entries.get(i).value = pan.getText(i);
		}
		
		List<Algorithm> algs = new ArrayList<>(1);
		algs.add(alg);
		temp.algorithm = algs;
		
		List<List<Genotype>> genotypes = new ArrayList<>();
		Registry registry = new Registry(new ArrayList<Entry>());
		genotypes.add(new ArrayList<Genotype>());
		Genotype genotype = new Genotype("Binary");
		List<Entry> genEntries = new ArrayList<>();
		genEntries.add(new Entry());
		genotypes.get(0).add(genotype);
		temp.genotypes = genotypes;
		temp.registry = registry;
		temp.userComment = "";
		
		XmlWriting.write(FILE, temp);
		String ecfPath = "C:/Temp/GAOneMax.exe";
		Job job = new Job(ecfPath , LOG, "lib/parameters1.txt"); //FIXME 3. FILE
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
			Generation gen = generations.get(i);
			sMinFit.add(Integer.valueOf(gen.id), Double.valueOf(gen.population.minFitness));
			sMaxFit.add(Integer.valueOf(gen.id), Double.valueOf(gen.population.maxFitness));
			sAvgFit.add(Integer.valueOf(gen.id), Double.valueOf(gen.population.avgFitness));
		}
		XYSeriesCollection coll = new XYSeriesCollection();
		coll.addSeries(sMinFit);
		coll.addSeries(sMaxFit);
		coll.addSeries(sAvgFit);
		List<Color> colors = new ArrayList<>(3);
		colors.add(Color.RED);
		colors.add(Color.GREEN);
		colors.add(Color.ORANGE);
		String chartTitle = alg.getName();
		String xAxisLabel = "Generation";
		String yAxisLabel = "Fitness";
		LineChartPanel lineChart = new LineChartPanel(coll, colors, chartTitle, xAxisLabel, yAxisLabel, true, false);
		JFrame frame = new ChartFrame(lineChart);
		frame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, e.getItem().toString());
	}
	
}
