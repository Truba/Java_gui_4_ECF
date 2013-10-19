package hr.fer.zemris.ecfxml.algorithm;

import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class Elimination {
	
	private double generationGap; //<!-- generation gap (percentage of population to be eliminated) -->
	private double selectionPressure; //<!-- selection pressure: how much is the worst individual 'worse' than the best -->
	
	
	public Elimination(double generationGap, double selectionPressure) {
		this.generationGap = generationGap;
		this.selectionPressure = selectionPressure;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Elimination>\n").
		append(Xmlhalper.entryTag("gengap", generationGap)+"\n").
		append(Xmlhalper.entryTag("selpressure", selectionPressure)+"\n"). 
		append("</Elimination>");
		return sb.toString();
	}
	

}
