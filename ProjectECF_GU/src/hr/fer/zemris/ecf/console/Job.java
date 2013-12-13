package hr.fer.zemris.ecf.console;


public class Job {
	
	public String ecfPath;
	public String paramsPath;
	public String logFilePath;
	
	public boolean isDone;
	
	
	
	public Job(String ecfPath, String paramsPath, String logFilePath) {
		this.ecfPath = ecfPath;
		this.paramsPath = paramsPath;
		this.logFilePath = logFilePath;
	}



	public Job(){
		isDone = false;
	}

}
