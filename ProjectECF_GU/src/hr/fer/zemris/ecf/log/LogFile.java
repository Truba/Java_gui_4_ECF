package hr.fer.zemris.ecf.log;

import java.util.ArrayList;

/**
 * This class is for use in offline reading <b>only</b>.
 * For online reading use ArrayList of {@link Generation}.
 */
public class LogFile {
	
	
	public ArrayList<Generation> generations;
	public String best;
	
	public LogFile(){
		generations = new ArrayList<>();
	}

}
