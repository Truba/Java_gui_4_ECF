package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.EntryBlock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class EntryFieldDisplay<T extends EntryBlock> extends JPanel {

	private static final long serialVersionUID = 1L;

	protected static final Icon DELETE_ICON = new ImageIcon("res/img/Erase.png");
	
	protected JButton display;
	protected JButton delete;
	protected T block;
	protected EntryListPanel blockDisplay;
	
	public EntryFieldDisplay(final IFieldListener listener, T block, EntryListPanel blockDisplay) {
		this.block = block;
		this.blockDisplay = blockDisplay;
		setLayout(new BorderLayout());
		Action displayAction = displayAction();
		display = new JButton(displayAction);
		display.setText(block.getName());
		
		Action deleteAction = new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				listener.removeField(EntryFieldDisplay.this);
			}
		};
		delete = new JButton(deleteAction);
		delete.setIcon(DELETE_ICON);
		
		add(display, BorderLayout.CENTER);
		add(delete, BorderLayout.EAST);
	}
	
	public T getBlock() {
		return block;
	}
	
	public EntryListPanel getBlockDisplay() {
		return blockDisplay;
	}
	
	protected abstract Action displayAction();
	
}
