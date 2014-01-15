package hr.fer.zemris.ecf.param;

/**
 * This class is the representation of the one entry form the parameters file that can belong to {@link Algorithm} or {@link Genotype} or {@link Registry}.
 * @version 1.0
 *
 */
public class Entry {

	/**
	 * Name of the entry.
	 */
	public String key; //name
	/**
	 * Description of the entry (usually provided by ECF parameters dumping)
	 */
	public String desc; //description
	/**
	 * Value of that entry.
	 */
	public String value;
	
	/**
	 * Constructor.
	 */
	public Entry() {		
	}
	
	/**
	 * Checks if this entry is mandatory.
	 * @return true if it is mandatory, false otherwise
	 */
	public boolean isMandatory() {
	return desc.contains("(mandatory)");
	}
	
	@Override
	public String toString() {
		return key;
	}

}
