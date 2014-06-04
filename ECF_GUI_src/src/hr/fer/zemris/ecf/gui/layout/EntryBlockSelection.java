package hr.fer.zemris.ecf.gui.layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.ecf.gui.layout.impl.FrameDisplay;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.EntryBlock;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Block that displays all added entries, drop-down menu for selecting new entry
 * and a button that adds new {@link Entry}.
 * 
 * @author Domagoj
 * 
 * @param <T>
 */
public class EntryBlockSelection<T extends EntryBlock> extends JPanel implements IFieldListener {

	private static final long serialVersionUID = 1L;

	protected EntryDisplayList addedEntries;
	protected DropDownPanel<T> dropDown;
	protected JButton addButton;

	public EntryBlockSelection(EntryDisplayList addedEntries, DropDownPanel<T> dropDown) {
		super();
		this.addedEntries = addedEntries;
		this.dropDown = dropDown;
		addButton = new JButton(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		setLayout(new BorderLayout());
		add(addedEntries, BorderLayout.NORTH);
		add(dropDown, BorderLayout.CENTER);
		addButton.setText("Add");
		add(addButton, BorderLayout.SOUTH);
	}

	public EntryBlockSelection(DropDownPanel<T> dropDown) {
		this(new EntryDisplayList(new ArrayList<EntryFieldDisplay<?>>()), dropDown);
	}

	/**
	 * @return Selected sort of {@link EntryBlock} that is chosen in the drop
	 *         down menu
	 */
	public T getSelectedItem() {
		return dropDown.getSelectedItem();
	}

	/**
	 * @return Selected list of {@link Entry} objects with specified parameters
	 */
	public EntryListPanel getSelectedEntryList() {
		return dropDown.getSelectedEntryList();
	}

	/**
	 * Shows specific card.
	 * 
	 * @param key
	 *            Card key
	 */
	public void show(String key) {
		dropDown.show(key);
	}

	/**
	 * Shows specific card.
	 * 
	 * @param index
	 *            Index of card
	 */
	public void show(int index) {
		dropDown.show(index);
	}

	@Override
	public void removeField(EntryFieldDisplay<?> field) {
		addedEntries.remove(field);
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.repaint();
	}

	public void add() {
		addedEntries.add(new FrameDisplay<T>(this, getSelectedItem(), getSelectedEntryList().copy()));
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		frame.repaint();
	}

	@SuppressWarnings("unchecked")
	public List<EntryFieldDisplay<T>> getAddedEntries() {
		Component[] components = addedEntries.getComponents();
		List<EntryFieldDisplay<T>> list = new ArrayList<>(components.length);
		for (Component comp : components) {
			list.add((EntryFieldDisplay<T>) comp);
		}
		return list;
	}

}
