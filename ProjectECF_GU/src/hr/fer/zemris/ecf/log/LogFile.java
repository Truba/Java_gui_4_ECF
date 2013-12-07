package hr.fer.zemris.ecf.log;

import java.util.ArrayList;

/**
 * This class is for use in offline reading <b>only</b>.
 * For online reading use ArrayList of {@link Generation}.
 */
public class LogFile {
	
	
	public ArrayList<Generation> generations;
	public ArrayList<Individual> hallOfFame;
	
	public LogFile(){
		this.generations=new ArrayList<>();
		this.hallOfFame=new ArrayList<>();
	}
	
	public LogFile(ArrayList<Generation> generations, ArrayList<Individual> hallOfFame){
		this.generations=generations;
		this.hallOfFame=hallOfFame;
	}

}
