package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

/**
 * This class is a representation of floating point genotype and it extends {@link AbstractGenotype}.
 * It represents a floating point genotype in a form of array list of doubles.
 * @version 1.0
 *
 */
public class FloatingPoint extends AbstractGenotype<Double> {

	/**
	 * Constructor, joust calls super to get the constructor of {@link AbstractGenotype}
	 */
	public FloatingPoint(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Double> getValues() {
		ArrayList<Double> result = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			result.add( Double.parseDouble(e) );
		}
		return result;
	}

}
