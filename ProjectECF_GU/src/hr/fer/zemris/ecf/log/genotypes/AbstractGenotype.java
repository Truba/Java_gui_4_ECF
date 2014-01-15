package hr.fer.zemris.ecf.log.genotypes;

import hr.fer.zemris.ecf.log.Individual;
import hr.fer.zemris.ecf.log.LogFile;

import java.util.ArrayList;

/**
 * This class is a representation of one genotype's solution form one {@link Individual} from one {@link LogFile}.
 * This is an abstract class that is extended by classes that implement specific genotype type.
 * <b>Classes that extend this class has to have the same name as the the name of some genotype and they have to be in <code>hr.fer.zemris.ecf.log.genotypes</code> package.</b>
 * For example: in ECF there is a genotype named BitString and in here there is <code>hr.fer.zemris.ecf.log.genotypes.BitString.java</code> class.
 * @version 1.0
 *
 * @param <T> This parameter depends of the type of the genotype.
 */
public abstract class AbstractGenotype<T> {

	/**
	 * Size of a genotype.
	 */
	public int size;
	/**
	 * All value of a genotype represented by a string.
	 */
	public String value;
	/**
	 * Name of the type of the genotype.
	 */
	public String name;
	
	/**
	 * Constructor, it gets the {@link InitialGenotype} to create this abstract genotype from it.
	 * @param ig initial genotype given
	 */
	public AbstractGenotype(InitialGenotype ig) {
		this.size = ig.size;
		this.value = ig.value;
		this.name = ig.name;
	}
	/**
	 * This method is used to get the values represented how they need to be as the array list of T that is best for representing that genotype.
	 * @return array list of values represented as they need to be for drawing.
	 */
	public abstract ArrayList<T> getValues();
	
}
