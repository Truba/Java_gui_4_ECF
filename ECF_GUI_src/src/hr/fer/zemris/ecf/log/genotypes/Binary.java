package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;
/**
 * This class is a representation of binary genotype and it extends {@link AbstractGenotype}.
 * It represents a binary genotype in a form of array list of doubles.
 * @version 1.0
 *
 */
public class Binary extends AbstractGenotype<Double> {
	
	
	/**
	 * Constructor, joust calls super to get the constructor of {@link AbstractGenotype}
	 */
	public Binary(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Double> getValues() {
		ArrayList<Double> resoult = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			resoult.add( Double.parseDouble(e) );
		}
		return resoult;
	}

}
