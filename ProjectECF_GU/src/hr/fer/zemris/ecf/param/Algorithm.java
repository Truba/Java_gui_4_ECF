package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents one algorithm written in the parameters file.
 * @version 1.0
 *
 */
public class Algorithm {

	private String name;
	private List<Entry> entryList;
	
	/**
	 * Constructor, it gets the algorithm name and it initializes it's entry list to new array list.
	 * @param name algorithm name.
	 */
	public Algorithm (String name){
		this.name = name;
		entryList = new ArrayList<>();
	}
	
	/**
	 * Constructor, it gets the algorithm name and it's entry list reference.
	 * @param name algorithm name.
	 * @param entryList pointer to the entry list for this algorithm.
	 */
	public Algorithm (String name, List<Entry> entryList){
		this.name = name;
		this.entryList = entryList;
	}

	/**
	 * Getter for the algorithm name.
	 * @return algorithm name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the algorithm name.
	 * @param name algorithm name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the entry list reference.
	 * @return pointer to the entry list for this algorithm.
	 */
	public List<Entry> getEntryList() {
		return entryList;
	}
	
	/**
	 * Setter for the entry list reference.
	 * @param entryList pointer to the entry list for this algorithm.
	 */
	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
