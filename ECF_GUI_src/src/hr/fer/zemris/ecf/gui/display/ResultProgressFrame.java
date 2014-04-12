package hr.fer.zemris.ecf.gui.display;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class ResultProgressFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static ResultProgressFrame instance;
	private JPanel pan = null;
	
	private ResultProgressFrame() {
		super();
		setTitle("Results");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLocation(300, 200);
		setSize(400, 200);
		pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		getContentPane().add(new JScrollPane(pan));
	}
	
	public static ResultProgressFrame getInstance() {
		if (instance == null) {
			instance = new ResultProgressFrame();
		}
		return instance;
	}
	
	public static void disposeInstance() {
		if (instance != null) {
			instance.dispose();
		}
	}
	
	@Override
	public Component add(Component comp) {
		return pan.add(comp);
	}
	
//	@Override
//	public Component add(Component comp, int index) {
//		return pan.add(comp, index);
//	}
//	
//	@Override
//	public void add(Component comp, Object constraints) {
//		pan.add(comp, constraints);
//	}
//	
//	@Override
//	public void add(Component comp, Object constraints, int index) {
//		pan.add(comp, constraints, index);
//	}
//	
//	@Override
//	public Component add(String name, Component comp) {
//		return pan.add(name, comp);
//	}
//	
	@Override
	public void remove(Component comp) {
		pan.remove(comp);
		getContentPane().validate();
		getContentPane().repaint();
	}
//	
//	@Override
//	public void remove(int index) {
//		pan.remove(index);
//	}
	
}

/*
 * private ResultProgressFrame() {
		setTitle("Results");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLocation(300, 200);
		setSize(400, 200);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	}
	
	public static ResultProgressFrame getInstance() {
		if (instance == null) {
			instance = new ResultProgressFrame();
		}
		return instance;
	}
	
	public static void disposeInstance() {
		if (instance != null) {
			instance.dispose();
		}
	}
 * */
