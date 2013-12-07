package hr.fer.zemris.ecf.log.reader;

import hr.fer.zemris.ecf.log.Generation;

import java.util.ArrayList;

public class OfflineReading {
	
	private ArrayList<Generation> genarations;
	
	public OfflineReading(){
		genarations = new ArrayList<>();
	}
	
	public ArrayList<Generation> getGenerations(){
		return genarations;
	}
	
	
	
//parsiranje best of run pocetak	
//	public static Document loadXMLFromString(String xml) throws Exception
//	{
//	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	    DocumentBuilder builder = factory.newDocumentBuilder();
//	    InputSource is = new InputSource(new StringReader(xml));
//	    return builder.parse(is);
//	}

}
