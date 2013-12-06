package hr.fer.zemris.ecf.xmldom;

import hr.fer.zemris.ecf.param.AlgGenRegList;
import hr.fer.zemris.ecf.param.Algorithm;
import hr.fer.zemris.ecf.param.Entry;
import hr.fer.zemris.ecf.param.Genotype;
import hr.fer.zemris.ecf.param.Registry;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StartingXmlReading {
	
	private static AlgGenRegList agrList;


	public static AlgGenRegList read(String file) {
		agrList = new AlgGenRegList();
		try {
			reading(file);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println("Error ocured while trying to gather initial data given by ECF in xml form.");
			e.printStackTrace();
		}
		return agrList;
		
	}

	private static void reading(String file) throws SAXException, IOException, ParserConfigurationException {
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
				System.out.println("a");
				algorithm(algGenReg.item(count));
				break;
			case "Genotype":
				System.out.println("g");
				genotype(algGenReg.item(count));
				break;
			case "Registry":
				System.out.println("r");
				registry(algGenReg.item(count));
				break;
			default:
				// No default, for your own good don't write here.
				break;
			}
		} 
		
		
	}

	private static void registry(Node item) {
		NodeList registries = item.getChildNodes();
		agrList.registry = new Registry();
		for(int i=0; i<registries.getLength(); i++){
			Node param = registries.item(i);
			
			if (param.getNodeType() == Node.ELEMENT_NODE) {
				Entry entry = new Entry();
				entry.key = ((Element) param).getAttribute("key");
				entry.desc = ((Element) param).getAttribute("desc");
				entry.value = param.getTextContent();
				agrList.registry.getEntryList().add(entry);
				System.out.print("Node Atribute =" +entry.key +" "+entry.desc);
				System.out.println("   Node Value =" + entry.value);
			}
		}
		
	}

	private static void genotype(Node item) {
		NodeList genotypes = item.getChildNodes();
		
		for(int j=0; j< genotypes.getLength(); j++){
			Node genotype = genotypes.item(j);
			
			if (genotype.getNodeType() == Node.ELEMENT_NODE) {
				Genotype gen = new Genotype(genotype.getNodeName());
				
				System.out.println(gen.getName());
				
				NodeList genotypeParams = genotype.getChildNodes();
				
				for(int i=0; i< genotypeParams.getLength(); i++){
					Node param = genotypeParams.item(i);
					
					if (param.getNodeType() == Node.ELEMENT_NODE) {
						Entry entry = new Entry();
						entry.key = ((Element) param).getAttribute("key");
						entry.desc = ((Element) param).getAttribute("desc");
						entry.value = param.getTextContent();
						gen.getEntryList().add(entry);
						System.out.print("Node Atribute =" + entry.key+" "+entry.desc);
						System.out.println("   Node Value =" + entry.value);
					}
				}
			agrList.genotypes.add(gen);
			}
		}
	}

	private static void algorithm(Node item) {
		NodeList algorithms = item.getChildNodes();
		
		for(int j=0; j< algorithms.getLength(); j++){		
			Node algorithm = algorithms.item(j);
			
			if (algorithm.getNodeType() == Node.ELEMENT_NODE) {
				Algorithm alg = new Algorithm(algorithm.getNodeName());
				System.out.println(alg.getName());
				
				NodeList algorithmParams = algorithm.getChildNodes();	
				
				for(int i=0; i< algorithmParams.getLength(); i++){
					Node param = algorithmParams.item(i);
					
					if (param.getNodeType() == Node.ELEMENT_NODE) {
						Entry entry = new Entry();
						entry.key = ((Element) param).getAttribute("key");
						entry.desc = ((Element) param).getAttribute("desc");
						entry.value = param.getTextContent();
						alg.getEntryList().add(entry);
						System.out.print("Node Atribute =" + entry.key+" "+entry.desc);
						System.out.println("   Node Value =" + entry.value);
					}
				}
				agrList.algorithms.add(alg);
			}
		}		
	}
}
