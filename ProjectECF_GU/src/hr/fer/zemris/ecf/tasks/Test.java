package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.Job;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		String ecfPath = ""; //even unesi nesto
		String paramsPath = "lib\\parameters.xml";
		
		ArrayList<Job> jobs = new ArrayList<>();
		for(int i=0; i<5; i++){
			jobs.add(new Job(ecfPath, "lib/log"+i, paramsPath));
		}
		
		TaskMannager ts = new TaskMannager();
		ts.startTasks(jobs, ts.getCpuCors());
	}

}
