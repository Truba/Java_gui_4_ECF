package hr.fer.zemris.ecf.log;

import java.util.ArrayList;

/**
 * This class is for use in off-line reading <b>only</b>.
 * For online reading use ArrayList of {@link Generation}.
 */
public class LogFile {
	
	
	public ArrayList<Generation> generations;
	public ArrayList<Individual> hallOfFame;
	
	/**
	 * Constructor, it initializes generations and hallOfFame to new array list.
	 */
	public LogFile(){
		this.generations=new ArrayList<>();
		this.hallOfFame=new ArrayList<>();
	}
	
	/**
	 * Constructor, it initializes generations and hallOfFame to given array lists.
	 * @param generations pointer to be added for generations list
	 * @param hallOfFame pointer to be added for hallOfFame list
	 */
	public LogFile(ArrayList<Generation> generations, ArrayList<Individual> hallOfFame){
		this.generations=generations;
		this.hallOfFame=hallOfFame;
	}

}
