package hr.fer.zemris.ecf.log.genotypes;

public class GenotypeReader {
	
	public static AbstractGenotype getGenotype(InitialGenotype ig) {
		
		switch (ig.name) {
		
		case "BitString":
			return new BitString(ig);
			
		case "Binary":
			return new Binary(ig);
			
		case "FloatingPoint":
			return new FloatingPoint(ig);
			
		case "Tree":
			return new Tree(ig);
			
		case "Permutation":
			return new Permutation(ig);

		default:
			throw new IllegalArgumentException("Genotype: "+ig.name+" has not been implemented.");
		}
	}

}
