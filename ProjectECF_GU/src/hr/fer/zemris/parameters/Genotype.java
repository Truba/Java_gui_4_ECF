package hr.fer.zemris.parameters;

import java.util.ArrayList;
import java.util.List;

public class Genotype {
	
	private String name;
	private List<Entry> entryList;
	
	public Genotype (String name){
		this.name = name;
		entryList = new ArrayList<>();
	}
	
	public Genotype (String name, List<Entry> entryList){
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
