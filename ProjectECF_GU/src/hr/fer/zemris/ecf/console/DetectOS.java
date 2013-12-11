package hr.fer.zemris.ecf.console;

public class DetectOS {
	
	private String OS;
	public static final int WINDOWS = 1;
	public static final int MAC = 2;
	public static final int LINUX = 3;
	public static final int SOLARIS = 4;
	public static final int NOT_SUPPORTED = 5;
	
	private int os;
	
	public DetectOS() {
		OS = System.getProperty("os.name").toLowerCase();
	
		if (isWindows()) {
			os = WINDOWS;
			
		} else if (isMac()) {
			os = MAC;
			
		} else if (isUnix()) {
			os = LINUX;
			
		} else if (isSolaris()) {
			os = SOLARIS;
			
		} else {
			os = NOT_SUPPORTED;
		}		
	}
	/**
	 * This method returns required {@link ITalk} for operating system that computer is currently running.
	 * For now only Windows and Linux are implemented, everything else is classified as Linux.
	 * @return required console talk.
	 */
	public ITalk getOS_console(){
		switch (os) {
        
		case DetectOS.WINDOWS:
			return new CmdTalk();
			
		case DetectOS.LINUX:
			return new TerminalTalk();

		default:
			return new TerminalTalk();
		}
		
	}

	/**
	 * This method is used to get the OS name as the numeric value specified as a constant in this class.
	 * @return OS name as integer representation
	 */
	public int getOS_asInt(){
		return os;
	}
	
	private boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
		
	}

	private boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

	private boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	private boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	
	

}
