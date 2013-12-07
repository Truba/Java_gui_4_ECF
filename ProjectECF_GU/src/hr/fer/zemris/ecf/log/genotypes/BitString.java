package hr.fer.zemris.ecf.log.genotypes;

import java.util.ArrayList;

public class BitString extends AbstractGenotype<Integer> {

	public BitString(InitialGenotype ig) {
		super(ig);
	}

	@Override
	public ArrayList<Integer> getValues() {
		ArrayList<Integer> result = new ArrayList<>();
		for(int i=0; i<this.value.length(); i++){
			result.add( (this.value.charAt(i)=='1') ? 1 : 0 );
		}
		return result;
	}

}
