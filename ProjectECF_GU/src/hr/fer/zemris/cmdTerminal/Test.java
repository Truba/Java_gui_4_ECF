package hr.fer.zemris.cmdTerminal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		DetectOS os = new DetectOS();
		int osName = os.getOS();
		System.out.println(osName);
		
		ITalk cmd = null;
		if(osName == DetectOS.WINDOWS){
			cmd = new CmdTalk();
		}
		if(osName == DetectOS.LINUX){
			cmd = new TerminalTalk();
		}
		cmd.write("lib","c.exe>in.txt");
		File f = new File("lib/in.txt");
		if(f.exists()) {
			Scanner s = new Scanner(f);
			System.out.println(s.hasNextLine() ? s.nextLine() : "Fatal error. exe did not write anything to file.");
		}
		//cmd.write("C:", "C:/Users/Vlaho/Desktop/mythsim-3.1.1", "java -jar mythsim-3.1.1.jar");
		
	}

}
