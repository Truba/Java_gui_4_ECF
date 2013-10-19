package hr.fer.zemris.ecfxml.algorithm;

import hr.fer.zemris.ecfxml.IAlgorithm;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class RouletteWheel implements IAlgorithm {
	
	private double crossoverRate; //<!-- crossover rate -->
	private double selectionPressure; //<!-- selection pressure: how much is the best individual 'better' than the worst -->
	
	public RouletteWheel(double crossoverRate, double selectionPressure) {
		this.crossoverRate = crossoverRate;
		this.selectionPressure = selectionPressure;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<RouletteWheel>\n").
		append(Xmlhalper.entryTag("crxprob", crossoverRate)+"\n").
		append(Xmlhalper.entryTag("selpressure", selectionPressure)+"\n"). 
		append("</RouletteWheel>");
		return sb.toString();
	}
	

}
