package hr.fer.zemris.ecfxmlDOM;

import hr.fer.zemris.parameters.AlgGenReg4Writing;
import hr.fer.zemris.parameters.Entry;
import hr.fer.zemris.parameters.Genotype;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ParamsXmlWriting {
	
	private static AlgGenReg4Writing agrw;

	public static void write(String file, AlgGenReg4Writing agrwGet) {
		agrw = agrwGet;
		try {
			writing(file);
		} catch (ParserConfigurationException | TransformerException e) {
			System.err.println("Error ocured while trying to create a paramethers xml for ECF.");
			e.printStackTrace();
		}
	}

	private static void writing(String file) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("ECF");
		doc.appendChild(rootElement);
		
		Element algorithm = doc.createElement("Algorithm");
		algorithm(algorithm,doc);
		rootElement.appendChild(algorithm);
				
		Element genotypes = doc.createElement("Genotype");
		genotype(genotypes,doc);
		rootElement.appendChild(genotypes);
				
		Element registry = doc.createElement("Registry");
		registry(registry,doc);
		rootElement.appendChild(registry);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(file));
		//StreamResult result = new StreamResult(System.out);   For testing.
		transformer.transform(source, result);
		
	}

	private static void registry(Element registry, Document doc) {
		List<Entry> eList = agrw.registry.getEntryList();
		for(int i=0; i<eList.size(); i++){
			Entry e = eList.get(i);
			Element entry = doc.createElement("Entry");
			entry.setAttribute("key", e.key);
			entry.appendChild(doc.createTextNode(e.value));
			registry.appendChild(entry);
			
		}
	}

	private static void genotype(Element genotypes, Document doc) {
		List<Genotype> gList = agrw.genotypes;
		for(Genotype genotype : gList) {
			Element genType = doc.createElement(genotype.getName());
			
			List<Entry> eList = genotype.getEntryList();
			for(int i=0; i<eList.size(); i++){
				Entry e = eList.get(i);
				Element entry = doc.createElement("Entry");
				entry.setAttribute("key", e.key);
				entry.appendChild(doc.createTextNode(e.value));
				genType.appendChild(entry);
				
			}
			
			genotypes.appendChild(genType);
		}
		
	}

	private static void algorithm(Element algorithm, Document doc) {
		Element algType = doc.createElement(agrw.algorithm.getName());
		List<Entry> eList = agrw.algorithm.getEntryList();
		for(int i=0; i<eList.size(); i++){
			Entry e = eList.get(i);
			Element entry = doc.createElement("Entry");
			entry.setAttribute("key", e.key);
			entry.appendChild(doc.createTextNode(e.value));
			algType.appendChild(entry);
			
		}
		
		algorithm.appendChild(algType);
		
	}	

}
