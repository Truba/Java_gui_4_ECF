package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.console.IObserver;
import hr.fer.zemris.ecf.console.ISubject;
import hr.fer.zemris.ecf.console.Job;
import hr.fer.zemris.ecf.gui.ECFLab;
import hr.fer.zemris.ecf.gui.Utils;
import hr.fer.zemris.ecf.gui.model.conf.ConfigurationKey;
import hr.fer.zemris.ecf.param.AlgGenRegUser;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.Genotype;
import hr.fer.zemris.ecf.param.Registry;
import hr.fer.zemris.ecf.tasks.TaskMannager;
import hr.fer.zemris.ecf.xmldom.XmlWriting;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

/**
 * Panel that displays available parameters for the selected ECF executable
 * file. When all the parameters are selected, configuration can be ran.
 * Configuration and log files are created on the specified paths.
 * 
 * @author Domagoj Stanković
 * @version 1.0
 */
public class ParametersSelection extends JPanel implements IObserver {

	private static final long serialVersionUID = 1L;

	private static final boolean DAEMON = false;

	private ECFLab parent;
	private EntryBlockSelection<Algorithm> algSel;
	private EntryBlockSelection<Genotype> genSel;
	private EntryListPanel regList;
	private String ecfPath;
	private DefinePanel definePanel;
	private String lastLogFilePath = null;

	/**
	 * Creates new {@link ParametersSelection} object for choosing ECF
	 * parameters.
	 * 
	 * @param parent
	 *            {@link ECFLab} frame that displays this panel
	 */
	public ParametersSelection(ECFLab parent) {
		super(new BorderLayout());
		this.parent = parent;
		ecfPath = parent.getEcfPath();
		if (ecfPath == null) {
			throw new NullPointerException("ECF executable file undefined!");
		}
		algSel = new EntryBlockSelection<>(new DropDownPanel<>(parent.getParDump().algorithms));
		genSel = new EntryBlockSelection<>(new DropDownPanel<>(parent.getParDump().genotypes));
		regList = EntryListPanel.getComponent(parent.getParDump().registry.getEntryList());
		String file = parent.getConfiguration().getValue(ConfigurationKey.DEFAULT_PARAMS_PATH);
		String log = parent.getConfiguration().getValue(ConfigurationKey.DEFAULT_LOG_PATH);
		// add(new JScrollPane(new TempPanel(algSel, genSel, regList)),
		// BorderLayout.CENTER);
		// add(new TempPanel(new JScrollPane(algSel), new JScrollPane(genSel),
		// new JScrollPane(regList)), BorderLayout.CENTER);
		add(new TempPanel(algSel, genSel, new JScrollPane(regList)), BorderLayout.CENTER);
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

	/**
	 * Action performed when the "Run" button is clicked. Configuration file is
	 * created under the specified path. Then the ECF exe is run and the results
	 * are written to the log file under the specified path.
	 */
	protected void clicked() {
		try {
			AlgGenRegUser temp = getParameters();
			String file = definePanel.getParamsPath();
			String log = definePanel.getLogPath();
			lastLogFilePath = log;
			int pn = definePanel.getThreadsCount();
			boolean change = false;
			int repeats = 1;
			List<Entry> list = temp.registry.getEntryList();
			Entry e = Utils.findEntry(list, "batch.repeats");
			if (pn > 1) {
				if (e != null) {
					repeats = Integer.parseInt(e.value);
					if (repeats > 1) {
						// N repeats, N threads -> separate repeats in N jobs (1 repeat per job)
						e.value = "1";
						change = true;
					} else {
						// 1 job (1 repeat), N threads -> change to 1 thread
						pn = 1;
					}
				} else {
					// 1 job, N threads -> change to 1 thread
					pn = 1;
				}
			} else {
				if (e != null) {
					repeats = Integer.parseInt(e.value);
					if (repeats > 1) {
						Entry l = Utils.findEntry(list, "log.filename");
						if (l != null) {
							String value = l.value;
							FileWriter fw = new FileWriter(log + Utils.LOG_EXT);
							fw.write(repeats + "\n");
							fw.write(value);
							fw.close();
						}
					}
				}
			}
			XmlWriting.write(file, temp);
			final List<Job> jobs;
			if (change) {
				jobs = new ArrayList<>(repeats);
				for (int i = 0; i < repeats; i++) {
					String newLog = Utils.addBeforeExtension(log, (i + 1), String.valueOf(repeats).length());
					Job job = new Job(ecfPath, newLog, file);
					job.setObserver(this);
					jobs.add(job);
				}
			} else {
				jobs = new ArrayList<>(1);
				Job job = new Job(ecfPath, log, file);
				job.setObserver(this);
				jobs.add(job);
			}
			final int tCount = pn;
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					TaskMannager tm = new TaskMannager();
					try {
						tm.startTasks(jobs, tCount);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			t.setDaemon(DAEMON);
			t.start();
		} catch (Exception e) {
			String message = e.getMessage();
			if (message.startsWith("java.lang.Exception: ")) {
				message = message.substring(21);
			}
			parent.reportError(message);
			parent.getLogger().log(e);
		}
	}
	
	/**
	 * Collects all the selected parameters from the selected
	 * {@link ParametersSelection} panel.
	 * 
	 * @return {@link AlgGenRegUser} object containing all the selected
	 *         parameters
	 */
	public AlgGenRegUser getParameters() {
		// Algorithm filling
		List<EntryFieldDisplay<Algorithm>> algList = algSel.getAddedEntries();
		List<Algorithm> algs = new ArrayList<>(algList.size());
		for (EntryFieldDisplay<Algorithm> a : algList) {
			algs.add(new Algorithm(a.getBlock().getName(), a.getBlockDisplay().getSelectedEntries()));
		}

		// Genotype filling
		List<EntryFieldDisplay<Genotype>> genList = genSel.getAddedEntries();
		List<Genotype> gens = new ArrayList<>(genList.size());
		for (EntryFieldDisplay<Genotype> g : genList) {
			gens.add(new Genotype(g.getBlock().getName(), g.getBlockDisplay().getSelectedEntries()));
		}
		List<List<Genotype>> genBlock = new ArrayList<>(1);
		genBlock.add(gens);

		// Registry filling
		int size = regList.getEntriesCount();
		List<Entry> entries = new ArrayList<>();
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

	/**
	 * @return {@link AlgorithmSelection} from the selected
	 *         {@link ParametersSelection} panel
	 */
	public EntryBlockSelection<Algorithm> getAlgSel() {
		return algSel;
	}

	/**
	 * @return {@link GenotypeSelection} from the selected
	 *         {@link ParametersSelection} panel
	 */
	public EntryBlockSelection<Genotype> getGenSel() {
		return genSel;
	}

	/**
	 * @return {@link EntryFieldPanel} representing Registry from the selected
	 *         {@link ParametersSelection} panel
	 */
	public EntryListPanel getRegList() {
		return regList;
	}

	/**
	 * @return Selected {@link Algorithm} from the selected
	 *         {@link ParametersSelection} panel
	 */
	public Algorithm getSelectedAlgorithm() {
		return algSel.getSelectedItem();
	}

	/**
	 * @return Selected {@link Genotype} from the selected
	 *         {@link ParametersSelection} panel
	 */
	public Genotype getSelectedGenotype() {
		return genSel.getSelectedItem();
	}

	/**
	 * @return {@link DefinePanel} containing information about configuration
	 *         and log files paths
	 */
	public DefinePanel getDefinePanel() {
		return definePanel;
	}

	@Override
	public void update(final ISubject subject) throws Exception {
		// String logFile = subject.getMessage();
		// parent.getResultDisplay().displayResult(logFile);
		// subject.removeObserver();

		SwingUtilities.invokeAndWait(new Runnable() {

			@Override
			public void run() {
				String logFile = subject.getMessage();
				synchronized (parent) {
					try {
						parent.getResultDisplay().displayResult(logFile);
					} catch (Exception e) {
						parent.getLogger().log(e);
						parent.reportError(e.getMessage());
					}
				}
				subject.removeObserver();
			}
		});
	}

	/**
	 * Panel used for grouping {@link AlgorithmSelection},
	 * {@link GenotypeSelection} and {@link EntryFieldPanel} panels.
	 * 
	 * @author Domagoj Stanković
	 * @version 1.0
	 */
	private static class TempPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public TempPanel(Component algSel, Component genSel, Component regList) {
			super();
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			add(algSel);
			add(new JSeparator(JSeparator.VERTICAL));
			add(genSel);
			add(new JSeparator(JSeparator.VERTICAL));
			add(regList);
		}

	}

	/**
	 * @return <code>true</code> if "Run" button was ever run before,
	 *         <code>false</code> otherwise
	 */
	public boolean wasRunBefore() {
		return lastLogFilePath == null ? false : true;
	}

	/**
	 * @return Path to the log file created during last experiment,
	 *         <code>null</code> if selected experiment was never run before
	 */
	public String getLastLogFilePath() {
		return lastLogFilePath;
	}

}
