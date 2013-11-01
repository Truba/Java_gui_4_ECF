package hr.fer.zemris.parameters;

import java.util.ArrayList;
import java.util.List;

public class AlgGenReg4Writing {
	
	public Algorithm algorithm;
	public List<Genotype> genotypes;
	public Registry registry;
	
	public AlgGenReg4Writing() {
		genotypes = new ArrayList<>();
	}

	public AlgGenReg4Writing(Algorithm algorithm, List<Genotype> genotypes,
			Registry registry) {
		this.algorithm = algorithm;
		this.genotypes = genotypes;
		this.registry = registry;
	}

}
