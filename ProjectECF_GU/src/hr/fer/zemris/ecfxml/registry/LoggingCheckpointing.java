package hr.fer.zemris.ecfxml.registry;

import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class LoggingCheckpointing {
	
	private Double logLevel;
	private Double logFilename;
	private Double logFrequency;
	private Double milestoneFilename;
	private Double milestoneInterval;
	
	public LoggingCheckpointing() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (logLevel != null)
			sb.append(Xmlhalper.entryTag("log.level", logLevel)+"\n");
		if (logFilename != null)
			sb.append(Xmlhalper.entryTag("log.filename", logFilename)+"\n");
		if (logFrequency != null)
			sb.append(Xmlhalper.entryTag("log.frequency", logFrequency)+"\n");
		if (milestoneFilename != null)
			sb.append(Xmlhalper.entryTag("milestone.filename", milestoneFilename)+"\n");
		if (milestoneInterval != null)
			sb.append(Xmlhalper.entryTag("milestone.interval", milestoneInterval)+"\n");
		
		return sb.toString();
	}

	public void setLogLevel(Double logLevel) {
		this.logLevel = logLevel;
	}

	public void setLogFilename(Double logFilename) {
		this.logFilename = logFilename;
	}

	public void setLogFrequency(Double logFrequency) {
		this.logFrequency = logFrequency;
	}

	public void setMilestoneFilename(Double milestoneFilename) {
		this.milestoneFilename = milestoneFilename;
	}

	public void setMilestoneInterval(Double milestoneInterval) {
		this.milestoneInterval = milestoneInterval;
	}
	
	

}
