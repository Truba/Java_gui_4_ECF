package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

/**
 * This class is extends {@link AbstractGenotype}, and it is a genotype that
 * exists in ECF but that genotype is not implemented as an extension of
 * {@link AbstractGenotype} in this GUI, so it is being threaded as unknown,
 * ergo the name UnknownGenotype.
 * 
 * 
 * @version 1.0
 * 
 */
public class UnknownGenotype extends AbstractGenotype<String> {

	/**
	 * Constructor, joust calls super to get the constructor of
	 * {@link AbstractGenotype}
	 */
	public UnknownGenotype(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<String> getValues() {
		ArrayList<String> list = new ArrayList<>();
		for (String s : this.value.trim().split(" +")) {
			list.add(s);
		}
		return list;
	}

}
