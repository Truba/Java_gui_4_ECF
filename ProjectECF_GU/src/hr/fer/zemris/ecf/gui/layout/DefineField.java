package hr.fer.zemris.ecf.gui.layout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DefineField extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel label;
	private JTextField text;
	
	public DefineField(String key, String value) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		label = new JLabel(key);
		text = new JTextField(value);
		add(label);
		add(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
}
