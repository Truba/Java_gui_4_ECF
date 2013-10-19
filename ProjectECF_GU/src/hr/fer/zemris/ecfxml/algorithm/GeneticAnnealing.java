package hr.fer.zemris.ecfxml.algorithm;

import hr.fer.zemris.ecfxml.IAlgorithm;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class GeneticAnnealing implements IAlgorithm {
	
	private double startingEnergyBank; //<!-- total starting energy bank (fitness dependant) -->
	private double coolingFactor; //<!-- simulated annealing cooling factor -->
	private double iBestIndividualPreserved; //<!-- is the best individual preserved -->
	
	
	public GeneticAnnealing(double startingEnergyBank, double coolingFactor,
			double iBestIndividualPreserved) {
		this.startingEnergyBank = startingEnergyBank;
		this.coolingFactor = coolingFactor;
		this.iBestIndividualPreserved = iBestIndividualPreserved;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<GeneticAnnealing>\n").
		append(Xmlhalper.entryTag("energybank", startingEnergyBank)+"\n").
		append(Xmlhalper.entryTag("coolingfactor", coolingFactor)+"\n"). 
		append(Xmlhalper.entryTag("elitism", iBestIndividualPreserved)+"\n"). 
		append("</GeneticAnnealing>");
		return sb.toString();
	}
	
	

}
