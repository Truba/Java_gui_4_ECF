package hr.fer.zemris.ecf.log.reader;

import hr.fer.zemris.ecf.log.Generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used for online reading a.k.a. it is reading while the ECF is still computing and writing to this file.
 * It can gather some of the already computed generations. They are all put in a array list of {@link Generation} called generations.
 * @version 1.0
 *
 */
public class OnlineReading {
	
	private ArrayList<Generation> genarations;
	
	/**
	 * Constructor, it initializes the {@link Generation} list to new array list.
	 */
	public OnlineReading(){	
		genarations = new ArrayList<>();
	}
	
	/**
	 * This is a gather for the {@link Generation} list.
	 * @return array list of generations
	 */
	public ArrayList<Generation> getGenerations(){
		return genarations;
	}
	
	/**
	 * This meted is used to read given log file, it also calls reading meted witch is responsible for calling and parsing log file into {@link Generation}s.
	 * @param file path to the log file
	 */
	public void read(String file) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			System.err.println("Can't find \"log\" file.");
			e.printStackTrace();
		}
		reading(sc);
		sc.close();
		
	}
	
	/**
	 * This meted is used for reading log file and it calls {@link GenerationReading} when it creates predefined lines for {@link GenerationReading} to parse.
	 * This meted also gathers parsed form {@link GenerationReading} and puts them into generations array list.
	 * @param sc scanner for the log file
	 */
	private void reading(Scanner sc) {
		String line = "";
		//Getting to first generation
		while(sc.hasNextLine() && !line.contains("Generation")){
			line = sc.nextLine();
		}
		
		ArrayList<String> gen = new ArrayList<>();
		gen.add(line);
		
		while(sc.hasNextLine()){
			line = sc.nextLine();
			if(line.contains("Generation")){
				genarations.add(GenerationReading.parse(gen));
				gen = new ArrayList<>();
			}
			gen.add(line);
		}
	}

}
