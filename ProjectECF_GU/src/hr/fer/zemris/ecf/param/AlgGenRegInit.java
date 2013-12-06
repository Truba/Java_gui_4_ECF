package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

public class AlgGenRegInit {
	

	public List<Algorithm> algorithms;
	public List<Genotype> genotypes;
	public Registry registry;
	
	public AlgGenRegInit() {
		algorithms = new ArrayList<>();
		genotypes = new ArrayList<>();
	}

	public AlgGenRegInit(List<Algorithm> algorithms, List<Genotype> genotypes,
			Registry registry) {
		this.algorithms = algorithms;
		this.genotypes = genotypes;
		this.registry = registry;
	}
	
	

}
