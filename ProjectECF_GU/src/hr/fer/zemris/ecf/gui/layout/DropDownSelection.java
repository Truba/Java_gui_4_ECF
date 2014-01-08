package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Algorithm;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DropDownSelection extends JPanel {

	private static final long serialVersionUID = 1L;
	private Algorithm[] model;
	private JComboBox<Algorithm> box = null;
	
	public DropDownSelection(List<Algorithm> list) {
		setLayout(new FlowLayout());
		int n = list.size();
		model = new Algorithm[n];
		list.toArray(model);
		box = new JComboBox<>(model);
		add(box);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setVisible(true);
		setOpaque(true);
	}
	
}
