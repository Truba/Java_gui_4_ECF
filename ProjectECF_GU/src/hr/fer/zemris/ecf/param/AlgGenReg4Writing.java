package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

public class AlgGenReg4Writing {
	
	public List<Algorithm> algorithm;
	public List<List<Genotype>> genotypes;
	public Registry registry;
	
	public AlgGenReg4Writing() {
		genotypes = new ArrayList<>();
	}

	public AlgGenReg4Writing(List<Algorithm> algorithm, List<List<Genotype>> genotypes,
			Registry registry) {
		this.algorithm = algorithm;
		this.genotypes = genotypes;
		this.registry = registry;
	}

}
