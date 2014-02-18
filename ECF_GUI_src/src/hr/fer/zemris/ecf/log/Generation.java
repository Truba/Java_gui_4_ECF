package hr.fer.zemris.ecf.log;

import java.util.ArrayList;

/**
 * This class  is container for all parameters that are in one generation in one {@link LogFile}.
 * All parameters are public and they are to be dealt with manually, although there are constructors to help.
 * @version 1.0
 *
 */
public class Generation {
	
	/**
	 *  ID of the generation, it's number.
	 */
	public int id;
	/**
	 * Elapsed time of a generation.
	 */
	public int elapsedTime;
	/**
	 * Array list of {@link Deme} belonging to this generation.
	 */
	public ArrayList<Deme> demes;
	/**
	 * {@link Population} belonging to this generation.
	 */
	public Population population;
	
	/**
	 * Constructor, it initializes the {@link Deme} list to new array list.
	 */
	public Generation(){
		demes = new ArrayList<>();
	}
	
	/**
	 * Constructor, it initializes the {@link Deme} list to new array list and it sets id to given id.
	 * @param id Given id.
	 */
	public Generation(int id){
		this.id=id;
		demes = new ArrayList<>();
	}
	

}
