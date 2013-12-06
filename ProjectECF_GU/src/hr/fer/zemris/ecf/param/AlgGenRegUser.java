package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

public class AlgGenRegUser {
	
	public List<Algorithm> algorithm;
	public List<List<Genotype>> genotypes;
	public Registry registry;
	public String userComment;
	
	public AlgGenRegUser() {
		genotypes = new ArrayList<>();
	}

	public AlgGenRegUser(List<Algorithm> algorithm, List<List<Genotype>> genotypes,
			Registry registry) {
		this.algorithm = algorithm;
		this.genotypes = genotypes;
		this.registry = registry;
	}
	
	public String getUserComment(){
		if (!userComment.isEmpty()){
			return userComment;
		}
		return "There are no user comments for this paramethers.";
	}

}
