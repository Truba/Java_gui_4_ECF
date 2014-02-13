package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Entry;

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
	
	private final Dimension dim = new Dimension(130, 20);
	
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
		
		label.setSize(dim);
		label.setPreferredSize(dim);
		label.setMaximumSize(dim);
		label.setMinimumSize(dim);
		
		text.setSize(dim);
		text.setPreferredSize(dim);
		text.setMaximumSize(dim);
		text.setMinimumSize(dim);
		
		setLayout(new BoxLayout(this, axis));
		checkBox = new JCheckBox();
		checkBox.setSelected(false);
		add(checkBox);
		add(label);
		add(text);
	}
	
	public EntryFieldPanel(Entry entry) {
		this(new JLabel(entry.key), new JTextField(entry.value), entry.desc);
		boolean b = entry.isMandatory();
		if (b) {
			setSelected(true);
			checkBox.setEnabled(false);
		}
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
	public String toString() {
		return label.getText();
	}
	
//	@Override
//	public Dimension getPreferredSize() {
//		return new Dimension(250, 20);
//	}
//	
//	@Override
//	public Dimension getMinimumSize() {
//		return new Dimension(250, 20);
//	}
//	
//	@Override
//	public Dimension getMaximumSize() {
//		return new Dimension(250, 20);
//	}
	
}
