package hr.fer.zemris.ecf.gui.layout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GenotypeDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JTextField text;
	
	public GenotypeDisplay(JLabel label, JTextField text) {
		super();
		this.label = label;
		this.text = text;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(label);
		add(text);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public String getLabelText() {
		return label.getText();
	}
	
	public void setLabelText(String text) {
		label.setText(text);
	}
	
}
