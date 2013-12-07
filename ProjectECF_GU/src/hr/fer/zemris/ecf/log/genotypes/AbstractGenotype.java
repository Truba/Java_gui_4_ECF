package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public abstract class AbstractGenotype<T> {

	public int size;
	public String value;
	public String name;
	
	public AbstractGenotype(InitialGenotype ig) {
		this.size = ig.size;
		this.value = ig.value;
		this.name = ig.name;
	}
	
	public abstract ArrayList<T> getValues();
	
}
