package hr.fer.zemris.ecf.log.reader;

import java.util.ArrayList;

import hr.fer.zemris.ecf.log.Deme;
import hr.fer.zemris.ecf.log.Generation;
import hr.fer.zemris.ecf.log.Population;

public class GenerationReading {
	
	public static Generation parse(ArrayList<String> data){
		Generation gen = new Generation();
		gen.id = Integer.parseInt(data.get(0).split("\\:")[1].trim());
		gen.elapsedTime = Integer.parseInt(data.get(1).split("\\:")[1].trim());
		
		if(!data.get(2).contains("Deme")) System.err.println("Deme not found");
		
		Deme deme = new Deme();
		for(int i=2; i<data.size(); i++){
			String line = data.get(i).trim();
			if(line.isEmpty()){
				continue;
			}
			else if(line.contains("Deme")){
				deme.id = Integer.parseInt(line.split("\\:")[1].trim());
			}
			else if(line.contains("Evaluations")){
				deme.evaluations = Integer.parseInt(line.split("\\:")[1].trim());
			}
			else if(line.contains("max")){
				deme.maxFitness = Double.parseDouble(line.split("\\:")[1].trim());
			}
			else if(line.contains("min")){
				deme.minFitness = Double.parseDouble(line.split("\\:")[1].trim());
			}
			else if(line.contains("avg")){
				deme.avgFitness = Double.parseDouble(line.split("\\:")[1].trim());
			}
			else if(line.contains("stdev")){
				deme.stdevFitness = Double.parseDouble(line.split("\\:")[1].trim());
				gen.demes.add(deme);
				deme = new Deme();
			}
			else if(line.contains("Population")){
				if(data.size()<=i+6){
					break;
				}
				gen.population = new Population();
				gen.population.evaluations = Integer.parseInt(data.get(i+1).split("\\:")[1].trim());
				gen.population.maxFitness = Double.parseDouble(data.get(i+3).split("\\:")[1].trim());
				gen.population.minFitness = Double.parseDouble(data.get(i+4).split("\\:")[1].trim());
				gen.population.avgFitness = Double.parseDouble(data.get(i+5).split("\\:")[1].trim());
				gen.population.stdevFitness = Double.parseDouble(data.get(i+6).split("\\:")[1].trim());
				break;
			}
		}
		return gen;
	}
}
