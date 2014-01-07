package hr.fer.zemris.ecf.log.reader;

import java.util.ArrayList;

import hr.fer.zemris.ecf.log.Deme;
import hr.fer.zemris.ecf.log.Generation;
import hr.fer.zemris.ecf.log.LogFile;
import hr.fer.zemris.ecf.log.Population;

/**
 * This class has only one static meted that is used to parse {@link Generation} from one {@link LogFile}.
 * @version 1.0
 *
 */
public class GenerationReading {
	
	/**
	 * This meted parses trough predefined lines in log file and parses it into a {@link Generation}.
	 * Lines are predefined in this manner:
	 * One string in given array list represents one line from log file.
	 * In first line (array list index 0) there must be generation line example "Generation: 1".
	 * End of the array list is after that generation, so the generation must be complete.
	 * @param data array list of strings containing generation to parse.
	 * @return brand new {@link Generation} class form log file.
	 */
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
				deme.evaluations = Integer.parseInt(data.get(i+1).split("\\:")[1].trim());
				deme.maxFitness = Double.parseDouble(data.get(i+3).split("\\:")[1].trim());
				deme.minFitness = Double.parseDouble(data.get(i+4).split("\\:")[1].trim());
				deme.avgFitness = Double.parseDouble(data.get(i+5).split("\\:")[1].trim());
				deme.stdevFitness = Double.parseDouble(data.get(i+6).split("\\:")[1].trim());
				gen.demes.add(deme);
				deme = new Deme();
				i +=6;
			}
			else if(line.contains("Population")){
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
