package hr.fer.zemris.parameters;

import java.util.ArrayList;
import java.util.List;

public class AlgGenRegList {
	

	public List<Algorithm> algorithms;
	public List<Genotype> genotypes;
	public Registry registry;
	
	public AlgGenRegList() {
		algorithms = new ArrayList<>();
		genotypes = new ArrayList<>();
	}

	public AlgGenRegList(List<Algorithm> algorithms, List<Genotype> genotypes,
			Registry registry) {
		this.algorithms = algorithms;
		this.genotypes = genotypes;
		this.registry = registry;
	}
	
	

}
