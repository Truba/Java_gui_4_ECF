package hr.fer.zemris.cmdTerminal;

import java.io.IOException;

public class TerminalTalk implements ITalk {

	@Override
	public void write(String path, String command) {
		try {
			//1 Atempt
			Runtime.getRuntime().exec("/bin/bash -c cd \""+path+"\" & \""+command+"\"");//if doesn't wort try replace & with | or &&
			//2 Atempt
			Process proc = new ProcessBuilder("xterm", "-c", "cd \""+path+"\" & \""+command+"\"").start(); //if doesn't wort try replace & with | or &&
		} 
		catch (IOException e) {
			System.err.println("Problem with writing to linux terminal.");
			e.printStackTrace();
		}

	}

}
