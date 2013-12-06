package hr.fer.zemris.ecf.xmldom;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.ecf.param.AlgGenReg4Writing;
import hr.fer.zemris.ecf.param.AlgGenRegList;
import hr.fer.zemris.ecf.param.Genotype;

public class XmlReadTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//reading
		AlgGenRegList sTANKOVIC_NAPRAVI_NESTO_S_OVIM =XmlReading.readInitial("lib/parameters.xml");
		
		//creating xml and writing in it
		AlgGenRegList agr = sTANKOVIC_NAPRAVI_NESTO_S_OVIM;
		
//		AlgGenReg4Writing sTANKOVIC_OVO_MI_TREBAS_STVORIT_SA_PARAMETRIMA_KOJE_KORISNIK_IZABERE
//		= new AlgGenReg4Writing(agr.algorithms, createGeotypesForTesting(agr.genotypes), agr.registry);
		AlgGenReg4Writing s = XmlReading.readArchive("lib/out.xml");
		XmlWriting.write("lib/out.xml",s);

	}

	private static List<List<Genotype>> createGeotypesForTesting(List<Genotype> genotypes) {
		List<List<Genotype>> genSomething = new ArrayList<>();
		genSomething.add(genotypes);
		genSomething.add(genotypes);
		return genSomething;
	}

}
