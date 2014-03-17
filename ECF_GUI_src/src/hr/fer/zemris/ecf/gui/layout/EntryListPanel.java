package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Entry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * List of {@link EntryFieldPanel} objects.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class EntryListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int RGB = 223;

	private List<EntryFieldPanel> fieldPanels = new ArrayList<>();
	
	public EntryListPanel(List<EntryFieldPanel> list) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int size = list.size();
		for (int i = 0; i < size; i++) {
			EntryFieldPanel fieldPanel = list.get(i);
			if (i % 2 == 0) {
				fieldPanel.setBackground(new Color(RGB, RGB, RGB));
			}
			fieldPanels.add(fieldPanel);
			add(fieldPanel);
		}
	}
	
	/**
	 * @return List of {@link EntryFieldPanel} objects
	 */
	public List<EntryFieldPanel> getFieldPanels() {
		return fieldPanels;
	}
	
	/**
	 * @param index Index of specified {@link EntryFieldPanel}
	 * @return <code>true</code> if field is selected, <code>false</code> otherwise
	 */
	public boolean isSelected(int index) {
		return fieldPanels.get(index).isSelected();
	}
	
	/**
	 * @param index Index of specified {@link EntryFieldPanel}
	 * @return Value from the text field on the specified index
	 */
	public String getValueAt(int index) {
		return fieldPanels.get(index).getText();
	}
	
	/**
	 * @param index Index of specified {@link EntryFieldPanel}
	 * @return Key from the label at the specified index
	 */
	public String getKeyAt(int index) {
		return fieldPanels.get(index).getLabelText();
	}
	
	/**
	 * @param index Index of specified {@link EntryFieldPanel}
	 * @return Description of the parameter at the specified index
	 */
	public String getDescriptionAt(int index) {
		return fieldPanels.get(index).getToolTipText();
	}

	/**
	 * @return Number of entries in the list
	 */
	public int getEntriesCount() {
		return fieldPanels.size();
	}
	
	/**
	 * @param key Entry field key
	 * @return {@link EntryFieldPanel} with the specified key
	 */
	public EntryFieldPanel getEntryField(String key) {
		for (EntryFieldPanel efp : fieldPanels) {
			if (efp.getLabelText().equals(key)) {
				return efp;
			}
		}
		return null;
	}
	
	public List<Entry> getSelectedEntries() {
		int size = getEntriesCount();
		List<Entry> entries = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (isSelected(i)) {
				entries.add(new Entry(getKeyAt(i), getDescriptionAt(i), getValueAt(i)));
			}
		}
		return entries;
	}
	
	public static EntryListPanel getComponent(List<Entry> list) {
		List<EntryFieldPanel> fields = new ArrayList<>(list.size());
		for (Entry e : list) {
			fields.add(new EntryFieldPanel(e));
		}
		return new EntryListPanel(fields);
	}
	
	public EntryListPanel copy() {
		List<EntryFieldPanel> list = new ArrayList<>(fieldPanels.size());
		for (EntryFieldPanel efp : fieldPanels) {
			list.add(efp.copy());
		}
		return new EntryListPanel(list);
	}
	
}
