package hr.fer.zemris.cmdTerminal;

import java.io.IOException;

public class CmdTalk implements ITalk{
	
	public void write(String path, String command){
		Runtime rt = Runtime.getRuntime();
		try {
			//MY TESTING
			//rt.exec("cmd.exe /c start cmd");
			//rt.exec(new String[]{"cmd", "/k", "start", "cmd"});
			//rt.exec("cmd.exe /c cd \""+new_dir+"\" & start cmd.exe /k \"java -flag -flag -cp terminal-based-program.jar\"");
			
			//IF CMD IS TO BEE SEEN:
			//rt.exec("cmd.exe /c cd \""+path+"\" & start cmd.exe /k \""+command+"\"");
			
			//IF CMD IS NOT TO BEE SEEN:
			rt.exec("cmd.exe /c cd \""+path+"\" & cmd.exe /k \""+command+"\"");
			
			
		} catch (IOException e) {
			System.err.println("Problem with writing to cmd.");
			e.printStackTrace();
		}
	}

	@Override
	public void write(String partition, String path, String command) {
		Runtime rt = Runtime.getRuntime();
		try {			
			rt.exec("cmd.exe /c "+partition+" & cd \""+path+"\" & cmd.exe /k \""+command+"\"");			
			
		} catch (IOException e) {
			System.err.println("Problem with writing to cmd.");
			e.printStackTrace();
		}
		
	}

}
