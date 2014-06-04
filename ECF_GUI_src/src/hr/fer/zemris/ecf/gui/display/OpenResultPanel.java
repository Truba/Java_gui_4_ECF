package hr.fer.zemris.ecf.gui.display;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel that shows name of the log file and buttons for opening results and
 * removing the panel.
 * 
 * @author Domagoj
 * 
 */
public class OpenResultPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public OpenResultPanel(String logFile, JButton openButton, JButton closeButton) {
		setLayout(new BorderLayout());
		JPanel butPanel = new JPanel();
		butPanel.setLayout(new BoxLayout(butPanel, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		butPanel.add(openButton);
		butPanel.add(closeButton);
		add(butPanel, BorderLayout.EAST);
		add(new JLabel(logFile), BorderLayout.CENTER);
	}

}
