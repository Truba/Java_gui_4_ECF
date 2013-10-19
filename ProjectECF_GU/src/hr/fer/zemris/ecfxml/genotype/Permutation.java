package hr.fer.zemris.ecfxml.genotype;

import hr.fer.zemris.ecfxml.IGenotype;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class Permutation implements IGenotype {
	
	private double size;
	private Double crxOX;
	private Double crxPBX;
	private Double crxPMX;
	private Double mutToggle; 
	private Double mutInv;
	private Double mutIns;
	
	
	public Permutation(double size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Permutation>\n");
		sb.append(Xmlhalper.entryTag("size", size)+"\n");
		if (crxOX != null)
			sb.append(Xmlhalper.entryTag("crx.OX", crxOX)+"\n");
		if (crxPBX != null)
			sb.append(Xmlhalper.entryTag("crx.PBX", crxPBX)+"\n");
		if (crxPMX != null)
			sb.append(Xmlhalper.entryTag("crx.PMX", crxPMX)+"\n");
		if (mutToggle != null)
			sb.append(Xmlhalper.entryTag("mut.toggle", mutToggle)+"\n");
		if (mutInv != null)
			sb.append(Xmlhalper.entryTag("mut.inv", mutInv)+"\n");
		if (mutIns != null)
			sb.append(Xmlhalper.entryTag("mut.ins", mutIns)+"\n");
		sb.append("</Permutation>");
		return sb.toString();
	}

	public void setCrxOX(Double crxOX) {
		this.crxOX = crxOX;
	}

	public void setCrxPBX(Double crxPBX) {
		this.crxPBX = crxPBX;
	}

	public void setCrxPMX(Double crxPMX) {
		this.crxPMX = crxPMX;
	}

	public void setMutToggle(Double mutToggle) {
		this.mutToggle = mutToggle;
	}

	public void setMutInv(Double mutInv) {
		this.mutInv = mutInv;
	}

	public void setMutIns(Double mutIns) {
		this.mutIns = mutIns;
	}
	
	
	
	

}
