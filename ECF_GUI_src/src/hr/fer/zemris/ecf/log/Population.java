package hr.fer.zemris.ecf.log;

/**
 * This class  is container for all parameters that are in one population in one {@link Generation} in one {@link LogFile}.
 * All parameters are public and they are to be dealt with manually, although there are constructors to help.
 * @version 1.0
 *
 */

public class Population {
	
	/**
	 * Evaluations in a population.
	 */
	public int evaluations;
	/**
	 * Maximum fitness of a population.
	 */
	public double maxFitness;
	/**
	 * Minimum fitness of a population.
	 */
	public double minFitness;
	/**
	 * Average fitness of a population.
	 */
	public double avgFitness;
	/**
	 * Standard deviation of a fitness of a population.
	 */
	public double stdevFitness;

}
