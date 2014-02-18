package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Entry;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Field for defining existence of specified parameter (check box), parameter
 * name (label) and parameter value (text field).
 * 
 * @author Domagoj Stanković
 * @version 1.0
 */
public class EntryFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JCheckBox checkBox;
	private JLabel label;
	private JTextField text;

	private final Dimension dim = new Dimension(130, 20);

	/**
	 * @param label Parameter name
	 * @param text Parameter value
	 */
	public EntryFieldPanel(JLabel label, JTextField text) {
		this(label, text, BoxLayout.X_AXIS);
	}

	/**
	 * @param label Parameter name
	 * @param text Parameter value
	 * @param tooltipText Parameter description
	 */
	public EntryFieldPanel(JLabel label, JTextField text, String tooltipText) {
		this(label, text, BoxLayout.X_AXIS, tooltipText);
	}

	/**
	 * @param label Parameter name
	 * @param text Parameter value
	 * @param axis List to be laid horizontally {@link BoxLayout}.X_AXIS or vertically {@link BoxLayout}.Y_AXIS
	 */
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

	/**
	 * @param entry Entry to be displayed
	 */
	public EntryFieldPanel(Entry entry) {
		this(new JLabel(entry.key), new JTextField(entry.value), entry.desc);
		boolean b = entry.isMandatory();
		if (b) {
			setSelected(true);
			checkBox.setEnabled(false);
		}
	}

	/**
	 * @param label Parameter name
	 * @param text Parameter value
	 * @param axis List to be laid horizontally {@link BoxLayout}.X_AXIS or vertically {@link BoxLayout}.Y_AXIS
	 * @param tooltipText Parameter description
	 */
	public EntryFieldPanel(JLabel label, JTextField text, int axis, String tooltipText) {
		this(label, text, axis);
		label.setToolTipText(tooltipText);
	}

	/**
	 * @return Text from the text field
	 */
	public String getText() {
		return text.getText();
	}

	/**
	 * @param text Text to be set in the text field
	 */
	public void setText(String text) {
		this.text.setText(text);
	}

	/**
	 * @return Parameter name
	 */
	public String getLabelText() {
		return label.getText();
	}

	/**
	 * @param text Name of the parameter
	 */
	public void setLabelText(String text) {
		label.setText(text);
	}

	/**
	 * @return <code>true</code> if parameter is chosen (check box is selected), <code>false</code> otherwise
	 */
	public boolean isSelected() {
		return checkBox.isSelected();
	}

	/**
	 * @param selected <code>true</code> if parameter should be selected, <code>false</code> otherwise
	 */
	public void setSelected(boolean selected) {
		checkBox.setSelected(selected);
	}

	@Override
	public String toString() {
		return label.getText();
	}

}
