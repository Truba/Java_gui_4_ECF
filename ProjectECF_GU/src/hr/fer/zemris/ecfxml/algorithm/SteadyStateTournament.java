package hr.fer.zemris.ecfxml.algorithm;

import hr.fer.zemris.ecfxml.IAlgorithm;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class SteadyStateTournament implements IAlgorithm {
	
	private double tournamentSize; //<!-- tournament size -->

	public SteadyStateTournament(int tournamentSize) {
		this.tournamentSize = tournamentSize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SteadyStateTournament>\n").
		append(Xmlhalper.entryTag("tsize", tournamentSize)+"\n").
		append("</SteadyStateTournament>");
		return sb.toString();
	}
	
	
	
	

}
