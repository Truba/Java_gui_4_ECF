package hr.fer.zemris.ecf.gui.layout;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JCheckBox checkBox;
	private JLabel label;
	private JTextField text;
	
	public EntryFieldPanel(JLabel label, JTextField text) {
		this(label, text, BoxLayout.X_AXIS);
	}
	
	public EntryFieldPanel(JLabel label, JTextField text, String tooltipText) {
		this(label, text, BoxLayout.X_AXIS, tooltipText);
	}
	
	public EntryFieldPanel(JLabel label, JTextField text, int axis) {
		super();
		this.label = label;
		this.text = text;
		setLayout(new BoxLayout(this, axis));
		checkBox = new JCheckBox();
		checkBox.setSelected(false);
		add(checkBox);
		add(label);
		add(text);
	}
	
	public EntryFieldPanel(JLabel label, JTextField text, int axis, String tooltipText) {
		this(label, text, axis);
		label.setToolTipText(tooltipText);
	}
	
	public String getText() {
		return text.getText();
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getLabelText() {
		return label.getText();
	}
	
	public void setLabelText(String text) {
		label.setText(text);
	}
	
	public boolean isSelected() {
		return checkBox.isSelected();
	}
	
	public void setSelected(boolean selected) {
		checkBox.setSelected(selected);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(250, 20);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(250, 20);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(250, 20);
	}
	
	@Override
	public String toString() {
		return label.getText();
	}
	
}
