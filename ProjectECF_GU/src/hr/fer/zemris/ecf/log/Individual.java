package hr.fer.zemris.ecf.log;

import hr.fer.zemris.ecf.log.genotypes.AbstractGenotype;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is container for all parameters that are in one individual in one hall of fame in one {@link LogFile}.
 * All parameters are public and they are to be dealt with manually.
 * It also contains constructor to help a little.
 * @version 1.0
 *
 */
public class Individual {
	
	/**
	 * Size of the individual.
	 */
	public int size;
	/**
	 * generation of the individual.
	 */
	public int gen;
	/**
	 * Max fitness of the individual.
	 */
	public double fitnessMax;
	/**
	 * Array list of AbstractGenotype that belongs to the individual. 
	 */
	public List<AbstractGenotype> genotypes;
	
	/**
	 * Constructor, it initializes the genotypes list to new array list.
	 */
	public Individual(){
		genotypes = new ArrayList<>();
	}

}
