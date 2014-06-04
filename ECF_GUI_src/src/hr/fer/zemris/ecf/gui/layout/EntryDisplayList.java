package hr.fer.zemris.ecf.gui.layout;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A panel that shows a list of {@link EntryFieldDisplay} panels.
 * 
 * @author Domagoj
 * 
 */
public class EntryDisplayList extends JPanel {

	private static final long serialVersionUID = 1L;

	public EntryDisplayList(List<EntryFieldDisplay<?>> fields) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for (EntryFieldDisplay<?> efd : fields) {
			add(efd);
		}
	}

}
