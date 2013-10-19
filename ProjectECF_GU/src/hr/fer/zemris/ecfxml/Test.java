package hr.fer.zemris.ecfxml;

import java.awt.Point;
import java.util.ArrayList;
import java.util.BitSet;

import hr.fer.zemris.ecfxml.algorithm.SteadyStateTournament;
import hr.fer.zemris.ecfxml.genotype.Binary;
import hr.fer.zemris.ecfxml.genotype.BitString;
import hr.fer.zemris.ecfxml.registry.Population;

public class Test {
	
	public static void main(String[] args) {
		
		IAlgorithm agl = new SteadyStateTournament(4);
		
		ArrayList<IGenotype> genList = new ArrayList<>();
		genList.add(new Binary(1, 2, 2, 2));
		genList.add(new BitString(4));
		
		ArrayList<IRegistry> regList = new ArrayList<>();
		Population population = new Population();
		population.setPopulationSize(30);
		regList.add(population);
		
		EcfXmlBuilder exb = new EcfXmlBuilder(agl, genList, regList);
		
		System.out.println(exb.toString());
		
	}

}
