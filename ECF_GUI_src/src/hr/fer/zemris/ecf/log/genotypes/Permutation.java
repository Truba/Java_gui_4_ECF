package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

/**
 * This class is a representation of permutation genotype and it extends {@link AbstractGenotype}.
 * It represents a permutation genotype in a form of array list of integers.
 * @version 1.0
 *
 */
public class Permutation extends AbstractGenotype<Integer> {

	/**
	 * Constructor, joust calls super to get the constructor of {@link AbstractGenotype}
	 */
	public Permutation(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Integer> getValues() {
		ArrayList<Integer> result = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			result.add( Integer.parseInt(e) );
		}
		return result;
	}
	
	

}
