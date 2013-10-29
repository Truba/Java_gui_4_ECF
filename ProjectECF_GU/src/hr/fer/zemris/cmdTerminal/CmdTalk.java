package hr.fer.zemris.cmdTerminal;

import java.io.IOException;

public class CmdTalk {
	
	public static void write(String path, String command){
		Runtime rt = Runtime.getRuntime();
		try {
			//rt.exec("cmd.exe /c start cmd");
			//rt.exec(new String[]{"cmd", "/k", "start", "cmd"});
			//rt.exec("cmd.exe /c cd \""+new_dir+"\" & start cmd.exe /k \"java -flag -flag -cp terminal-based-program.jar\"");
			rt.exec("cmd.exe /c cd \""+path+"\" & start cmd.exe /k \""+command+"\"");
		} catch (IOException e) {
			System.err.println("Problem with writing to cmd.");
			e.printStackTrace();
		}
	}

}
