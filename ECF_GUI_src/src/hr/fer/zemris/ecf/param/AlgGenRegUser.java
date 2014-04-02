package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the container for the parameters file that will be given to the ECF by the user.
 * @version 1.0
 *
 */
public class AlgGenRegUser {
	
	/**
	 * List of {@link Algorithm} to be written in the algorithm block.
	 */
	public List<Algorithm> algorithm;
	/**
	 * List of list of {@link Genotype} to be written in the multiple genotype blocks.
	 * One list of {@link Genotype} in one genotype block.
	 */
	public List<List<Genotype>> genotypes;
	/**
	 * {@link Registry} to be written into registry block.
	 */
	public Registry registry;
	/**
	 * User comments to be written at the beginning of the parameters file as the xml comment.
	 */
	public String userComment = "";
	
	/**
	 * Constructor it initializes genotypes outer list to new array list.
	 */
	public AlgGenRegUser() {
		algorithm = new ArrayList<>();
		genotypes = new ArrayList<>();
		registry = new Registry();
	}

	/**
	 * Constructor it gets the reference to algorithms, genotypes blocks, genotypes blocks.
	 * @param algorithm given algorithms.
	 * @param genotypes given genotypes blocks.
	 * @param registry given registry.
	 */
	public AlgGenRegUser(List<Algorithm> algorithm, List<List<Genotype>> genotypes,
			Registry registry) {
		this.algorithm = algorithm;
		this.genotypes = genotypes;
		this.registry = registry;
	}
	
	/**
	 * This method is used for retrieving user comment.	 *
	 * @return user comment or text: "There are no user comments for this parameters."
	 */
	public String getUserComment(){
		if (!userComment.isEmpty()){
			return userComment;
		}
		return "There are no user comments for this parameters.";
	}

}
