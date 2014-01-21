package hr.fer.zemris.ecf.gui.layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.EntryBlock;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public abstract class DropDownPanel<T extends EntryBlock> extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;
	
	protected JPanel cards;
	protected List<T> blocks;
	protected String[] model;
	protected JComboBox<String> box = null;
	protected List<EntryListPanel> algPanels = new ArrayList<>();
	
	public DropDownPanel(List<T> blocks, String buttonText) {
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
		
		cards = new JPanel(new CardLayout());
		
		int size = blocks.size();
		for (int i = 0; i < size; i++) {
			List<Entry> ent = blocks.get(i).getEntryList();
			EntryListPanel card = new EntryListPanel(ent);
			algPanels.add(card);
			cards.add(card, model[i]);
		}
		
		if (buttonText != null) {
			JButton button = new JButton(new AbstractAction() {

				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					clicked();
				}
			});
			button.setText(buttonText);
			add(button, BorderLayout.SOUTH);
		}
		
		add(box, BorderLayout.NORTH);
		add(cards, BorderLayout.CENTER);
	}
	
	protected abstract void clicked();

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, e.getItem().toString());
	}
	
}
