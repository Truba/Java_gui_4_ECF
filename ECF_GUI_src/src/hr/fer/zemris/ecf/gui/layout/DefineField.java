package hr.fer.zemris.ecf.gui.layout;

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

	public DefineField(String key, String value) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel(key);
		textField = new JTextField(value);
		add(label);
		add(textField);
	}

	/**
	 * @return Text in the text field
	 */
	public String getText() {
		return textField.getText();
	}

	/**
	 * @param text Text to be written in the text field
	 */
	public void setText(String text) {
		textField.setText(text);
	}

}
