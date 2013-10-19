package hr.fer.zemris.ecfxml.genotype;

import hr.fer.zemris.ecfxml.IGenotype;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class Tree implements IGenotype {
	
	private double maxdepth;
	private double mindepth;
	private String functionset;
	private String terminalset;
	private Double initmaxdepth;
	private Double initmindepth;
	private Double crxSimple;
	private Double crxSimpleFunctionprob;
	private Double crxContext;
	private Double crxSizefair;
	private Double crxOnepoint;
	private Double crxUniform;
	private Double mutSubtree;
	private Double mutPermutation;
	private Double mutGauss;
	private Double mutHoist;
	private Double mutNodecomplement;
	private Double mutNodereplac;
	private Double mutShrink;
	
	public Tree(double maxdepth, double mindepth, String functionset,
			String terminalset) {
		super();
		this.maxdepth = maxdepth;
		this.mindepth = mindepth;
		this.functionset = functionset;
		this.terminalset = terminalset;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Tree>\n");
		sb.append(Xmlhalper.entryTag("maxdepth", maxdepth)+"\n");
		sb.append(Xmlhalper.entryTag("mindepth", mindepth)+"\n");
		sb.append(Xmlhalper.entryTag("functionset", functionset)+"\n");
		sb.append(Xmlhalper.entryTag("terminalset", terminalset)+"\n");
		if (initmaxdepth != null)
			sb.append(Xmlhalper.entryTag("initmaxdepth", initmaxdepth)+"\n");
		if (initmindepth != null)
			sb.append(Xmlhalper.entryTag("initmindepth", initmindepth)+"\n");
		if (crxSimple != null)
			sb.append(Xmlhalper.entryTag("crx.simple", crxSimple)+"\n");
		if (crxSimpleFunctionprob != null)
			sb.append(Xmlhalper.entryTag("crx.simple.functionprob", crxSimpleFunctionprob)+"\n");
		if (crxContext != null)
			sb.append(Xmlhalper.entryTag("crx.context", crxContext)+"\n");
		if (crxSizefair != null)
			sb.append(Xmlhalper.entryTag("crx.sizefair", crxSizefair)+"\n");
		if (crxOnepoint != null)
			sb.append(Xmlhalper.entryTag("crx.onepoint", crxOnepoint)+"\n");
		if (crxUniform != null)
			sb.append(Xmlhalper.entryTag("crx.uniform", crxUniform)+"\n");
		if (mutSubtree != null)
			sb.append(Xmlhalper.entryTag("mut.subtree", mutSubtree)+"\n");
		if (mutPermutation != null)
			sb.append(Xmlhalper.entryTag("mut.permutation", mutPermutation)+"\n");
		if (mutGauss != null)
			sb.append(Xmlhalper.entryTag("mut.gauss", mutGauss)+"\n");
		if (mutHoist != null)
			sb.append(Xmlhalper.entryTag("mut.hoist", mutHoist)+"\n");
		if (mutNodecomplement != null)
			sb.append(Xmlhalper.entryTag("mut.nodecomplement", mutNodecomplement)+"\n");
		if (mutNodereplac != null)
			sb.append(Xmlhalper.entryTag("mut.nodereplace", mutNodereplac)+"\n");
		if (mutShrink != null)
			sb.append(Xmlhalper.entryTag("mut.shrink", mutShrink)+"\n");
		sb.append("</Tree>");
		return sb.toString();
		
	}

	public void setInitmaxdepth(Double initmaxdepth) {
		this.initmaxdepth = initmaxdepth;
	}

	public void setInitmindepth(Double initmindepth) {
		this.initmindepth = initmindepth;
	}

	public void setCrxSimple(Double crxSimple) {
		this.crxSimple = crxSimple;
	}

	public void setCrxSimpleFunctionprob(Double crxSimpleFunctionprob) {
		this.crxSimpleFunctionprob = crxSimpleFunctionprob;
	}

	public void setCrxContext(Double crxContext) {
		this.crxContext = crxContext;
	}

	public void setCrxSizefair(Double crxSizefair) {
		this.crxSizefair = crxSizefair;
	}

	public void setCrxOnepoint(Double crxOnepoint) {
		this.crxOnepoint = crxOnepoint;
	}

	public void setCrxUniform(Double crxUniform) {
		this.crxUniform = crxUniform;
	}

	public void setMutSubtree(Double mutSubtree) {
		this.mutSubtree = mutSubtree;
	}

	public void setMutPermutation(Double mutPermutation) {
		this.mutPermutation = mutPermutation;
	}

	public void setMutGauss(Double mutGauss) {
		this.mutGauss = mutGauss;
	}

	public void setMutHoist(Double mutHoist) {
		this.mutHoist = mutHoist;
	}

	public void setMutNodecomplement(Double mutNodecomplement) {
		this.mutNodecomplement = mutNodecomplement;
	}

	public void setMutNodereplac(Double mutNodereplac) {
		this.mutNodereplac = mutNodereplac;
	}

	public void setMutShrink(Double mutShrink) {
		this.mutShrink = mutShrink;
	}
	
	


}




