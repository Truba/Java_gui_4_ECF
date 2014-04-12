package hr.fer.zemris.ecf.gui.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ResultProgressFrameDisplayer implements ResultDisplay {

	private ResultProgressFrame frame = null;
	
	@Override
	public void displayResult(final String logFile) throws Exception {
		if (frame == null) {
			frame = ResultProgressFrame.getInstance();
		}
		JButton openButton = new JButton(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new FrameDisplayer().displayResult(logFile);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error while reading log file", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		openButton.setText("Open");
		JButton closeButton = new JButton();
		final OpenResultPanel comp = new OpenResultPanel(logFile, openButton, closeButton);
		closeButton.setAction(new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(comp);
				frame.repaint();
			}
		});
		closeButton.setText("Close");
		frame.add(comp);
		frame.setVisible(true);
	}

}
