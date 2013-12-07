package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public class FloatingPoint extends AbstractGenotype<Double> {

	public FloatingPoint(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Double> getValues() {
		ArrayList<Double> resoult = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			resoult.add( Double.parseDouble(e) );
		}
		return resoult;
	}

}
