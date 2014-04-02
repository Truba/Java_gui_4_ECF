package hr.fer.zemris.ecf.console;

import java.io.IOException;

/**
 * This class is the Window's cmd implementation of {@link ITalk}.
 * @version 1.0
 *
 */
public class CmdTalk implements ITalk{
	
	@Override
	public void write(String ecfPath, String paramsPath){
		
		try {			
			//MY TESTING
			//Runtime rt = Runtime.getRuntime();
			
			//rt.exec("cmd.exe /c start cmd");
			//rt.exec(new String[]{"cmd", "/k", "start", "cmd"});
			//rt.exec("cmd.exe /c cd \""+new_dir+"\" & start cmd.exe /k \"java -flag -flag -cp terminal-based-program.jar\"");
			
			//IF CMD IS TO BEE SEEN:
			//rt.exec("cmd.exe /c cd \""+path+"\" & start cmd.exe /k \""+command+"\"");
			
			//IF CMD IS NOT TO BEE SEEN:
			//rt.exec("cmd.exe /c cd \""+path+"\" & cmd.exe /k \""+command+"\"");

			/*
			 * my new way for executing commands - just concatenate everything into one command 
			 * also, it's linux style(I mean / sign) so there is no need for escaping \ character
			 */
			
			//rt.exec("cmd.exe /c \""+path+"/"+command+"\"");
			
			/**
			 * Process is created because it has nice waitFor method which waits for c process to end
			 * and dosen't execute Java code till then.
			 */
			String controlString = "-gui -pardump";
//			System.out.println("cmd.exe /c \""+(ecfPath + " " + controlString + " " + paramsPath)+"\"");
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "\""+(ecfPath + " " + controlString + " " + paramsPath)+"\""); // ispravljeno, parametri se moraju odvojiti, ne mogu se samo slijepiti stringovi
			Process process = pb.start();
			process.waitFor();
			
		} catch (IOException e) {
			System.err.println("Problem with writing to cmd.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(Job job) {
		String path = job.ecfPath + ">";
		String errorFilePath = job.logFilePath + ".err";
		//		String command = job.logFilePath + " " + job.paramsPath;
		String command = job.logFilePath +  " 2> " + errorFilePath + " " + job.paramsPath;
		Process process;
		System.out.println("cmd.exe /c \"" + path + "" + command + "\"");
		try {
			process = new ProcessBuilder("cmd.exe", "/c", "\"" + path + "" + command + "\"").start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	

}
