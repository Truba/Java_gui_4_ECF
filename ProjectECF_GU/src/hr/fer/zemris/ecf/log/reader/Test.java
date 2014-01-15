package hr.fer.zemris.ecf.log.reader;

import hr.fer.zemris.ecf.log.Generation;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		//OnlineReading or = new OnlineReading();
		OfflineReading off = new OfflineReading();
		off.read("lib/log0.txt");
		ArrayList<Generation> gen = off.getLogFile().generations;
		System.out.println(gen.get(gen.size()-1).population.avgFitness);
		
		//STUDY CAREFOULY THE NEXT LINE :)
		List<Integer> li = off.getLogFile().hallOfFame.get(0).genotypes.get(0).getValues();
		
		for(int i : li){
			System.out.print(i+" ");
		}
	}

}
