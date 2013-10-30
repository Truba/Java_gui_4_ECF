package hr.fer.zemris.cmdTerminal;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		cmd.write("lib","java -jar mythsim-3.1.1.jar");

	}

}
