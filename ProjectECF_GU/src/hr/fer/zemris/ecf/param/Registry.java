package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents regisry block written in the parameters file.
 * @version 1.0
 *
 */
public class Registry {
	
	private List<Entry> entryList;
	
	/**
	 * Constructor, it initializes it's entry list to new array list.
	 */
	public Registry() {
		entryList = new ArrayList<>();
	}
	
	/**
	 * Constructor, it gets it's entry list reference.
	 * @param entryList pointer to the entry list for registry.
	 */
	public Registry(List<Entry> entryList){
		this.entryList = entryList;
	}
	
	/**
	 * Getter for the entry list reference.
	 * @return pointer to the entry list for registry.
	 */
	public List<Entry> getEntryList() {
		return entryList;
	}

	/**
	 * Setter for the entry list reference.
	 * @param entryList pointer to the entry list for registry.
	 */
	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}

}
