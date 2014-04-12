package hr.fer.zemris.ecf.gui.display;

import hr.fer.zemris.ecf.gui.Utils;
import hr.fer.zemris.ecf.gui.model.log.ILog;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ResultProgressFrameDisplayer implements IResultDisplay {

	private ResultProgressFrame frame = null;
	private ILog logger = null;
	
	public ResultProgressFrameDisplayer(ILog logger) {
		super();
		this.logger = logger;
	}

	@Override
	public void displayResult(String logFile) throws Exception {
		if (frame == null) {
			frame = ResultProgressFrame.getInstance();
		}
		addComp(logFile);
		File f = new File(logFile + Utils.LOG_EXT);
		if (f.exists()) {
			Scanner sc = new Scanner(f);
			String line1 = sc.nextLine();
			String path = sc.nextLine();
			sc.close();
			int num = Integer.parseInt(line1);
			int len = String.valueOf(num).length();
			for (int i = 1; i <= num; i++) {
				addComp(Utils.addBeforeExtension(path, i, len));
			}
			f.delete();
		}
		frame.setVisible(true);
	}
	
	private void addComp(final String logFile) {
		JButton openButton = new JButton(new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new FrameDisplayer().displayResult(logFile);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "An error occured while reading log file",
							JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					logger.log(e1);
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
	}

}
