package hr.fer.zemris.ecf.param;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

	private String name;
	private List<Entry> entryList;
	
	public Algorithm (String name){
		this.name = name;
		entryList = new ArrayList<>();
	}
	
	public Algorithm (String name, List<Entry> entryList){
		this.name = name;
		this.entryList = entryList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}
	
	
	
	
}
