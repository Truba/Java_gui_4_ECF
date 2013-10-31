package hr.fer.zemris.parameters;

import java.util.ArrayList;
import java.util.List;

public class Registry {
	
	private List<Entry> entryList;
	
	public Registry() {
		entryList = new ArrayList<>();
	}
	
	public Registry(List<Entry> entryList){
		this.entryList = entryList;
	}
	
	public List<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}

}
