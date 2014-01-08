package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;
/**
 * This class is a container for the initial parameters dumped by the ECF.
 * @version 1.0
 *
 */
public class AlgGenRegInit {
	
	/**
	 * Array list of all available {@link Algorithm}s from ECF.
	 */
	public List<Algorithm> algorithms;
	/**
	 * Array list of all available {@link Genotype} form ECF.
	 */
	public List<Genotype> genotypes;
	/**
	 * {@link Registry} from ECF.
	 */
	public Registry registry;
	
	/**
	 * Constructor, it initializes algorithms and genotypes list to new array lists. 
	 */
	public AlgGenRegInit() {
		algorithms = new ArrayList<>();
		genotypes = new ArrayList<>();
	}

	/**
	 * Constructor it gets references to algorithms and genotypes list and registry
	 * @param algorithms given algorithms list pointer
	 * @param genotypes given genotypes list pointer
	 * @param registry given registry pointer
	 */
	public AlgGenRegInit(List<Algorithm> algorithms, List<Genotype> genotypes,
			Registry registry) {
		this.algorithms = algorithms;
		this.genotypes = genotypes;
		this.registry = registry;
	}
	
	

}
