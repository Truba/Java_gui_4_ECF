package hr.fer.zemris.ecf.console;

import hr.fer.zemris.ecf.tasks.Job;

import java.io.IOException;

public class TerminalTalk implements ITalk {

	@Override
	public void write(String path, String command) {
		try {
			//1 Atempt
			//Runtime.getRuntime().exec("/bin/bash -c cd \""+path+"\" & \""+command+"\"");//if doesn't wort try replace & with | or &&
			//2 Atempt
			System.out.println("\"" + path + "/" + command + "\"");
			
			/**
			 * Have no idea what we've done here...
			 * Tried everything but I can't make it work
			 * Also, waitFor is added so Java will definitely wait for process to end
			 * before it starts to execute its code.
			 */
			Process process = new ProcessBuilder("xterm", "-e", "cd \"" + path + "\" | \"" + command + "\"").start(); //if doesn't wort try replace & with | or &&
			process.waitFor();
		} 
		catch (IOException | InterruptedException e) {
			System.err.println("Problem with writing to linux terminal.");
			e.printStackTrace();
		}

	}

	@Override
	public void write(Job job) {
		// TODO Auto-generated method stub
		
	}

	

}
