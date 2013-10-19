package hr.fer.zemris.ecfxml.genotype;

import hr.fer.zemris.ecfxml.IGenotype;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class BitString implements IGenotype {
	
	private double size;
	private Double crxOnepoint = null;
	private Double crxUniform = null;
	private Double mutSimple = null;
	private Double mutSimpleBitprob = null;
	private Double mutMix = null;

	public BitString(double size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BitString>\n");
		sb.append(Xmlhalper.entryTag("size", size)+"\n");
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
		sb.append("</BitString>");
		return sb.toString();
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
