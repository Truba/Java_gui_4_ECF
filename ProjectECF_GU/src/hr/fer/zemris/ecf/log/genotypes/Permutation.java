package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public class Permutation extends AbstractGenotype<Integer> {

	public Permutation(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Integer> getValues() {
		ArrayList<Integer> result = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			result.add( Integer.parseInt(e) );
		}
		return result;
	}
	
	

}
