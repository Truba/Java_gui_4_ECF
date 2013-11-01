package hr.fer.zemris.ecfxmlDOM;

import hr.fer.zemris.parameters.AlgGenReg4Writing;
import hr.fer.zemris.parameters.AlgGenRegList;

public class XmlReadTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//reading
		AlgGenRegList sTANKOVIC_NAPRAVI_NESTO_S_OVIM =StartingXmlReading.read("lib/parameters.txt");
		
		//creating xml and writing in it
		AlgGenRegList agr = sTANKOVIC_NAPRAVI_NESTO_S_OVIM;
		AlgGenReg4Writing agrw = new AlgGenReg4Writing(agr.algorithms.get(1), agr.genotypes, agr.registry);
		ParamsXmlWriting.write("lib/out.xml",agrw);

	}

}
