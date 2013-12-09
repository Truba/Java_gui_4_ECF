package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;
import java.util.Arrays;

public class Tree extends AbstractGenotype<String> {

	public Tree(InitialGenotype ig) {
		super(ig);
	}
	
	/**
	 * This is still in prefix form.
	 */
	@Override
	public ArrayList<String> getValues() {
		String[] list = this.value.trim().split(" +");
		return new ArrayList<String>(Arrays.asList(list));
	}

}
