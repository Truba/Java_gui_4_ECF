package hr.fer.zemris.ecf.gui;

import hr.fer.zemris.ecf.gui.layout.DropDownSelection;
import hr.fer.zemris.ecf.gui.model.conf.ConfigurationException;
import hr.fer.zemris.ecf.gui.model.conf.ConfigurationKey;
import hr.fer.zemris.ecf.gui.model.conf.IConfiguration;
import hr.fer.zemris.ecf.gui.model.conf.impl.PropertyFile;
import hr.fer.zemris.ecf.gui.model.log.ILog;
import hr.fer.zemris.ecf.gui.model.log.impl.FileLog;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.tasks.TaskMannager;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
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
	private static final String LOG_FILE = "res/log/log.txt";

	private IConfiguration configuration;
	private ILog log;
	private Map<String, Action> actions = new HashMap<>();
	private JMenuBar menuBar = new JMenuBar();
	private JTabbedPane tabbedPane;
	private String ecfPath = "";
	private String paramsPath = "res/dump/paramsDump.txt";

	/**
	 * Creates a new main frame for ECF Lab.
	 */
	public ECFLab() {
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
		setLocation(100, 100);
		setSize(800, 600);
		setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);
		// TODO

		setVisible(true);
	}

	private void initGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});

		log = new FileLog(LOG_FILE);

		try {
			configuration = new PropertyFile(CONFIGURATION_FILE);
		} catch (ConfigurationException e1) {
			log.log(e1);
			reportError("Configuration file error!");
			System.exit(0);
		}

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
				List<Algorithm> list = getAlgortihmList();
				tabbedPane.add("New algorithm", new DropDownSelection(list));
			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Create new experiment");
		actions.put("New", action);
		
		action = new AbstractAction("Open") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Open existing experiment");
		actions.put("Open", action);
		
		action = new AbstractAction("Save") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		};
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		action.putValue(Action.SHORT_DESCRIPTION, "Save experiment");
		actions.put("Save", action);
		
		// TODO nastavak akcija
	}

	protected List<Algorithm> getAlgortihmList() {
		TaskMannager tm = new TaskMannager();
		return tm.getInitialECFparams(ecfPath, paramsPath).algorithms;
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
	protected void reportError(String errorMessage) {
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

	/**
	 * Runs this application.
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				startGUIApp();
			}
		});
	}

	/**
	 * Starts GUI.
	 */
	protected static void startGUIApp() {
		new ECFLab();
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
