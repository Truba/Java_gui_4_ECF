package hr.fer.zemris.ecfxml.registry;

import hr.fer.zemris.ecfxml.IRegistry;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class Operators implements IRegistry {
	
	private Double mutationIndprob; //<!-- individual mutation probability (regardless of the algorithm!) (default: 0.3) -->
	private Double mutationGenotypes; //<!-- if there are multiple genotypes, which to mutate? 'random': a random one, 'all': mutate all (default: random) -->
	private Double mutationProtected; //<!-- indexes of genotypes that are excluded (protected) from mutation (default: none) -->
	private Double crossoverGenotypes; //<!-- if there are multiple genotypes, which to cross? 'random': a random pair, 'all': all pairs (default: random) -->
	private Double crossoverProtected; //<!-- indexes of genotypes that are excluded (protected) from crossover (default: none) -->

	public Operators() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (mutationIndprob != null)
			sb.append(Xmlhalper.entryTag("mutation.indprob", mutationIndprob)+"\n");
		if (mutationGenotypes != null)
			sb.append(Xmlhalper.entryTag("mutation.genotypes", mutationGenotypes)+"\n");
		if (mutationProtected != null)
			sb.append(Xmlhalper.entryTag("mutation.protected", mutationProtected)+"\n");
		if (crossoverGenotypes != null)
			sb.append(Xmlhalper.entryTag("crossover.genotypes", crossoverGenotypes)+"\n");
		if (crossoverProtected != null)
			sb.append(Xmlhalper.entryTag("crossover.protected", crossoverProtected)+"\n");
		
		
		return sb.toString();
	}

	public void setMutationIndprob(Double mutationIndprob) {
		this.mutationIndprob = mutationIndprob;
	}

	public void setMutationGenotypes(Double mutationGenotypes) {
		this.mutationGenotypes = mutationGenotypes;
	}

	public void setMutationProtected(Double mutationProtected) {
		this.mutationProtected = mutationProtected;
	}

	public void setCrossoverGenotypes(Double crossoverGenotypes) {
		this.crossoverGenotypes = crossoverGenotypes;
	}

	public void setCrossoverProtected(Double crossoverProtected) {
		this.crossoverProtected = crossoverProtected;
	}
	
	
	
}
