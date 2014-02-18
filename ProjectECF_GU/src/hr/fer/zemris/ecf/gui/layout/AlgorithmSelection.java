package hr.fer.zemris.ecf.gui.layout;

import hr.fer.zemris.ecf.param.Algorithm;
import java.util.List;

/**
 * Panel for algorithm selection.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class AlgorithmSelection extends DropDownPanel<Algorithm> {

	private static final long serialVersionUID = 1L;

	public AlgorithmSelection(List<Algorithm> list) {
		super(list, null);
	}

}
