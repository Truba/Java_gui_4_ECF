package hr.fer.zemris.ecf.param;

import java.util.List;

/**
 * This class represents one genotype written in the parameters file.
 * @version 1.0
 *
 */
public class Genotype extends EntryBlock {
	
	public Genotype (String name){
		super(name);
	}
	
	/**
	 * Constructor, it gets the genotype name and it's entry list reference.
	 * @param name genotype name.
	 * @param entryList pointer to the entry list for this genotype.
	 */
	public Genotype (String name, List<Entry> entryList){
		super(name, entryList);
	}

}
