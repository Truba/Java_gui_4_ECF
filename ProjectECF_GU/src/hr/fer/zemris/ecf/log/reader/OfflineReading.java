package hr.fer.zemris.ecf.log.reader;

import hr.fer.zemris.ecf.log.Generation;
import hr.fer.zemris.ecf.log.Individual;
import hr.fer.zemris.ecf.log.LogFile;
import hr.fer.zemris.ecf.log.genotypes.GenotypeReader;
import hr.fer.zemris.ecf.log.genotypes.InitialGenotype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class OfflineReading {
	
	private ArrayList<Generation> genarations;
	private ArrayList<Individual> hallOfFame;
	
	public OfflineReading(){
		genarations = new ArrayList<>();
		hallOfFame = new ArrayList<>();
	}
	
	public LogFile getLogFile(){
		return new LogFile(genarations,hallOfFame);
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
		createHallOfFame(sc);
		sc.close();
		
	}

	private void createHallOfFame(Scanner sc) {
		StringBuilder sb = new StringBuilder();
		while(sc.hasNextLine()){
			sb.append(sc.nextLine());			
		}
		try {
			parseHallOfFame(sb.toString());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Error ocured while trying to read HallOfFame from log file.");
			e.printStackTrace();
		}
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
			if(line.contains("Best of run")){
				genarations.add(GenerationReading.parse(gen));
				gen = new ArrayList<>();
				break;
			}
			gen.add(line);
		}
		
	}

	private void parseHallOfFame(String xml) throws ParserConfigurationException, SAXException, IOException {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    Document doc =  builder.parse(is);
	    NodeList hofParent = doc.getChildNodes();
	    Node hof = hofParent.item(0);
	    
		//((Element) hof).getAttribute("size"); This is size
//		System.out.println("Hof  =" +((Element) hof).getAttribute("size"));
	    
		NodeList individualList = hof.getChildNodes();
		
		for(int i=0; i<individualList.getLength(); i++){
			Node indiv = individualList.item(i);
			Individual ind = new Individual();
			if (indiv.getNodeType() == Node.ELEMENT_NODE) {
				ind.size = Integer.parseInt(((Element) indiv).getAttribute("size").trim());
				ind.gen = Integer.parseInt(((Element) indiv).getAttribute("gen").trim());
//				System.out.println("size + gen =" +ind.size +" + "+ind.gen);
				fillIndividulal(indiv,ind);
				hallOfFame.add(ind);
			}
		}
		
	}

	private void fillIndividulal(Node indiv, Individual ind) {
		NodeList paramsList = indiv.getChildNodes();
		
		for(int i=0; i<paramsList.getLength(); i++){
			Node param = paramsList.item(i);			
			if (param.getNodeType() == Node.ELEMENT_NODE) {
				
				if(param.getNodeName().equals("FitnessMax")){
					ind.fitnessMax = Double.parseDouble(((Element) param).getAttribute("value").trim());
//					System.out.println("fit max " +ind.fitnessMax);
					continue;
				}
				
				InitialGenotype gen = new InitialGenotype();
				gen.size = Integer.parseInt(((Element) param).getAttribute("size").trim());
				gen.name = param.getNodeName();
				gen.value = param.getTextContent();
				ind.genotypes.add(GenotypeReader.getGenotype(gen));
//				System.out.println("Name + size =" +gen.name +" "+gen.size);
//				System.out.println("   Gen Value =" + gen.value);
			}
		}
		
	}

}
