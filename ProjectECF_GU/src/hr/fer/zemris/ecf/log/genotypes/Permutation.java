package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public class Permutation extends AbstractGenotype<Integer> {

	public Permutation(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Integer> getValues() {
		ArrayList<Integer> resoult = new ArrayList<>();
		String[] list = this.value.trim().split(" +");
		for(String e : list){
			resoult.add( Integer.parseInt(e) );
		}
		return resoult;
	}
	
	

}
