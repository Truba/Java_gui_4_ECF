package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public class Binary extends AbstractGenotype<Double> {

	public Binary(InitialGenotype ig) {
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
