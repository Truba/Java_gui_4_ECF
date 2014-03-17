package hr.fer.zemris.ecf.gui.layout;

import java.util.List;

import hr.fer.zemris.ecf.param.Genotype;

/**
 * Panel for genotype selection.
 * @author Domagoj Stanković
 * @version 1.0
 */
public class GenotypeSelection extends DropDownPanel<Genotype> {

	private static final long serialVersionUID = 1L;
	
	public GenotypeSelection(List<Genotype> blocks) {
		super(blocks);
	}

}
