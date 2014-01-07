package hr.fer.zemris.ecf.log;

/**
 * This class is container for all parameters that are in one deme in one {@link Generation} in one {@link LogFile}.
 * All parameters are public and they are to be dealt with manually.
 * @version 1.0
 *
 */
public class Deme {
	/**
	 * ID of the deme, it's number.
	 */
	public int id;
	/**
	 * Evaluations in a deme.
	 */
	public int evaluations;
	/**
	 * Maximum fitness of a deme.
	 */
	public double maxFitness;
	/**
	 * Minimum fitness of a deme.
	 */
	public double minFitness;
	/**
	 * Average fitness of a deme.
	 */
	public double avgFitness;
	/**
	 * Standard deviation of a fitness of a deme.
	 */
	public double stdevFitness;
	

}
