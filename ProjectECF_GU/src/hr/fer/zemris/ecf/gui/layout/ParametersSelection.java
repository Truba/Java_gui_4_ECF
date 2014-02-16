package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.console.IObserver;
import hr.fer.zemris.ecf.console.ISubject;
import hr.fer.zemris.ecf.console.Job;
import hr.fer.zemris.ecf.gui.ECFLab;
import hr.fer.zemris.ecf.gui.chart.ChartUtils;
import hr.fer.zemris.ecf.gui.model.conf.ConfigurationKey;
import hr.fer.zemris.ecf.param.AlgGenRegUser;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.Genotype;
import hr.fer.zemris.ecf.param.Registry;
import hr.fer.zemris.ecf.tasks.TaskMannager;
import hr.fer.zemris.ecf.xmldom.XmlWriting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class ParametersSelection extends JPanel implements IObserver {

	private static final long serialVersionUID = 1L;

	private ECFLab parent;
	private AlgorithmSelection algSel;
	private GenotypeSelection genSel;
	private EntryListPanel regList;
	private String ecfPath;
	private DefinePanel definePanel;

	public ParametersSelection(ECFLab parent) {
		super(new BorderLayout());
		this.parent = parent;
		ecfPath = parent.getEcfPath();
		if (ecfPath == null) {
			throw new NullPointerException("ECF executable file undefined!");
		}
		algSel = new AlgorithmSelection(parent.getParDump().algorithms);
		genSel = new GenotypeSelection(parent.getParDump().genotypes);
		regList = new EntryListPanel(parent.getParDump().registry.getEntryList());
		String file = parent.getConfiguration().getValue(ConfigurationKey.DEFAULT_PARAMS_PATH);
		String log = parent.getConfiguration().getValue(ConfigurationKey.DEFAULT_LOG_PATH);
		add(new JScrollPane(new TempPanel(algSel, genSel, regList)), BorderLayout.CENTER);
		JButton button = new JButton(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				clicked();
			}
		});
		button.setText("Run");
		int cores = Runtime.getRuntime().availableProcessors();
		definePanel = new DefinePanel(file, log, cores, button);
		add(definePanel, BorderLayout.SOUTH);
	}

	protected void clicked() {
		try {
			AlgGenRegUser temp = getParameters();
			String file = definePanel.getParamsPath();
			String log = definePanel.getLogPath();
			XmlWriting.write(file, temp);
			Job job = new Job(ecfPath, log, file);
			TaskMannager tm = new TaskMannager();
			int pn = definePanel.getThreadsCount();
			List<Job> jobs = new ArrayList<>(1);
			job.setObserver(this);
			jobs.add(job);
			tm.startTasks(jobs, pn);
		} catch (Exception e) {
			parent.reportError(e.getMessage());
			parent.getLog().log(e);
		}
	}

	public AlgGenRegUser getParameters() {
		// Algorithm filling
		EntryListPanel pan = algSel.getSelectedEntryList();
		int size = pan.getEntriesCount();
		Algorithm alg = algSel.getSelectedItem();
		List<Entry> entries = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (pan.isSelected(i)) {
				entries.add(new Entry(pan.getKeyAt(i), pan.getDescriptionAt(i), pan.getValueAt(i)));
			}
		}
		Algorithm algTemp = new Algorithm(alg.getName(), entries);
		List<Algorithm> algs = new ArrayList<>(1);
		algs.add(algTemp);

		// Genotype filling
		pan = genSel.getSelectedEntryList();
		size = pan.getEntriesCount();
		Genotype gen = genSel.getSelectedItem();
		entries = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (pan.isSelected(i)) {
				entries.add(new Entry(pan.getKeyAt(i), pan.getDescriptionAt(i), pan.getValueAt(i)));
			}
		}
		Genotype genTemp = new Genotype(gen.getName(), entries);
		List<Genotype> gens = new ArrayList<>(1);
		gens.add(genTemp);
		List<List<Genotype>> genBlock = new ArrayList<>(1);
		genBlock.add(gens);

		// Registry filling
		size = regList.getEntriesCount();
		entries = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (regList.isSelected(i)) {
				entries.add(new Entry(regList.getKeyAt(i), regList.getDescriptionAt(i), regList.getValueAt(i)));
			}
		}
		Registry reg = new Registry(entries);

		AlgGenRegUser temp = new AlgGenRegUser();
		temp.algorithm = algs;
		temp.genotypes = genBlock;
		temp.registry = reg;
		return temp;
	}

	public AlgorithmSelection getAlgSel() {
		return algSel;
	}

	public GenotypeSelection getGenSel() {
		return genSel;
	}

	public EntryListPanel getRegList() {
		return regList;
	}

	public Algorithm getSelectedAlgorithm() {
		return algSel.getSelectedItem();
	}

	public Genotype getSelectedGenotype() {
		return genSel.getSelectedItem();
	}
	
	public DefinePanel getDefinePanel() {
		return definePanel;
	}

	@Override
	public synchronized void update(ISubject subject) {
		String logFile = subject.getMessage();
		ChartUtils.showResults(logFile);
		subject.removeObserver();
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
