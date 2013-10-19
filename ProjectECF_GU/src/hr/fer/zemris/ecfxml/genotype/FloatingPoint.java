package hr.fer.zemris.ecfxml.genotype;

import hr.fer.zemris.ecfxml.IGenotype;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class FloatingPoint implements IGenotype {
	
	private double lbound;
	private double ubound;
	private double dimension;
	private Double mutSimple;
	private Double crxOnepoint;
	private Double crxArithmetic;
	
	
	public FloatingPoint(double lbound, double ubound, double dimension) {
		this.lbound = lbound;
		this.ubound = ubound;
		this.dimension = dimension;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<FloatingPoint>\n");
		sb.append(Xmlhalper.entryTag("lbound", lbound)+"\n");
		sb.append(Xmlhalper.entryTag("ubound", ubound)+"\n");
		sb.append(Xmlhalper.entryTag("dimension", dimension)+"\n");
		if (mutSimple != null)
			sb.append(Xmlhalper.entryTag("mut.simple", mutSimple)+"\n");
		if (crxOnepoint != null)
			sb.append(Xmlhalper.entryTag("crx.onepoint", crxOnepoint)+"\n");
		if (crxArithmetic != null)
			sb.append(Xmlhalper.entryTag("mut.simple.bitprob", crxArithmetic)+"\n");
		sb.append("</FloatingPoint>");
		return sb.toString();
	}

	public void setMutSimple(Double mutSimple) {
		this.mutSimple = mutSimple;
	}

	public void setCrxOnepoint(Double crxOnepoint) {
		this.crxOnepoint = crxOnepoint;
	}

	public void setCrxArithmetic(Double crxArithmetic) {
		this.crxArithmetic = crxArithmetic;
	}
	
	
	
	

}
