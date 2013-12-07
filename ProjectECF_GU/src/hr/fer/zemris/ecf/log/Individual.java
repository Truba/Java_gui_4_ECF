package hr.fer.zemris.ecf.log;

import hr.fer.zemris.ecf.log.genotypes.InitialGenotype;

import java.util.ArrayList;
import java.util.List;

public class Individual {
	
	public int size;
	public int gen;
	public double fitnessMax;
	public List<InitialGenotype> genotypes;
	
	public Individual(){
		genotypes = new ArrayList<>();
	}

}
