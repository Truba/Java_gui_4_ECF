package hr.fer.zemris.ecfxml.algorithm;

import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class DifferentialEvolution {
	
	private double scalingConstant; // <!-- scaling constant -->
	private double crossoverRate; //<!-- crossover rate -->
	
	public DifferentialEvolution(double scalingConstant, double crossoverRate) {
		this.scalingConstant = scalingConstant;
		this.crossoverRate = crossoverRate;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<DifferentialEvolution>\n").
		append(Xmlhalper.entryTag("F", scalingConstant)+"\n").
		append(Xmlhalper.entryTag("CR", crossoverRate)+"\n"). 
		append("</DifferentialEvolution>");
		return sb.toString();
	}
	

}
