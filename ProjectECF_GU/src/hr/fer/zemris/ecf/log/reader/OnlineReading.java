package hr.fer.zemris.ecf.log.reader;

import hr.fer.zemris.ecf.log.Generation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineReading {
	
	private ArrayList<Generation> genarations;
	
	public OnlineReading(){	
		genarations = new ArrayList<>();
	}
	
	public ArrayList<Generation> getGenerations(){
		return genarations;
	}
	
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
