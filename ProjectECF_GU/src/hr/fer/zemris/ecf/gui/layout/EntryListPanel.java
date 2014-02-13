package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Entry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class EntryListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int RGB = 223;

	private List<EntryFieldPanel> fieldPanels = new ArrayList<>();
	
	public EntryListPanel(List<Entry> list) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int size = list.size();
		for (int i = 0; i < size; i++) {
			EntryFieldPanel fieldPanel = new EntryFieldPanel(list.get(i));
			if (i % 2 == 0) {
				fieldPanel.setBackground(new Color(RGB, RGB, RGB));
			}
			fieldPanels.add(fieldPanel);
			add(fieldPanel);
		}
	}
	
	public List<EntryFieldPanel> getFieldPanels() {
		return fieldPanels;
	}
	
	public boolean isSelected(int index) {
		return fieldPanels.get(index).isSelected();
	}
	
	public String getValueAt(int index) {
		return fieldPanels.get(index).getText();
	}
	
	public String getKeyAt(int index) {
		return fieldPanels.get(index).getLabelText();
	}
	
	public String getDescriptionAt(int index) {
		return fieldPanels.get(index).getToolTipText();
	}

	public int getEntriesCount() {
		return fieldPanels.size();
	}
	
	public EntryFieldPanel getEntryField(String key) {
		for (EntryFieldPanel efp : fieldPanels) {
			if (efp.getLabelText().equals(key)) {
				return efp;
			}
		}
		return null;
	}
	
}
