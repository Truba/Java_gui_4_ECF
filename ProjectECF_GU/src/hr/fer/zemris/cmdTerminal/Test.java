package hr.fer.zemris.cmdTerminal;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DetectOS os = new DetectOS();
		System.out.println(os.getOS());
		
		CmdTalk.write("lib","java -jar mythsim-3.1.1.jar");

	}

}
