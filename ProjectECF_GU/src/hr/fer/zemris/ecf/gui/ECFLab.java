package hr.fer.zemris.ecf.gui;

import hr.fer.zemris.ecf.gui.display.ECFExePathPanel;
import hr.fer.zemris.ecf.gui.layout.EntryFieldPanel;
import hr.fer.zemris.ecf.gui.layout.EntryListPanel;
import hr.fer.zemris.ecf.gui.layout.ParametersSelection;
import hr.fer.zemris.ecf.gui.model.conf.ConfigurationKey;
import hr.fer.zemris.ecf.gui.model.conf.IConfiguration;
import hr.fer.zemris.ecf.gui.model.conf.impl.PropertyFile;
import hr.fer.zemris.ecf.gui.model.log.ILog;
import hr.fer.zemris.ecf.gui.model.log.impl.FileLog;
import hr.fer.zemris.ecf.param.AlgGenRegInit;
import hr.fer.zemris.ecf.param.AlgGenRegUser;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.Genotype;
import hr.fer.zemris.ecf.param.Registry;
import hr.fer.zemris.ecf.tasks.TaskMannager;
import hr.fer.zemris.ecf.xmldom.XmlReading;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * Main frame of the application.
 * 
 * @author Domagoj Stanković
 * @version 1.0
 */
public class ECFLab extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String CONFIGURATION_FILE = "res/conf/conf.properties";

	private IConfiguration configuration;
	private ILog log;
	private Map<String, Action> actions = new HashMap<>();
	private JMenuBar menuBar = new JMenuBar();
	private JTabbedPane tabbedPane;
	private String ecfPath;
	private String paramsPath;
	private AlgGenRegInit parDump;

	/**
	 * Creates a new main frame for ECF Lab.
	 */
	public ECFLab(IConfiguration configuration, ILog log) {
		this.configuration = configuration;
		this.log = log;
		try {
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			setLookAndFeel(true);
			initGUI();

			setTitle(configuration.getValue(ConfigurationKey.APP_TITLE));
			Image image = null;
			try {
				image = ImageIO.read(new FileInputStream(configuration.getValue(ConfigurationKey.APP_ICON_PATH)));
			} catch (IOException e) {
				log.log(e);
			}

			setIconImage(image);
			setLocation(300, 100);
			setSize(900, 600);
			setLayout(new BorderLayout());

			ECFExePathPanel ecfExePanel = new ECFExePathPanel();
			int retVal = JOptionPane.showConfirmDialog(this, ecfExePanel, "Choose executable ECF file",
					JOptionPane.OK_CANCEL_OPTION);

			if (retVal == JOptionPane.CANCEL_OPTION) {
				ecfPath = configuration.getValue(ConfigurationKey.DEFAULT_ECF_EXE_PATH);
			} else {
				ecfPath = ecfExePanel.getText();
			}

			paramsPath = configuration.getValue(ConfigurationKey.DEFAULT_PARAMS_DUMP);
			parDump = callParDump();

			tabbedPane = new JTabbedPane();
			add(tabbedPane, BorderLayout.CENTER);
			// TODO

			setVisible(true);
		} catch (Exception e) {
			log.log(e);
			reportError(e.getMessage());
		}
	}

	private void initGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		initActions();
		initMenuBar();

		// TODO
	}

	private void initActions() {
		Action action;
		action = new AbstractAction("New") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				newTab("New algorithm");
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Create new experiment");
		actions.put("New", action);

		action = new AbstractAction("Open") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				open();
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Open existing experiment");
		actions.put("Open", action);

		action = new AbstractAction("Save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Save experiment");
		actions.put("Save", action);

		// TODO nastavak akcija
	}

	protected void save() {
		// TODO Auto-generated method stub

	}

	protected void open() {
		try {
			JFileChooser fc = new JFileChooser();
			int retVal = fc.showOpenDialog(this);
			if (retVal != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File file = fc.getSelectedFile();
			AlgGenRegUser agru = XmlReading.readArchive(file);
			ParametersSelection ps = newTab(file.getName());

			Algorithm alg = agru.algorithm.get(0);
			List<Entry> entries = alg.getEntryList();
			ps.getAlgSel().show(alg.getName());
			EntryListPanel enp = ps.getAlgSel().getSelectedEntryList();
			for (Entry entry : entries) {
				EntryFieldPanel efp = enp.getEntryField(entry.key);
				efp.setSelected(true);
				efp.setText(entry.value);
			}

			Genotype gen = agru.genotypes.get(0).get(0);
			entries = gen.getEntryList();
			ps.getGenSel().show(gen.getName());
			enp = ps.getGenSel().getSelectedEntryList();
			for (Entry entry : entries) {
				EntryFieldPanel efp = enp.getEntryField(entry.key);
				efp.setSelected(true);
				efp.setText(entry.value);
			}

			Registry reg = agru.registry;
			entries = reg.getEntryList();
			enp = ps.getRegList();
			for (Entry entry : entries) {
				EntryFieldPanel efp = enp.getEntryField(entry.key);
				efp.setSelected(true);
				efp.setText(entry.value);
			}
		} catch (Exception e) {
			String message = e.getMessage();
			if (message == null) {
				message = "Error";
			}
			message.trim();
			reportError(message.isEmpty() ? "Error" : message);
			log.log(e);
		}
	}

	protected ParametersSelection newTab(String tabName) {
		ParametersSelection parSel = new ParametersSelection(this);
		tabbedPane.add(tabName, parSel);
		return parSel;
	}

	protected AlgGenRegInit callParDump() {
		TaskMannager tm = new TaskMannager();
		return tm.getInitialECFparams(ecfPath, paramsPath);
	}

	private void initMenuBar() {
		JMenu fileMenu = new JMenu("File");

		fileMenu.add(actions.get("New"));
		fileMenu.add(actions.get("Open"));
		fileMenu.add(actions.get("Save"));
		menuBar.add(fileMenu);

		setJMenuBar(menuBar);
	}

	/**
	 * Displays warning dialog with specified message and "Error" title.
	 * 
	 * @param errorMessage
	 *            Message to be shown
	 */
	public void reportError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Exit method. Defines actions that have to be done before closing the
	 * frame.
	 */
	protected void exit() {
		// TODO
		dispose();
	}

	public ILog getLog() {
		return log;
	}

	public IConfiguration getConfiguration() {
		return configuration;
	}

	public Map<String, Action> getActions() {
		return actions;
	}

	public String getEcfPath() {
		return ecfPath;
	}

	public String getParamsPath() {
		return paramsPath;
	}

	public AlgGenRegInit getParDump() {
		return parDump;
	}

	/**
	 * Runs this application.
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		final IConfiguration configuration = new PropertyFile(CONFIGURATION_FILE);
		final ILog log = new FileLog(configuration.getValue(ConfigurationKey.LOG_FILE_PATH));

		Thread.setDefaultUncaughtExceptionHandler(new EDTExceptionHandler(log));
		System.setProperty("sun.awt.exception.handler", EDTExceptionHandler.class.getName());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				startGUIApp(configuration, log);
			}
		});
	}

	/**
	 * Starts GUI.
	 */
	protected static void startGUIApp(IConfiguration configuration, ILog log) {
		new ECFLab(configuration, log);
	}

	protected void setLookAndFeel(boolean system) {
		String newLookAndFeel = system ? UIManager.getSystemLookAndFeelClassName() : UIManager
				.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(newLookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static class EDTExceptionHandler implements Thread.UncaughtExceptionHandler {

		private ILog log;

		public EDTExceptionHandler(ILog log) {
			super();
			this.log = log;
		}

		public void handle(Throwable thrown) {
			log.log(getStackTraceString(thrown.getStackTrace()));
		}

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			e.printStackTrace(ps);
			try {
				String message = baos.toString(StandardCharsets.UTF_8.name());
				log.log(message);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}

		private String getStackTraceString(StackTraceElement[] stackTrace) {
			StringBuilder sb = new StringBuilder();
			for (StackTraceElement ste : stackTrace) {
				sb.append(ste.toString() + "\n");
			}
			return sb.toString();
		}

	}

	// URI uri;
	// try {
	// uri = new URI("https://www.google.hr/");
	// Desktop.getDesktop().browse(uri);
	// } catch (URISyntaxException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }

}
