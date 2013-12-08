package hr.fer.zemris.ecf.log;

import hr.fer.zemris.ecf.log.genotypes.AbstractGenotype;

import java.util.ArrayList;
import java.util.List;

public class Individual {
	
	public int size;
	public int gen;
	public double fitnessMax;
	public List<AbstractGenotype> genotypes;
	
	public Individual(){
		genotypes = new ArrayList<>();
	}

}
