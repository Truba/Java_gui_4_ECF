package hr.fer.zemris.ecf.gui;

import hr.fer.zemris.ecf.gui.chart.ChartUtils;
import hr.fer.zemris.ecf.gui.display.BrowsePanel;
import hr.fer.zemris.ecf.gui.layout.EntryBlockSelection;
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
import hr.fer.zemris.ecf.xmldom.XmlWriting;

import java.awt.BorderLayout;
import java.awt.Desktop;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
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
	private static final String ICON_NEW_CONF_PATH = "res/img/toolbar/New.png";
	private static final String ICON_OPEN_CONF_PATH = "res/img/toolbar/Open.png";
	private static final String ICON_SAVE_CONF_PATH = "res/img/toolbar/Save.png";
	private static final String ICON_SAVE_CONF_AS_PATH = "res/img/toolbar/Save_as.png";
	private static final String ICON_OPEN_LOG_PATH = "res/img/toolbar/Chart.png";
	private static final String ICON_SAVE_LOG_PATH = "res/img/toolbar/Save_data.png";
	private static final String APP_TITLE = "ECF Lab";
	private static final String APP_ICON_PATH = "res/img/icon/dna_icon.png";

	private IConfiguration configuration;
	private ILog logger;
	private Map<String, Action> actions = new HashMap<>();
	private JMenuBar menuBar = new JMenuBar();
	private JTabbedPane tabbedPane;
	private JToolBar toolbar;
	private String ecfPath;
	private String parDumpPath;
	private AlgGenRegInit parDump;

	/**
	 * Creates a new main frame for ECF Lab.
	 */
	public ECFLab(IConfiguration configuration, ILog logger) {
		this.configuration = configuration;
		this.logger = logger;
		try {
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			setLookAndFeel(true);
			initGUI();

			setTitle(APP_TITLE);
			try {
				Image image = ImageIO.read(new FileInputStream(APP_ICON_PATH));
				setIconImage(image);
			} catch (IOException e) {
				logger.log(e);
			}

			setLocation(300, 100);
			setSize(900, 600);
			setLayout(new BorderLayout());

			add(toolbar, BorderLayout.NORTH);

			tabbedPane = new JTabbedPane();
			add(tabbedPane, BorderLayout.CENTER);

			setVisible(true);
			chooseECFExe();
		} catch (Exception e) {
			logger.log(e);
			reportError(e.getMessage());
		}
	}

	/**
	 * Displays dialog for choosing ECF executable file.
	 */
	private void chooseECFExe() {
		BrowsePanel ecfExePanel = new BrowsePanel();
		int retVal = JOptionPane.showConfirmDialog(this, ecfExePanel, "Choose executable ECF file",
				JOptionPane.OK_CANCEL_OPTION);

		if (retVal == JOptionPane.OK_OPTION) {
			ecfPath = ecfExePanel.getText();
			parDumpPath = configuration.getValue(ConfigurationKey.DEFAULT_PARAMS_DUMP);
			setTitle(APP_TITLE + " - " + ecfPath);
			parDump = callParDump();
		}
	}

	/**
	 * Initializes exit action, other actions and menu bar.
	 */
	private void initGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		initActions();
		initMenuBar();
		initToolbar();
	}

	/**
	 * Initializes toolbar.
	 */
	private void initToolbar() {
		toolbar = new JToolBar();
		toolbar.setFloatable(false);

		JButton button = makeToolbarButton(ICON_NEW_CONF_PATH, "NewConf", "New configuration");
		toolbar.add(button);

		button = makeToolbarButton(ICON_OPEN_CONF_PATH, "OpenConf", "Open existing configuration");
		toolbar.add(button);

		button = makeToolbarButton(ICON_SAVE_CONF_PATH, "SaveConf", "Save configuration");
		toolbar.add(button);

		button = makeToolbarButton(ICON_SAVE_CONF_AS_PATH, "SaveConfAs", "Save configuration As");
		toolbar.add(button);
		
		toolbar.addSeparator();
		
		button = makeToolbarButton(ICON_OPEN_LOG_PATH, "OpenLog", "Open log file");
		toolbar.add(button);
		
		button = makeToolbarButton(ICON_SAVE_LOG_PATH, "SaveLog", "Save log file");
		toolbar.add(button);
	}

	protected JButton makeToolbarButton(String imgPath, String action, String toolTipText) {
		JButton button = new JButton(actions.get(action));
		button.setText("");
		button.setToolTipText(toolTipText);;
		ImageIcon icon = new ImageIcon(imgPath);
		button.setIcon(icon);
		return button;
	}

	/**
	 * Initializes main actions for ECF Lab GUI.
	 */
	private void initActions() {
		Action action;
		action = new AbstractAction("New") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				newTab("New configuration");
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Create new configuration");
		actions.put("NewConf", action);

		action = new AbstractAction("Open") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				openConf();
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Open existing configuration");
		actions.put("OpenConf", action);

		action = new AbstractAction("Save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				saveConf();
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Save configuration");
		actions.put("SaveConf", action);

		action = new AbstractAction("Save As") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				saveConfAs();
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Save configuration as");
		actions.put("SaveConfAs", action);

		action = new AbstractAction("Open") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				openLog();
			}
		};
		action.putValue(Action.SHORT_DESCRIPTION, "Open log file");
		actions.put("OpenLog", action);

		action = new AbstractAction("Save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				saveLog();
			}
		};
		action.putValue(Action.SHORT_DESCRIPTION, "Save log file");
		actions.put("SaveLog", action);

		action = new AbstractAction("Change ECF") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				chooseECFExe();
			}
		};
		action.putValue(Action.SHORT_DESCRIPTION, "Change ECF executable file");
		actions.put("ChangeECFExe", action);

		action = new AbstractAction("ECF home page") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				ecfHomePage();
			}
		};
		action.putValue(Action.SHORT_DESCRIPTION, "Go to ECF home page");
		actions.put("ecfHomePage", action);
	}

	/**
	 * Copies log file created during last experiment to the destination path.
	 */
	protected void saveLog() {
		ParametersSelection ps = (ParametersSelection) tabbedPane.getSelectedComponent();
		boolean b = ps.wasRunBefore();
		if (!b) {
			JOptionPane.showMessageDialog(this, "This experiment was never run before!", "Action unavailable",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		JFileChooser fc = new JFileChooser();
		int retVal = fc.showSaveDialog(this);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			Path source = Paths.get(ps.getLastLogFilePath());
			Path target = Paths.get(file.getAbsolutePath());
			CopyOption options = StandardCopyOption.REPLACE_EXISTING;
			try {
				Files.copy(source, target, options);
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Log file copied successfully!", "Saved successfully",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Opens dialog for choosing log file to be viewed. Then displays results.
	 */
	protected void openLog() {
		BrowsePanel logPathPanel = new BrowsePanel();
		int retVal = JOptionPane.showConfirmDialog(this, logPathPanel, "Choose log file", JOptionPane.OK_CANCEL_OPTION);

		if (retVal == JOptionPane.OK_OPTION) {
			try {
				ChartUtils.showResults(logPathPanel.getText());
			} catch (Exception e) {
				logger.log(e);
				reportError(e.getMessage());
			}
		}
	}

	/**
	 * Displays ECF home page in users default browser.
	 */
	protected void ecfHomePage() {
		URI uri;
		try {
			uri = new URI(configuration.getValue(ConfigurationKey.ECF_HOME_PAGE));
			Desktop.getDesktop().browse(uri);
		} catch (URISyntaxException e) {
			logger.log(e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(e);
			e.printStackTrace();
		}
	}

	/**
	 * Saves current configuration under the name chosen in the file chooser
	 * dialog.
	 */
	protected void saveConfAs() {
		JFileChooser fc = new JFileChooser();
		int retVal = fc.showSaveDialog(this);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String path = file.getAbsolutePath();
			ParametersSelection ps = (ParametersSelection) tabbedPane.getSelectedComponent();
			XmlWriting.write(path, ps.getParameters());
			JOptionPane.showMessageDialog(this, "Saved under name: " + path, "Saved succesfully",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Saves current configuration under the name written in the define panel of
	 * the selected {@link ParametersSelection} panel.
	 */
	protected void saveConf() {
		ParametersSelection ps = (ParametersSelection) tabbedPane.getSelectedComponent();
		String path = ps.getDefinePanel().getParamsPath();
		XmlWriting.write(path, ps.getParameters());
		tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), path);
	}

	protected void openConf() {
		try {
			JFileChooser fc = new JFileChooser();
			int retVal = fc.showOpenDialog(this);
			if (retVal != JFileChooser.APPROVE_OPTION) {
				return;
			}
			File file = fc.getSelectedFile();
			AlgGenRegUser agru = XmlReading.readArchive(file);
			ParametersSelection ps = newTab(file.getAbsolutePath());

			List<Algorithm> algs = agru.algorithm;
			for (Algorithm alg : algs) {
				List<Entry> entries = alg.getEntryList();
				ps.getAlgSel().show(alg.getName());
				EntryListPanel enp = ps.getAlgSel().getSelectedEntryList();
				for (Entry entry : entries) {
					EntryFieldPanel efp = enp.getEntryField(entry.key);
					efp.setSelected(true);
					efp.setText(entry.value);
				}
				EntryBlockSelection<Algorithm> algSel = ps.getAlgSel();
				algSel.add();
			}

			List<Genotype> gens = agru.genotypes.get(0);
			for (Genotype gen : gens) {
				List<Entry> entries = gen.getEntryList();
				ps.getGenSel().show(gen.getName());
				EntryListPanel enp = ps.getGenSel().getSelectedEntryList();
				for (Entry entry : entries) {
					EntryFieldPanel efp = enp.getEntryField(entry.key);
					efp.setSelected(true);
					efp.setText(entry.value);
				}
				EntryBlockSelection<Genotype> genSel = ps.getGenSel();
				genSel.add();
			}

			Registry reg = agru.registry;
			List<Entry> entries = reg.getEntryList();
			EntryListPanel enp = ps.getRegList();
			for (Entry entry : entries) {
				EntryFieldPanel efp = enp.getEntryField(entry.key);
				efp.setSelected(true);
				efp.setText(entry.value);
			}

			ps.getDefinePanel().setParamsPath(file.getAbsolutePath());
		} catch (Exception e) {
			String message = e.getMessage();
			if (message == null) {
				message = "Error";
			}
			message = message.trim();
			reportError(message.isEmpty() ? "Error" : message);
			logger.log(e);
		}
	}

	/**
	 * Creates new tab with {@link ParametersSelection} panel.
	 * 
	 * @param tabName
	 *            Name of the new tab
	 * @return Created {@link ParametersSelection} panel
	 */
	protected ParametersSelection newTab(String tabName) {
		ParametersSelection parSel = new ParametersSelection(this);
		tabbedPane.add(tabName, parSel);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		return parSel;
	}

	/**
	 * Calls parameters dump from ECF executable file.
	 * 
	 * @return
	 */
	protected AlgGenRegInit callParDump() {
		TaskMannager tm = new TaskMannager();
		return tm.getInitialECFparams(ecfPath, parDumpPath);
	}

	/**
	 * Initializes menu bar with all main actions.
	 */
	private void initMenuBar() {
		JMenu confMenu = new JMenu("Configuration");
		confMenu.add(actions.get("NewConf"));
		confMenu.add(actions.get("OpenConf"));
		confMenu.add(actions.get("SaveConf"));
		confMenu.add(actions.get("SaveConfAs"));

		JMenu logMenu = new JMenu("Log");
		logMenu.add(actions.get("OpenLog"));
		logMenu.add(actions.get("SaveLog"));

		JMenu exeMenu = new JMenu("ECF");
		exeMenu.add(actions.get("ChangeECFExe"));
		exeMenu.add(actions.get("ecfHomePage"));

		menuBar.add(confMenu);
		menuBar.add(logMenu);
		menuBar.add(exeMenu);

		setJMenuBar(menuBar);
	}

	/**
	 * Displays warning dialog with specified message and "Error" title.
	 * 
	 * @param errorMessage
	 *            Message to be shown
	 */
	public void reportError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Exit method. Defines actions that have to be done before closing the
	 * frame.
	 */
	protected void exit() {
		boolean b = Boolean.parseBoolean(configuration.getValue(ConfigurationKey.CONFIRM_EXIT));
		if (b) {
			int ret = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Really exit?",
					JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION) {
				dispose();
			}
		} else {
			dispose();
		}
	}

	/**
	 * Logger for errors.
	 * 
	 * @return Error logger
	 */
	public ILog getLogger() {
		return logger;
	}

	/**
	 * External application configuration.
	 * 
	 * @return Configuration
	 */
	public IConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * All main actions.
	 * 
	 * @return Main actions.
	 */
	public Map<String, Action> getActions() {
		return actions;
	}

	/**
	 * Current ECF executable file path.
	 * 
	 * @return ECF exe path
	 */
	public String getEcfPath() {
		return ecfPath;
	}

	/**
	 * Path to the parameters dump file.
	 * 
	 * @return Path to the parameters dump file
	 */
	public String getParDumpPath() {
		return parDumpPath;
	}

	/**
	 * Object with all parameters from the selected ECF exe.
	 * 
	 * @return {@link AlgGenRegInit} object with all the parameters from the
	 *         current ECF executable file.
	 */
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

	/**
	 * Sets {@link LookAndFeel} to the system or to the java look.
	 * 
	 * @param system
	 *            True for system look, false for java look
	 */
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

	/**
	 * Class for handling EDT exceptions.
	 * 
	 * @author Domagoj Stanković
	 * @version 1.0
	 */
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

}
