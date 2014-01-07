package hr.fer.zemris.ecf.console;

/**
 * This class's main function is to determine the pc's operating system. It's
 * getOS_console() method is used to get needed {@link ITalk} depending on the
 * pc's OS.
 * 
 * @version 1.0
 * 
 */
public class DetectOS {

	private String OS;
	public static final int WINDOWS = 1;
	public static final int MAC = 2;
	public static final int LINUX = 3;
	public static final int SOLARIS = 4;
	public static final int NOT_SUPPORTED = 5;

	private int os;

	/**
	 * Main constructor it determines the operating system and writes that in
	 * the string named OS and the int named os (number is determined by this
	 * class's constants)
	 */
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
	 * This method returns required {@link ITalk} for operating system that
	 * computer is currently running. For now only Windows and Linux are
	 * implemented, everything else is classified as Linux.
	 * 
	 * @return required console talk.
	 */
	public ITalk getOS_console() {
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
	 * This method is used to get the OS name as the numeric value specified as
	 * a constant in this class.
	 * 
	 * @return OS name as integer representation
	 */
	public int getOS_asInt() {
		return os;
	}
	
	/**
	 * This method is used to check if the operating system is Solaris.
	 * @return true if OS is Solaris.
	 */
	private boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);

	}
	
	/**
	 * This method is used to check if the operating system is Unix (linux).
	 * @return true if OS is Unix (linux).
	 */
	private boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
				.indexOf("aix") > 0);
	}

	/**
	 * This method is used to check if the operating system is Mac.
	 * @return true if OS is Mac.
	 */
	private boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	/**
	 * This method is used to check if the operating system is Windows.
	 * @return true if OS is Windows.
	 */
	private boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

}
