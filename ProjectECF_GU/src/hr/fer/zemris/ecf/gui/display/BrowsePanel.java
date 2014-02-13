package hr.fer.zemris.ecf.gui.display;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BrowsePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField text;
	private JButton button;
	private File file = null;
	
	public BrowsePanel() {
		super();
		text = new JTextField("");
		text.setMinimumSize(new Dimension(200, 20));
		text.setMaximumSize(new Dimension(200, 20));
		text.setPreferredSize(new Dimension(200, 20));
		text.setSize(new Dimension(200, 20));
		button = new JButton(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				click();
			}
		});
		button.setText("Browse");
		
		add(text);
		add(button);
	}
	
	protected void click() {
		JFileChooser fc = new JFileChooser();
		int retVal = fc.showDialog(this, "Choose");
		if (retVal != JFileChooser.APPROVE_OPTION) {
			return;
		}
		file = fc.getSelectedFile();
		text.setText(file.getAbsolutePath());
	}

	public String getText() {
		return text.getText();
	}
	
	public File getFile() {
		return file;
	}
	
}
