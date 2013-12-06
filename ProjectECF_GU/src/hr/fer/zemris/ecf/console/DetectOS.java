package hr.fer.zemris.ecf.console;

public class DetectOS {
	
	private String OS;
	public static final int WINDOWS = 1;
	public static final int MAC = 2;
	public static final int LINUX = 3;
	public static final int SOLARIS = 4;
	public static final int NOT_SUPPORTED = 5;
	
	public DetectOS() {
		OS = System.getProperty("os.name").toLowerCase();
	}
	
	public int getOS(){
		
		if (isWindows()) {
			return WINDOWS;
		} else if (isMac()) {
			return MAC;
		} else if (isUnix()) {
			return LINUX;
		} else if (isSolaris()) {
			return SOLARIS;
		} else {
			return NOT_SUPPORTED;
		}		
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
