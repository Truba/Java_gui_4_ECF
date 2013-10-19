package hr.fer.zemris.ecfxml.xmlhalpers;

public class Xmlhalper {
	
	public static String entryTag(String key, double value){
		return "<Entry key=\""+key+"\">"+value+"</Entry>";
	}
	public static String entryTag(String key, int value){
		return "<Entry key=\""+key+"\">"+value+"</Entry>";
	}
	public static String entryTag(String key, String value){
		return "<Entry key=\""+key+"\">"+value+"</Entry>";
	}

}
