package hr.fer.zemris.gui;

import hr.fer.zemris.gui.model.conf.ConfigurationException;
import hr.fer.zemris.gui.model.conf.ConfigurationKey;
import hr.fer.zemris.gui.model.conf.IConfiguration;
import hr.fer.zemris.gui.model.conf.impl.PropertyFile;
import hr.fer.zemris.gui.model.log.ILog;
import hr.fer.zemris.gui.model.log.impl.FileLog;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Main frame of the application.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class ECFLab extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final String CONFIGURATION_FILE = "res/conf/conf.properties";
	private static final String LOG_FILE = "res/log/log.txt";

	private IConfiguration configuration;
	private ILog log;
	private JMenuBar menuBar = new JMenuBar();
	
	/**
	 * Creates a new main frame for ECF Lab.
	 */
	public ECFLab() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
		
		initMenuBar();
		
		// TODO
	}

	private void initMenuBar() {
		JMenu fileMenu = new JMenu();
		menuBar.add(fileMenu);
	}

	/**
	 * Displays warning dialog with specified message and "Error" title.
	 * @param errorMessage Message to be shown
	 */
	protected void reportError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Exit method. Defines actions that have to be done before closing the frame.
	 */
	protected void exit() {
		// TODO
		dispose();
	}

	/**
	 * Runs this application.
	 * @param args Not used
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
	
//	URI uri;
//	try {
//		uri = new URI("https://www.google.hr/");
//		Desktop.getDesktop().browse(uri);
//	} catch (URISyntaxException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
	
}
