package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DropDownSelection extends JPanel implements ItemListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel cards;
	private List<Algorithm> list;
	private String[] model;
	private JComboBox<String> box = null;
	
	public DropDownSelection(List<Algorithm> list) {
		this.list = list;
		setLayout(new BorderLayout());
		int n = list.size();
		model = new String[n];
		for (int i = 0; i < n; i++) {
			model[i] = list.get(i).toString();
		}
		box = new JComboBox<>(model);
		box.setEditable(false);
		box.addItemListener(this);
		
		cards = new JPanel(new CardLayout());
		
		int size = list.size();
		for (int i = 0; i < size; i++) {
			List<Entry> ent = list.get(i).getEntryList();
			JPanel card = new EntryListPanel(ent);
			cards.add(card, model[i]);
		}
		
		JButton button = new JButton(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button.setText("Run");
		
		add(box, BorderLayout.NORTH);
		add(cards, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) cards.getLayout();
		cl.show(cards, e.getItem().toString());
	}
	
}
