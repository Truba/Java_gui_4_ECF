package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is a representation of tree genotype and it extends {@link AbstractGenotype}.
 * It represents a tree genotype in a form of array list of strings.
 * @version 1.0
 *
 */
public class Tree extends AbstractGenotype<String> {
	
	/**
	 * Constructor, joust calls super to get the constructor of {@link AbstractGenotype}
	 */
	public Tree(InitialGenotype ig) {
		super(ig);
	}
	/**
	 * This method is used to get the values represented how they need to be as the array list of strings that is best for representing tree.
	 * This is still in prefix form.
	 * @return array list of values represented as they need to be for drawing.
	 */
	@Override
	public ArrayList<String> getValues() {
		String[] list = this.value.trim().split(" +");
		return new ArrayList<String>(Arrays.asList(list));
	}

}
