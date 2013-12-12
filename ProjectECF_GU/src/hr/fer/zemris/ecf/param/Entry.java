package hr.fer.zemris.ecf.param;

public class Entry {

	public String key; //name
	public String desc; //description
	public String value;
	
	public Entry() {		
	}
	
	public boolean isMandetory() {
	return desc.contains("(mandatory)");
	}

}
