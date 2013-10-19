package hr.fer.zemris.ecfxml.genotype;

import hr.fer.zemris.ecfxml.IGenotype;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class Binary implements IGenotype {
	
	private double lbound;
	private double ubound;
	private double precision;
	private double dimension;
	private Double rounding;
	private Double crxOnepoint;
	private Double crxUniform;
	private Double mutSimple;
	private Double mutSimpleBitprob;
	private Double mutMix;
	
	public Binary(double lbound, double ubound, double precision,
			double dimension) {
		this.lbound = lbound;
		this.ubound = ubound;
		this.precision = precision;
		this.dimension = dimension;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Binary>\n");
		sb.append(Xmlhalper.entryTag("lbound", lbound)+"\n");
		sb.append(Xmlhalper.entryTag("ubound", ubound)+"\n");
		sb.append(Xmlhalper.entryTag("precision", precision)+"\n");
		sb.append(Xmlhalper.entryTag("dimension", dimension)+"\n");
		if (rounding != null)
			sb.append(Xmlhalper.entryTag("rounding", rounding)+"\n");
		if (crxOnepoint != null)
			sb.append(Xmlhalper.entryTag("crx.onepoint", crxOnepoint)+"\n");
		if (crxUniform != null)
			sb.append(Xmlhalper.entryTag("crx.uniform", crxUniform)+"\n");
		if (mutSimple != null)
			sb.append(Xmlhalper.entryTag("mut.simple", mutSimple)+"\n");
		if (mutSimpleBitprob != null)
			sb.append(Xmlhalper.entryTag("mut.simple.bitprob", mutSimpleBitprob)+"\n");
		if (mutMix != null)
			sb.append(Xmlhalper.entryTag("mut.mix", mutMix)+"\n");
		sb.append("</Binary>");
		return sb.toString();
	}

	public void setRounding(Double rounding) {
		this.rounding = rounding;
	}

	public void setCrxOnepoint(Double crxOnepoint) {
		this.crxOnepoint = crxOnepoint;
	}

	public void setCrxUniform(Double crxUniform) {
		this.crxUniform = crxUniform;
	}

	public void setMutSimple(Double mutSimple) {
		this.mutSimple = mutSimple;
	}

	public void setMutSimpleBitprob(Double mutSimpleBitprob) {
		this.mutSimpleBitprob = mutSimpleBitprob;
	}

	public void setMutMix(Double mutMix) {
		this.mutMix = mutMix;
	}
	

	
	

}
