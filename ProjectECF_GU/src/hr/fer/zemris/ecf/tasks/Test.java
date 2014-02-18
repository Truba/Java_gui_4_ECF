package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.IObserver;
import hr.fer.zemris.ecf.console.ISubject;
import hr.fer.zemris.ecf.console.Job;
import hr.fer.zemris.ecf.log.reader.OfflineReading;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		String ecfPath = "C:/Temp/GAOneMax.exe";
		String paramsPath = "test/parameters.txt";
		Nesto n = new Nesto();
		ArrayList<Job> jobs = new ArrayList<>();
		for(int i=0; i<3; i++){
			Job j = new Job(ecfPath, "test/log"+getNumOfJob(i)+".txt", paramsPath);
			j.setObserver(n);
			jobs.add(j);
		}
		
		TaskMannager ts = new TaskMannager();
		try {
			ts.startTasks(jobs, ts.getCpuCors());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getNumOfJob(int i) {
		if(i < 10) {
			return "0" + String.valueOf(i + 1);
		} 
		return String.valueOf(i + 1);
	}

	public static class Nesto implements IObserver{

		public Nesto() {
		}
		
		@Override
		public void update(ISubject subject) {
			String logFile = subject.getMessage();
			OfflineReading off = new OfflineReading();
			try {
				off.read(logFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			ArrayList<Generation> generations = off.getLogFile().generations;
			
		}
		
		
	}

}
