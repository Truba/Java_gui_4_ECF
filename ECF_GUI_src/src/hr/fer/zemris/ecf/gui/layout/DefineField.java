package hr.fer.zemris.ecf.gui.layout;

import java.awt.Dimension;

import hr.fer.zemris.ecf.gui.display.BrowsePanel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Field for defining specific property.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class DefineField extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel label;
	private JTextField textField;
	private BrowsePanel browsePanel;
	private boolean browse;

	public DefineField(String key, String value, boolean browse) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel(key);
		this.browse = browse;
		add(label);
		if (browse) {
			browsePanel = new BrowsePanel(value);
			add(browsePanel);
		} else {
			textField = new JTextField(value);
			Dimension dim = new Dimension(200, 20);
			textField.setMinimumSize(dim);
			textField.setMaximumSize(dim);
			textField.setPreferredSize(dim);
//			textField.setSize(dim);
			add(textField);
		}
	}

	/**
	 * @return Text in the text field
	 */
	public String getText() {
		if (browse) {
			return browsePanel.getText();
		} else {
			return textField.getText();
		}
	}

	/**
	 * @param text Text to be written in the text field
	 */
	public void setText(String text) {
		if (browse) {
			browsePanel.setText(text);
		} else {
			textField.setText(text);
		}
	}

}
