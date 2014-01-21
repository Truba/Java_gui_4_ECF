package hr.fer.zemris.ecf.param;

import java.util.List;

/**
 * This class represents one algorithm written in the parameters file.
 * @version 1.0
 *
 */
public class Algorithm extends EntryBlock {

	/**
	 * Constructor, it gets the algorithm name and it initializes it's entry list to new array list.
	 * @param name algorithm name.
	 */
	public Algorithm (String name) {
		super(name);
	}
	
	/**
	 * Constructor, it gets the algorithm name and it's entry list reference.
	 * @param name algorithm name.
	 * @param entryList pointer to the entry list for this algorithm.
	 */
	public Algorithm (String name, List<Entry> entryList) {
		super(name, entryList);
	}

}
