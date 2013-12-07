package hr.fer.zemris.ecf.log.reader;

import hr.fer.zemris.ecf.log.Generation;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		OnlineReading or = new OnlineReading();
		or.read("lib/log.txt");
		ArrayList<Generation> gen = or.getGenerations();
		System.out.println(gen.get(gen.size()-1).demes.size());
	}

}
