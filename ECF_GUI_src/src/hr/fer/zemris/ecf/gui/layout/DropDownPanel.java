package hr.fer.zemris.ecf.gui.layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.EntryBlock;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Panel with drop down panel for choosing sort of {@link EntryBlock} and list
 * of {@link Entry} fields for defining specific parameters.
 * 
 * @author Domagoj Stanković
 * @version 1.0
 * @param <T>
 *            Sort of {@link EntryBlock} displayed for choice
 */
public class DropDownPanel<T extends EntryBlock> extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;

	protected JPanel cards;
	protected List<T> blocks;
	protected String[] model;
	protected JComboBox<String> box = null;
	protected List<EntryListPanel> algPanels = new ArrayList<>();
	protected CardLayout cardLayout;

	public DropDownPanel(List<T> blocks) {
		this.blocks = blocks;
		setLayout(new BorderLayout());
		int n = blocks.size();
		model = new String[n];
		for (int i = 0; i < n; i++) {
			model[i] = blocks.get(i).toString();
		}
		box = new JComboBox<>(model);
		box.setEditable(false);
		box.addItemListener(this);
		box.setPreferredSize(new Dimension(150, 20));
		box.setMinimumSize(new Dimension(150, 20));
		box.setMaximumSize(new Dimension(150, 20));

		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);

		int size = blocks.size();
		for (int i = 0; i < size; i++) {
			List<Entry> ent = blocks.get(i).getEntryList();
			EntryListPanel card = EntryListPanel.getComponent(ent);
			algPanels.add(card);
			cards.add(card, model[i]);
		}

		add(box, BorderLayout.NORTH);
		add(new JScrollPane(cards), BorderLayout.CENTER);
	}

	/**
	 * @return Selected sort of {@link EntryBlock} that is chosen in the drop down menu
	 */
	public T getSelectedItem() {
		int index = box.getSelectedIndex();
		return blocks.get(index);
	}

	/**
	 * @return Selected list of {@link Entry} objects with specified parameters
	 */
	public EntryListPanel getSelectedEntryList() {
		int index = box.getSelectedIndex();
		return algPanels.get(index);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, e.getItem().toString());
	}

	/**
	 * Shows specific card
	 * @param key Card key
	 */
	public void show(String key) {
		box.setSelectedItem(key);
		cardLayout.show(cards, key);
	}

	/**
	 * Shows specific card
	 * @param index Index of card
	 */
	public void show(int index) {
		box.setSelectedIndex(index);
		cardLayout.show(cards, model[index]);
	}

}
