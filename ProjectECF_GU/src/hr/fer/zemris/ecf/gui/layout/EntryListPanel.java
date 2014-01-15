package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Entry;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EntryListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Entry> list;
	
	public EntryListPanel(List<Entry> list) {
		this.list = list;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		int size = list.size();
		for (int i = 0; i < size; i++) {
			JLabel label = new JLabel(list.get(i).key);
			JTextField text = new JTextField(list.get(i).value);
			add(new EntryFieldPanel(label, text));
		}
	}
	
	public List<Entry> getList() {
		return list;
	}
	
}
