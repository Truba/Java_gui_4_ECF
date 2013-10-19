package hr.fer.zemris.ecfxml;

import java.security.InvalidParameterException;
import java.util.List;



public class EcfXmlBuilder {
	
	private IAlgorithm algorithm;
	private List<IGenotype> genotypList;
	private List<IRegistry> registryList;
	
	
	public EcfXmlBuilder(IAlgorithm algorithm, List<IGenotype> genotypList, List<IRegistry> registryList) {
		if (genotypList == null || genotypList.get(0) == null){
			throw new InvalidParameterException("No genotyp found");
		}
		this.algorithm = algorithm;
		this.genotypList = genotypList;
		this.registryList = registryList;
		
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ECF>\n");
		if(algorithm != null){
			addAlgorithm(sb);
		}
		addGenotypList(sb);
		if(registryList != null){
			addRegistryList(sb);
		}
		sb.append("</ECF>");
		
		return sb.toString();
	}
	
	private void addRegistryList(StringBuilder sb) {
		sb.append("<Registry>\n");
		for (IRegistry registry : registryList){
			sb.append(registry.toString());
		}		
		sb.append("</Registry>\n");
		
	}
	
	private void addGenotypList(StringBuilder sb) {
		sb.append("<Genotype>\n");
		for (IGenotype genityp : genotypList){
			sb.append(genityp.toString()+"\n");
		}		
		sb.append("</Genotype>\n");
		
	}
	
	private void addAlgorithm(StringBuilder sb) {
		sb.append("<Algorithm>\n");
		sb.append(algorithm.toString()+"\n");
		sb.append("</Algorithm>\n");
		
	}
	

}
