package hr.fer.zemris.ecf.xmldom;

import hr.fer.zemris.ecf.param.AlgGenRegUser;
import hr.fer.zemris.ecf.param.AlgGenRegInit;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.Genotype;
import hr.fer.zemris.ecf.param.Registry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class is only used for reading xml parameters files.
 * It uses static methods for it.
 * It has 2 specific static methods: readInitial(String file) for reading initial parameters dumped by ECF and readArchive(String file)
 * for reading parameter files that have been created by the used and stored into some kind of archive.
 * @version 1.0
 *
 */
public class XmlReading {
	
	private static AlgGenRegInit agrList;
	private static AlgGenRegUser agr2list;


	/**
	 * This class is used for parsing initial parameters set given by ECF.	
	 * @param file path to the parameters (.xml) given by ECF.
	 * @return AlgGenRegList class filed with necessary data.
	 */
	public static AlgGenRegInit readInitial(String file) {
		agrList = new AlgGenRegInit();
		try {
			readingInitial(file);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println("Error ocured while trying to gather initial data given by ECF in xml form.");
			e.printStackTrace();
		}
		return agrList;
	}
		
	/**
	 * This class is used for parsing parameters that was user defined earlier and saved within an archive.
	 * @param file path to the parameters (.xml)
	 * @return AlgGenReg4Writing class filled with necessary data.
	 */
	public static AlgGenRegUser readArchive(String file) {
			agr2list = new AlgGenRegUser();
			try {
				readingArchive(file);
			} catch (SAXException | IOException | ParserConfigurationException e) {
				System.err.println("Error ocured while trying to gather initial data given by ECF in xml form.");
				e.printStackTrace();
			}
			return agr2list;
		
	}
	
	/**
	 * This class is used for parsing parameters that was user defined earlier and saved within an archive.
	 * @param file parameters file (.xml)
	 * @return AlgGenReg4Writing class filled with necessary data.
	 */
	public static AlgGenRegUser readArchive(File file) {
		agr2list = new AlgGenRegUser();
		try {
			readingArchive(file);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println("Error ocured while trying to gather initial data given by ECF in xml form.");
			e.printStackTrace();
		}
		return agr2list;
	
}
	
	/**
	 * This class is used for parsing initial parameters set given by ECF.	
	 * @param file path to the parameters given by ECF.
	 * @throws SAXException in case of problem.
	 * @throws IOException in case of problem.
	 * @throws ParserConfigurationException in case of problem.
	 */
	private static void readingInitial(String file) throws SAXException, IOException, ParserConfigurationException {
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);		
		doc.getDocumentElement().normalize();
		NodeList ecf = doc.getChildNodes();
		Node tempNode = ecf.item(0);
		NodeList algGenReg = tempNode.getChildNodes();
		for(int count = 0; count < algGenReg.getLength(); count++){
			switch (algGenReg.item(count).getNodeName()) {
			case "Algorithm":
//				System.out.println("a");
				algorithm(algGenReg.item(count),agrList.algorithms);
				break;
			case "Genotype":
//				System.out.println("g");
				genotype(algGenReg.item(count),agrList.genotypes);
				break;
			case "Registry":
//				System.out.println("r");
				agrList.registry = new Registry();
				registry(algGenReg.item(count),agrList.registry);
				break;
			default:
				// No default, for your own good don't write here.
				break;
			}
		}
	} 
	
	/**
	 * This class is used for parsing parameters that was user defined earlier and saved within an archive.
	 * @param file path to the parameters.
	 * @throws SAXException in case of problem.
	 * @throws IOException in case of problem.
	 * @throws ParserConfigurationException in case of problem.
	 */
	private static void readingArchive(String file) throws SAXException, IOException, ParserConfigurationException {
		File fXmlFile = new File(file);
		readingArchive(fXmlFile);
	}
	
	/**
	 * This class is used for parsing parameters that was user defined earlier and saved within an archive.
	 * @param file parameters file.
	 * @throws SAXException in case of problem.
	 * @throws IOException in case of problem.
	 * @throws ParserConfigurationException in case of problem.
	 */
	private static void readingArchive(File file) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);		
		doc.getDocumentElement().normalize();
		NodeList ecf = doc.getChildNodes();
		
		Node comment = ecf.item(0);
		Node tempNode;
		if(comment instanceof Comment){
			agr2list.userComment = comment.getNodeValue();
			tempNode = ecf.item(1);
		}
		else{
			agr2list.userComment = agr2list.getUserComment();
			tempNode = ecf.item(0);
		}
		
		
		NodeList algGenReg = tempNode.getChildNodes();
		for(int count = 0; count < algGenReg.getLength(); count++){
			switch (algGenReg.item(count).getNodeName()) {
			case "Algorithm":
//				System.out.println("a");
				agr2list.algorithm = new ArrayList<>();
				algorithm(algGenReg.item(count),agr2list.algorithm);
				break;
			case "Genotype":
//				System.out.println("g");
				ArrayList<Genotype> genList = new ArrayList<>(); 
				genotype(algGenReg.item(count),genList);
				agr2list.genotypes.add(genList);
				break;
			case "Registry":
//				System.out.println("r");
				agr2list.registry = new Registry();
				registry(algGenReg.item(count),agr2list.registry);
				break;
			default:
				// No default, for your own good don't write here.
				break;
			}
		}
		
		
	}

	
	/**
	 * This method is used to fill the given {@link Registry} with given {@link Node}.
	 * @param item node containing the registry.
	 * @param registry {@link Registry} class to be filled from node.
	 */
	private static void registry(Node item, Registry registry) {
		NodeList registries = item.getChildNodes();
		
		for(int i=0; i<registries.getLength(); i++){
			Node param = registries.item(i);			
			if (param.getNodeType() == Node.ELEMENT_NODE) {
				Entry entry = new Entry();
				entry.key = ((Element) param).getAttribute("key");
				entry.desc = ((Element) param).getAttribute("desc");
				entry.value = param.getTextContent();
				registry.getEntryList().add(entry);
//				System.out.print("Node Atribute =" +entry.key +" "+entry.desc);
//				System.out.println("   Node Value =" + entry.value);
			}
		}
		
	}

	/**
	 * This method is used to fill the given {@link Genotype} list with given {@link Node}.
	 * @param item node containing the genotype block.
	 * @param genList {@link Genotype} list to be filled from node.
	 */
	private static void genotype(Node item, List<Genotype> genList) {
		NodeList genotypes = item.getChildNodes();
		
		for(int j=0; j< genotypes.getLength(); j++){
			Node genotype = genotypes.item(j);
			
			if (genotype.getNodeType() == Node.ELEMENT_NODE) {
				Genotype gen = new Genotype(genotype.getNodeName());
				
//				System.out.println(gen.getName());
				
				NodeList genotypeParams = genotype.getChildNodes();
				
				for(int i=0; i< genotypeParams.getLength(); i++){
					Node param = genotypeParams.item(i);
					
					if (param.getNodeType() == Node.ELEMENT_NODE) {
						Entry entry = new Entry();
						entry.key = ((Element) param).getAttribute("key");
						entry.desc = ((Element) param).getAttribute("desc");
						entry.value = param.getTextContent();
						gen.getEntryList().add(entry);
//						System.out.print("Node Atribute =" + entry.key+" "+entry.desc);
//						System.out.println("   Node Value =" + entry.value);
					}
				}
			genList.add(gen);
			}
		}
	}

	/**
	 * This method is used to fill the given {@link Algorithm} list with given {@link Node}.
	 * @param item node containing the algorithm block.
	 * @param algorithmsList {@link Algorithm} list to be filled from node.
	 */
	private static void algorithm(Node item, List<Algorithm> algorithmsList) {
		NodeList algorithms = item.getChildNodes();
		
		for(int j=0; j< algorithms.getLength(); j++){		
			Node algorithm = algorithms.item(j);
			
			if (algorithm.getNodeType() == Node.ELEMENT_NODE) {
				Algorithm alg = new Algorithm(algorithm.getNodeName());
//				System.out.println(alg.getName());
				
				NodeList algorithmParams = algorithm.getChildNodes();	
				
				for(int i=0; i< algorithmParams.getLength(); i++){
					Node param = algorithmParams.item(i);
					
					if (param.getNodeType() == Node.ELEMENT_NODE) {
						Entry entry = new Entry();
						entry.key = ((Element) param).getAttribute("key");
						entry.desc = ((Element) param).getAttribute("desc");
						entry.value = param.getTextContent();
						alg.getEntryList().add(entry);
//						System.out.print("Node Atribute =" + entry.key+" "+entry.desc);
//						System.out.println("   Node Value =" + entry.value);
					}
				}
				algorithmsList.add(alg);
			}
		}		
	}
}
