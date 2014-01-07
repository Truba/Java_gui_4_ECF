package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

/**
 * This class is a representation of bit string genotype and it extends {@link AbstractGenotype}.
 * It represents a bit string genotype in a form of array list of integers.
 * @version 1.0
 *
 */
public class BitString extends AbstractGenotype<Integer> {
	
	/**
	 * Constructor, joust calls super to get the constructor of {@link AbstractGenotype}
	 */
	public BitString(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Integer> getValues() {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0; i<this.value.length(); i++){
			result.add( (this.value.charAt(i)=='1') ? 1 : 0 );
		}
		return result;
	}

}
