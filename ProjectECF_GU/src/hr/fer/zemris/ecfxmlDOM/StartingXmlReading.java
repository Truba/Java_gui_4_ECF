package hr.fer.zemris.ecfxmlDOM;

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


	public static void read(String file) {
		try {
			reading(file);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println("Error ocured while trying to gather initial data given by ECF in xml form.");
			e.printStackTrace();
		}
		
		
	}

	private static void reading(String file) throws SAXException, IOException, ParserConfigurationException {
		File fXmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);		
		doc.getDocumentElement().normalize();
		 
		//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
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
		
		for(int i=0; i<registries.getLength(); i++){
			Node param = registries.item(i);
			
			if (param.getNodeType() == Node.ELEMENT_NODE) {
				System.out.print("Node Atribute =" + ((Element) param).getAttribute("key"));
				System.out.println("   Node Value =" + param.getTextContent());
			}
		}
		
	}

	private static void genotype(Node item) {
		NodeList genotypes = item.getChildNodes();
		
		for(int j=0; j< genotypes.getLength(); j++){
			Node genotype = genotypes.item(j);
			
			if (genotype.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(genotype.getNodeName());
				
				NodeList genotypeParams = genotype.getChildNodes();
				
				for(int i=0; i< genotypeParams.getLength(); i++){
					Node param = genotypeParams.item(i);
					
					if (param.getNodeType() == Node.ELEMENT_NODE) {
						System.out.print("Node Atribute =" + ((Element) param).getAttribute("key"));
						System.out.println("   Node Value =" + param.getTextContent());
					}
				}
			}
		}
	}

	private static void algorithm(Node item) {
		NodeList algorithms = item.getChildNodes();
		
		for(int j=0; j< algorithms.getLength(); j++){		
			Node algorithm = algorithms.item(j);
			
			if (algorithm.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(algorithm.getNodeName());
				
				NodeList algorithmParams = algorithm.getChildNodes();	
				
				for(int i=0; i< algorithmParams.getLength(); i++){
					Node param = algorithmParams.item(i);
					
					if (param.getNodeType() == Node.ELEMENT_NODE) {
						System.out.print("Node Atribute =" + ((Element) param).getAttribute("key"));
						System.out.println("   Node Value =" + param.getTextContent());
					}
				}
			}
		}		
	}
}
