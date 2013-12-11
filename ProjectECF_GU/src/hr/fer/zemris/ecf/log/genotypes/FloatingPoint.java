package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public class FloatingPoint extends AbstractGenotype<Double> {

	public FloatingPoint(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Double> getValues() {
		ArrayList<Double> result = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			result.add( Double.parseDouble(e) );
		}
		return result;
	}

}
