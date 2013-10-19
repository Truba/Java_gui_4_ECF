package hr.fer.zemris.ecfxml.registry;

import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class TerminationConditions {
	
	private Double termStagnation;
	private Double termMaxgen;
	private Double termMaxtime;
	private Double termFitnessval;
	private Double termEval;
	
	public TerminationConditions() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (termStagnation != null)
			sb.append(Xmlhalper.entryTag("term.stagnation", termStagnation)+"\n");
		if (termMaxgen != null)
			sb.append(Xmlhalper.entryTag("term.maxgen", termMaxgen)+"\n");
		if (termMaxtime != null)
			sb.append(Xmlhalper.entryTag("term.maxtime", termMaxtime)+"\n");
		if (termFitnessval != null)
			sb.append(Xmlhalper.entryTag("term.fitnessval", termFitnessval)+"\n");
		if (termEval != null)
			sb.append(Xmlhalper.entryTag("term.eval", termEval)+"\n");
		
		return sb.toString();
	}

	public void setTermStagnation(Double termStagnation) {
		this.termStagnation = termStagnation;
	}

	public void setTermMaxgen(Double termMaxgen) {
		this.termMaxgen = termMaxgen;
	}

	public void setTermMaxtime(Double termMaxtime) {
		this.termMaxtime = termMaxtime;
	}

	public void setTermFitnessval(Double termFitnessval) {
		this.termFitnessval = termFitnessval;
	}

	public void setTermEval(Double termEval) {
		this.termEval = termEval;
	}
	
	

}
