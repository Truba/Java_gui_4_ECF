package hr.fer.zemris.ecf.gui.layout;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	private JTextField text;
	
	public EntryFieldPanel(JLabel label, JTextField text) {
		this(label, text, BoxLayout.X_AXIS);
	}
	
	public EntryFieldPanel(JLabel label, JTextField text, int axis) {
		super();
		this.label = label;
		this.text = text;
		setLayout(new BoxLayout(this, axis));
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
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(200, 20);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(300, 20);
	}
	
}
