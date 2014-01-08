package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents one genotype written in the parameters file.
 * @version 1.0
 *
 */
public class Genotype {
	
	private String name;
	private List<Entry> entryList;
	
	public Genotype (String name){
		this.name = name;
		entryList = new ArrayList<>();
	}
	
	/**
	 * Constructor, it gets the genotype name and it's entry list reference.
	 * @param name genotype name.
	 * @param entryList pointer to the entry list for this genotype.
	 */
	public Genotype (String name, List<Entry> entryList){
		this.name = name;
		this.entryList = entryList;
	}

	/**
	 * Getter for the genotype name.
	 * @return genotype name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the genotype name.
	 * @param name genotype name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the entry list reference.
	 * @return pointer to the entry list for this genotype.
	 */
	public List<Entry> getEntryList() {
		return entryList;
	}

	/**
	 * Setter for the entry list reference.
	 * @param entryList pointer to the entry list for this genotype.
	 */
	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}

}
